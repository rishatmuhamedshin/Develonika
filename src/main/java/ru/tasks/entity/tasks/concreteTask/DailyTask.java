package ru.tasks.entity.tasks.concreteTask;

import ru.tasks.entity.tasks.Priority;
import ru.tasks.entity.tasks.Task;

public class DailyTask extends Task {



    public DailyTask(Priority priority, String name, String message) {
        super(priority, name, message);
    }



    @Override
    public String toString() {
        return "Дневная задача: " + super.toString();
    }
}
