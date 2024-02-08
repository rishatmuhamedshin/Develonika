package ru.tasks.service;

import ru.tasks.entity.User;
import ru.tasks.exception.NoSuchUserInDirectory;
import ru.tasks.exception.UserAlredyExistException;
import ru.tasks.repository.UserRepository;

import java.util.Scanner;

public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private boolean userValidation(String name, char[] password) {
        if (name.isEmpty()) return false;
        if (password.length == 0) return false;
        else return true;
    }

    public boolean logIn(String name, char[] password) {
        User user = new User(name, password);
        try {
            if (userRepository.findIfPresent(user) != null) {
                System.out.println("Доро пожаловать," + name);
                return true;
            }
        } catch (NoSuchUserInDirectory e) {
            System.out.println("\nТакого пользователя не существует");
        }
        return false;
    }

    public boolean registration(String name, char[] password) {
        if (userValidation(name, password)) {
            User user = new User(name, password);
            try {
                userRepository.saveUser(user);
            } catch (UserAlredyExistException e) {
                System.out.println(e.getMessage());
            }
            System.out.println("Добро пожаловать," + name);
            return true;
        } else {
            System.out.println("Пустые данные");
            return false;
        }
    }

    public void deleteUser(Scanner in) {
        System.out.println("Введите имя и пароль пользователя, которого хотите удалить");
        System.out.println("Имя: ");
        String name = in.next();
        System.out.print("Пароль: ");
        char[] password = in.next().toCharArray();
        if(userValidation(name,password)){
            try {
                userRepository.deleteUserByName(name);
                System.out.println("Пока, " + name);
            } catch (NoSuchUserInDirectory e) {
                System.out.println(e.getMessage());
            }
        }
        userRepository.showAll();
    }

    public void updateUserName(Scanner in) {
        System.out.println("Введите имя пользователя, у которого хотите поменять имя: ");
        String oldName = in.next();
        in.nextLine();
        System.out.println("Введите новое имя пользователя: ");
        String newName = in.next();
        in.nextLine();
        System.out.print("Пароль: ");
        char[] password = in.next().toCharArray();
        if(userValidation(newName,password)){
            try {
                User user = userRepository.findIfPresent(new User(oldName, password));
                user.setName(newName);
                System.out.println("Пользователь успешно изменен");
            } catch (NoSuchUserInDirectory e) {
                System.out.println("-----------------Ошибка в данных----------------");
            }
        }

    }

    public void updateUserPassword(Scanner scanner) {
        System.out.println("Введите имя пользователя, у которого хотите поменять пароль: ");
        String name = scanner.next();
        System.out.println("Введите старый пароль:");
        char[] oldPassword = scanner.next().toCharArray();
        System.out.print("Введите новый пароль: ");
        char[] newPassword = scanner.next().toCharArray();
        if(userValidation(name,newPassword)){
            try {
                User user = userRepository.findIfPresent(new User(name, oldPassword));
                user.setPassword(newPassword);
                System.out.println("Пользователь успешно изменен");
            } catch (NoSuchUserInDirectory e) {
                System.out.println(e.getMessage());
            }
        }

    }


}
