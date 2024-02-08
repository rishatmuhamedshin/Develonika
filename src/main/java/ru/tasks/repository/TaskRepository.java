package ru.tasks.repository;

import ru.tasks.entity.User;
import ru.tasks.entity.tasks.Task;
import ru.tasks.exception.NoSuchTaskException;
import ru.tasks.exception.NoSuchUserInDirectory;

import java.util.HashMap;
import java.util.Map;

public class TaskRepository {

    private static final Map<Task, User> performers = new HashMap<>();

    public void deleteTask(Task task) throws NoSuchTaskException {
        if(performers.containsKey(task)){
            performers.remove(task);
        }else throw new NoSuchTaskException();
    }

    public void addTask(Task task,User user) {
        performers.put(task,user);
    }

    public Task getTaskByUser(User user) throws NoSuchTaskException {
        for (Map.Entry<Task,User> map : performers.entrySet()){
            if( map.getValue().equals(user) ) return map.getKey();
        }
        throw new NoSuchTaskException();
    }

    public User getUserByTask(Task task) throws NoSuchUserInDirectory {
        for (Map.Entry<Task,User> map : performers.entrySet()){
            if( map.getKey().equals(task) ) return map.getValue();
        }
        throw new NoSuchUserInDirectory();
    }

    public Task getTaskByName(String name) throws NoSuchTaskException {
        for (Map.Entry<Task,User> map : performers.entrySet()){
            if( map.getKey().getName().equals(name) ) return map.getKey();
        }
        throw new NoSuchTaskException();
    }



    public Map<Task, User> getPerformers() {
        return performers;
    }
}
