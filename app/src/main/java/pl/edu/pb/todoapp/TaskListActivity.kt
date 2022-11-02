package pl.edu.pb.todoapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView

class TaskListActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    val tasks: List<Task> = TaskStorage.GetInstance().tasks

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_task_list)

        recyclerView = findViewById(R.id.task_recycler_view)
        var adapter: TaskAdapter = TaskAdapter(this, tasks)
        recyclerView.adapter = adapter
    }

}