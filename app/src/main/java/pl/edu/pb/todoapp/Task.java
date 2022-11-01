package pl.edu.pb.todoapp;

import java.util.Date;
import java.util.UUID;

public class Task {
    private UUID id;
    private String name;
    private Date date;
    private boolean done;

    public Task(){
        id = UUID.randomUUID();
        date = new Date();
    }

    public void SetName(String newName){
        this.name = newName;
    }

    public Date GetDate(){
        return this.date;
    }

    public boolean isDone(){
        return this.done;
    }
    public void setIsDone(boolean done){
        this.done = done;
    }
    public UUID getID(){return this.id;}
    public String GetName(){return this.name;}
}
