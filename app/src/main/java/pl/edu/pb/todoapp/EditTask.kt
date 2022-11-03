package pl.edu.pb.todoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import java.util.*

class EditTask : SingleFragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun createFragment(): Fragment {
        var taskId: UUID = intent.getSerializableExtra(MainActivity.taks_id_name) as UUID
        return TaskFragment.newInstance(taskId)
    }
}