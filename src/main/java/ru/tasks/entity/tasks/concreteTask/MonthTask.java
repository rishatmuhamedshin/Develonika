package ru.tasks.entity.tasks.concreteTask;

import ru.tasks.entity.tasks.Priority;
import ru.tasks.entity.tasks.Task;

public class MonthTask extends Task {



    public MonthTask(Priority priority, String name, String message) {
        super(priority, name, message);
    }



    @Override
    public String toString() {
        return "Задача на месяц: " + super.toString();
    }
}
