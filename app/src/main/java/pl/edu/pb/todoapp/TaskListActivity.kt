package pl.edu.pb.todoapp

import pl.edu.pb.todoapp.SingleFragmentActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import pl.edu.pb.todoapp.R
import pl.edu.pb.todoapp.TaskListFragment

class TaskListActivity : SingleFragmentActivity() {

    lateinit var recyclerView: RecyclerView
    val tasks: List<Task> = TaskStorage.GetInstance().tasks

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_task_list)

        recyclerView = findViewById(R.id.task_recycler_view)
    }

    override fun createFragment(): Fragment? {
        val fragmentManager = supportFragmentManager
        return fragmentManager.findFragmentById(R.id.task_recycler_view)
    }
}