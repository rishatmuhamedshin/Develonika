package ru.tasks.service;

import ru.tasks.entity.User;
import ru.tasks.entity.tasks.Priority;
import ru.tasks.entity.tasks.Status;
import ru.tasks.entity.tasks.Task;
import ru.tasks.entity.tasks.TaskBuilder;
import ru.tasks.exception.NoSuchTaskException;
import ru.tasks.exception.NoSuchUserInDirectory;
import ru.tasks.repository.TaskRepository;
import ru.tasks.repository.UserRepository;

import java.util.Map;
import java.util.Scanner;

public class TaskService {

    private TaskRepository taskRepository;
    private UserRepository userRepository;

    public TaskService(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    public void showAllTasks() {
        Map<Task, User> taskUserMap = taskRepository.getPerformers();
        System.out.println("Все задачи и их исполнители:");
        if (taskUserMap.isEmpty()) {
            System.out.println("Пусто ....");
            return;
        }
        for (Map.Entry<Task, User> entry : taskUserMap.entrySet()) {
            System.out.println(entry.getKey().getName() + ":" + entry.getValue());
        }
    }

    public void addTaskUser(Task task) {
        System.out.println(task);
        System.out.println("Введите имя человека, которому хотите задать задачу");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.next();
        try {
            User user = userRepository.findUserByName(name);
            taskRepository.addTask(task, user);
            System.out.println("Задача  \"" + task.getName() + "\" успешно добавилась");
        } catch (NoSuchUserInDirectory e) {
            System.out.println(e.getMessage());
        }
    }

    public void createNewTask(Scanner scanner) {
        Priority priority;
        Task task = new TaskBuilder().buildDailyTask();
        System.out.println("Создаем новую задачу");
        System.out.println("Название задачи: ");
        String name = scanner.next();
        System.out.println();
        System.out.println("Выберите приоритет :" +
                "\n1 -> ВАЖНАЯ!!!" +
                "\n2 -> Первоочередная" +
                "\n3 -> Второстепенная");
        int number = scanner.nextInt();
        System.out.println("Выберите  тип задачи:" +
                "\n1 -> Дневная" +
                "\n2 -> Месячная" +
                "\n3 -> Недельная");
        int term = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Описание: ");
        String description = scanner.nextLine();
        if (number == 1) priority = Priority.IMPORTANT;
        else if (number == 2) priority = Priority.FIRST;
        else priority = Priority.SECOND;

        switch (term) {
            case 1 -> task = new TaskBuilder()
                    .buildDescription(description)
                    .buildName(name)
                    .buildStatus(Status.NEW)
                    .buildPriority(priority)
                    .buildDailyTask();
            case 2 -> task = new TaskBuilder()
                    .buildDescription(description)
                    .buildName(name)
                    .buildStatus(Status.NEW)
                    .buildPriority(priority)
                    .buildMonthTask();
            case 3 -> task = new TaskBuilder()
                    .buildDescription(description)
                    .buildName(name)
                    .buildStatus(Status.NEW)
                    .buildPriority(priority)
                    .buildWeeklyTask();
        }
        addTaskUser(task);
    }

    public void deleteTask() {
        try {
            Task task = findTask();
            taskRepository.deleteTask(task);
            System.out.println("Задача : " + task.getName() + " успешно удалилась");
        } catch (NoSuchTaskException e) {
            System.out.println(e.getMessage());
        }
    }

    public void setPriority() {
        try {
            Task task = findTask();
            String taskPriority = task.getPriority();
            if (taskPriority.equals("ВАЖНАЯ!!!")) task.setPriority(Priority.SECOND);
            else if (taskPriority.equals("Первоочередная")) task.setPriority(Priority.IMPORTANT);
            else task.setPriority(Priority.FIRST);
            System.out.println("Приоретет задачи : \"" + task.getName()
                    + "\" изменился на \"" + task.getPriority() + "\"");
        } catch (NoSuchTaskException e) {
            System.out.println(e.getMessage());
        }
    }


    public void setStatus() {
        try {
            Task task = findTask();
            String taskStatus = task.getStatus();
            if (taskStatus.equals("Новая")) task.setStatus(Status.PROGRESS);
            else if (taskStatus.equals("В процессе")) task.setStatus(Status.DONE);
            else task.setStatus(Status.NEW);
            System.out.println("Статус задачи : " + task.getName()
                    + " изсенился на " + task.getStatus());
        } catch (NoSuchTaskException e) {
            System.out.println(e.getMessage());
        }

    }

    public void changeControlPoint(int days) {
        try {
            Task task = findTask();
            task.getDateControlPoint().plusDays(days);
            System.out.println("Задаче : " + task.getName()
                    + " добавилось " + days + " дней");
        } catch (NoSuchTaskException e) {
            System.out.println(e.getMessage());
        }
    }

    private Task findTask() throws NoSuchTaskException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("В ведите имя задачи:");
        String name = scanner.nextLine();
        scanner.nextLine();
        return taskRepository.getTaskByName(name);
    }

}
