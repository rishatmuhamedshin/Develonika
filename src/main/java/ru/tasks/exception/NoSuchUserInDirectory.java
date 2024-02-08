package ru.tasks.exception;

public class NoSuchUserInDirectory extends Throwable {

    public NoSuchUserInDirectory() {
        super("Нет такого пользователя !!!");
    }
}
