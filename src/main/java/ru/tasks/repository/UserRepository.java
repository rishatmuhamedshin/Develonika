package ru.tasks.repository;

import ru.tasks.entity.User;
import ru.tasks.exception.NoSuchUserInDirectory;
import ru.tasks.exception.UserAlredyExistException;

import java.util.*;

public class UserRepository {

    private static final List<User> users = new ArrayList<>();

    public void showAll(){
        System.out.println(Collections.singletonList(users));
    }

    public void saveUser(User user) throws UserAlredyExistException {
        if(!users.contains(user)){
            users.add(user);
        }else throw new UserAlredyExistException();

    }
    public void deleteUserByName(String name) throws NoSuchUserInDirectory {
        for(User user: users){
            if(user.getName().equals(name)){
                users.remove(user);
            }
            throw new NoSuchUserInDirectory();
        }
    }

    public User findIfPresent(User user) throws NoSuchUserInDirectory {
        if(users.contains(user)){
            for(User u : users){
                if(u.equals(user)){
                    return u;
                }
            }
        }
        throw new NoSuchUserInDirectory();
    }


    public User findUserByName(String name) throws NoSuchUserInDirectory {
        for(User user: users){
            if(user.getName().equals(name)){
                return user;
            }
        }
        throw new NoSuchUserInDirectory();
    }
}
