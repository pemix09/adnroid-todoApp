package pl.edu.pb.todoapp

import pl.edu.pb.todoapp.TaskStorage
import java.util.*

class TaskStorage private constructor() {
    val tasks: MutableList<Task>
    var notDoneCount: Int = 0
    fun GetById(id: UUID): Task {
        for (task in tasks) {
            if (task.iD == id) {
                return task
            }
        }
        return Task()
    }


    init {
        tasks = ArrayList()
        for (i in 1..150) {
            val task = Task()
            task.name = "Pilne zadanie nr. $i"
            task.isDone = (i % 3 == 0)
            task.category = if(i%3 == 0) Category.Studies else Category.Home
            if(!task.isDone) notDoneCount ++
            tasks.add(task)
        }
    }

    private fun UpdateNotDoneTasks(){
        var counter: Int = 0
        for(task in taskStorage.tasks){
            if(task.isDone == false) counter ++
        }
        taskStorage.notDoneCount = counter
    }

    companion object {
        private val taskStorage = TaskStorage()
        fun GetInstance(): TaskStorage {
            return taskStorage
        }
        fun ChangeItem(editedTask: Task){
            for(task in taskStorage.tasks){
                if(editedTask.iD == task.iD){
                    task.name = editedTask.name
                    task.isDone = editedTask.isDone
                }
            }
            taskStorage.UpdateNotDoneTasks()
        }

        fun AddTask(task: Task){
            taskStorage.tasks.add(task)
            taskStorage.UpdateNotDoneTasks()
        }

        fun TaskToDoLeft(): Int{
            return taskStorage.notDoneCount
        }

    }
}