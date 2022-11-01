package pl.edu.pb.todoapp;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TaskStorage {
    private static final TaskStorage taskStorage = new TaskStorage();
    private final List<Task> tasks;

    public static TaskStorage GetInstance(){
        return taskStorage;
    }

    public Task GetById(UUID id){
        for (Task task : this.tasks) {
            if(task.getID().equals(id)){
                return task;
            }
        }
        return null;
    }

    public List<Task> getTasks(){return this.tasks;}

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
