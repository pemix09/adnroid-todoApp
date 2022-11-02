package pl.edu.pb.todoapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import java.util.UUID

class MainActivity : SingleFragmentActivity() {

    companion object{
        val taks_id_name: String = "Task ID"
    }



    override fun createFragment(): Fragment {
        var taskId: UUID = intent.getSerializableExtra(taks_id_name) as UUID
        return TaskFragment.newInstance(taskId)
    }


}