package ru.tasks.entity.tasks;

public enum Priority {
    IMPORTANT("ВАЖНАЯ!!!"),FIRST("Первоочередная"),SECOND("Второстепенная");

    private final String name;

    Priority(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
