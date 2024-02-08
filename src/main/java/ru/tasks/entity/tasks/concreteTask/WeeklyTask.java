package ru.tasks.entity.tasks.concreteTask;

import ru.tasks.entity.tasks.Priority;
import ru.tasks.entity.tasks.Task;

public class WeeklyTask extends Task {



    public WeeklyTask(Priority priority, String name, String message) {
        super(priority, name, message);
    }



    @Override
    public String toString() {
        return "Недельная задача: " + super.toString();
    }
}
