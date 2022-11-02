package pl.edu.pb.todoapp

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class TaskListActivity : SingleFragmentActivity() {


    lateinit var recyclerView: RecyclerView
    val tasks: List<Task> = TaskStorage.GetInstance().tasks

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
    }

    override fun createFragment(): Fragment {
        return TaskListFragment()
    }

}