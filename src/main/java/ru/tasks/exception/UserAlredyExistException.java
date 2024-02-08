package ru.tasks.exception;

public class UserAlredyExistException extends Throwable {
    public UserAlredyExistException() {
        super("Пользователь уже есть");
    }
}
