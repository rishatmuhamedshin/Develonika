package ru.tasks.exception;

public class NoSuchTaskException extends Throwable {
    public NoSuchTaskException() {
        super("Нет такой задачи !!!");
    }
}
