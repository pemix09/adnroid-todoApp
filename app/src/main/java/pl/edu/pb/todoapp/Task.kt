package pl.edu.pb.todoapp

import java.util.*

class Task {
    val iD: UUID = UUID.randomUUID()
    var date: Date = Date()
    var name: String? = null
    var isDone: Boolean = false
    var category: Category = Category.Home
}