package ru.tasks;

import ru.tasks.entity.User;
import ru.tasks.entity.tasks.Priority;
import ru.tasks.entity.tasks.Status;
import ru.tasks.entity.tasks.Task;
import ru.tasks.entity.tasks.TaskBuilder;
import ru.tasks.exception.UserAlredyExistException;
import ru.tasks.repository.TaskRepository;
import ru.tasks.repository.UserRepository;
import ru.tasks.service.TaskService;
import ru.tasks.service.UserService;

import java.util.Scanner;

public class Application {
    private static final TaskService taskService = new TaskService(new TaskRepository(), new UserRepository());
    private static final UserService userService = new UserService(new UserRepository());
    private final Scanner in = new Scanner(System.in);


    private void start() throws InterruptedException {
        System.out.println("Запуск приложения TaskTracker.....");
        System.out.println("Вы зарегестрированы ? (y/n)");
        char c = in.next().charAt(0);
        switch (c) {
            case 'y' -> logIn();
            case 'n' -> registration();
            default -> {
                System.out.println("Вы ввели не правильное значение");
                System.exit(0);
            }
        }
        process();
    }

    private void process() throws InterruptedException {
        while (true) {
            Thread.sleep(1200);
            System.out.println("Выберите действие:" +
                    "\n__________________________________" +
                    "\nПоменять имя пользователя: 1" +
                    "\nУдалить пользователя: 2" +
                    "\n__________________________________" +
                    "\nДобавить новую задачу: 3" +
                    "\nПоказать все задачи: 4" +
                    "\nИзменить статус задачи: 5" +
                    "\nИзменить приоритет задачи: 6" +
                    "\nИзменить контрольную точку: 7" +
                    "\nУдалить задачу: 8" +
                    "\nЧтобы выйти нажмите : q");
            char action = in.next().charAt(0);


            if (action == 'q') {
                finish();
            } else {
                switch (action) {

                    case '1' -> userService.updateUserName(in);
                    case '2' -> userService.deleteUser(in);
                    case '3' -> taskService.createNewTask(in);
                    case '4' -> taskService.showAllTasks();
                    case '5' -> taskService.setStatus();
                    case '6' -> taskService.setPriority();
                    case '7' -> {
                        System.out.println("Введите кол-во дней");

                        int days = in.nextInt();
                        taskService.changeControlPoint(days);
                    }
                    case '8' -> taskService.deleteTask();
                    default -> throw new IllegalStateException("Нет такого числа: " + action);
                }
            }
        }
    }


    private void finish() {
        System.out.println("До скорых встреч !!!");
        System.out.println("Выключение приложения TaskTracker.....");
        in.close();
        System.exit(0);
    }

    private void registration() {
        System.out.println("Введите данные: ");
        System.out.print("Имя: ");
        String name = in.next();
        System.out.print("Пароль: ");
        char[] password = in.next().toCharArray();
        if (!userService.registration(name, password)) {
            System.out.println("Давайте заново");
            registration();
        }

    }

    private void logIn() {
        System.out.println("Введите данные: ");
        System.out.print("Имя: ");
        String name = in.next();
        System.out.print("Пароль: ");
        char[] password = in.next().toCharArray();
        if (!userService.logIn(name, password)) {
            System.out.println("Давайте заново");
            logIn();
        }
    }

    private void fillData(){
        UserRepository userRepository = new UserRepository();
        TaskRepository taskRepository = new TaskRepository();


        User misha = new User("Misha","asdf".toCharArray());
        User sasha = new User("Sasha","qwerty".toCharArray());
        User alina = new User("Alina","zxcv".toCharArray());

        try {
            userRepository.saveUser(misha);
            userRepository.saveUser(sasha);
            userRepository.saveUser(alina);
        } catch (UserAlredyExistException e) {
            e.printStackTrace();
        }


        Task task = new TaskBuilder()
                .buildDescription("Задание для Миши")
                .buildName("Домашняя работа")
                .buildPriority(Priority.FIRST)
                .buildStatus(Status.PROGRESS)
                .buildDailyTask();

        Task task2 = new TaskBuilder()
                .buildDescription("Задание для Саши")
                .buildName("Работа в офисе")
                .buildPriority(Priority.SECOND)
                .buildStatus(Status.NEW)
                .buildWeeklyTask();

        Task task3 = new TaskBuilder()
                .buildDescription("Задание для Алины")
                .buildName("Поиск новой работы")
                .buildPriority(Priority.IMPORTANT)
                .buildStatus(Status.DONE)
                .buildMonthTask();

        taskRepository.addTask(task,misha);
        taskRepository.addTask(task2,sasha);
        taskRepository.addTask(task3,alina);

    }


    public static void StartApplication() throws InterruptedException {
        Application application = new Application();
        application.fillData();
        application.start();
    }
}



