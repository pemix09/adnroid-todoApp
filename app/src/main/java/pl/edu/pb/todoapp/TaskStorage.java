package pl.edu.pb.todoapp;

import java.util.ArrayList;
import java.util.List;

public class TaskStorage {
    private static final TaskStorage taskStorage = new TaskStorage();
    private final List<Task> tasks;

    public static TaskStorage GetInstance(){
        return taskStorage;
    }

    private TaskStorage(){
        tasks = new ArrayList<>();
        for(int i = 1; i <= 150; i++){
            Task task = new Task();
            task.SetName("Pilne zadanie nr. "+i);
            task.setIsDone( i%3 == 0);
            tasks.add(task);
        }
    }
}
