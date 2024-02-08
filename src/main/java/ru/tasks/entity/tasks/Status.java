package ru.tasks.entity.tasks;

public enum Status {
    DONE("Выполнена"),PROGRESS("В процессе"),NEW("Новая");

    private final String name;

    Status(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
}
