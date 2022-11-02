package pl.edu.pb.todoapp

import pl.edu.pb.todoapp.TaskStorage
import java.util.*

class TaskStorage private constructor() {
    val tasks: MutableList<Task>
    fun GetById(id: UUID): Task? {
        for (task in tasks) {
            if (task.iD == id) {
                return task
            }
        }
        return null
    }


    init {
        tasks = ArrayList()
        for (i in 1..150) {
            val task = Task()
            task.name = "Pilne zadanie nr. $i"
            task.isDone = (i % 3 == 0)
            tasks.add(task)
        }
    }

    companion object {
        private val taskStorage = TaskStorage()
        fun GetInstance(): TaskStorage {
            return taskStorage
        }
    }
}