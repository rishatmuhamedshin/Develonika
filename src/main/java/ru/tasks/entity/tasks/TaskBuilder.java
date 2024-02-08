package ru.tasks.entity.tasks;

import ru.tasks.entity.tasks.concreteTask.DailyTask;
import ru.tasks.entity.tasks.concreteTask.MonthTask;
import ru.tasks.entity.tasks.concreteTask.WeeklyTask;

public class TaskBuilder {
        private Priority priority = Priority.SECOND;
        private String name = "Новая задача";
        private String description = "...";
        private Status status = Status.NEW;

    public TaskBuilder buildPriority(Priority priority) {
        this.priority = priority;
        return this;
    }

    public TaskBuilder buildName(String name) {
        this.name = name;
        return this;
    }

    public TaskBuilder buildDescription(String description) {
        this.description = description;
        return this;
    }

    public TaskBuilder buildStatus(Status status) {
        this.status = status;
        return this;
    }



    public Task buildDailyTask(){
        Task task = new DailyTask(priority,name,description);
        task.setName(name);
        task.setStatus(status);
        task.setDateControlPoint(task.getDateControlPoint().plusDays(1));
        return task;
    }
    public Task buildMonthTask(){
        Task task = new MonthTask(priority,name,description);
        task.setStatus(status);
        task.setDateControlPoint(task.getDateControlPoint().plusDays(31));
        return task;
    }
    public Task buildWeeklyTask(){
        Task task = new WeeklyTask(priority,name,description);
        task.setStatus(status);
        task.setDateControlPoint(task.getDateControlPoint().plusDays(7));
        return task;
    }

}
