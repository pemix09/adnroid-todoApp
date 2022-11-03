package pl.edu.pb.todoapp

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class TaskListActivity : SingleFragmentActivity() {


    override fun createFragment(): Fragment {
        return TaskListFragment()
    }

}