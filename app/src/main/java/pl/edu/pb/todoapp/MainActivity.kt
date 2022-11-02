package pl.edu.pb.todoapp

import pl.edu.pb.todoapp.SingleFragmentActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import pl.edu.pb.todoapp.R
import pl.edu.pb.todoapp.TaskFragment

class MainActivity : SingleFragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fragmentManager = supportFragmentManager
        var fragment = createFragment()
        if (fragment == null) {
            fragment = TaskFragment()
            fragmentManager.beginTransaction()
                .add(R.id.fragment_container, fragment)
                .commit()
        }
    }

    override fun createFragment(): Fragment {
        val fragmentManager = supportFragmentManager
        return fragmentManager.findFragmentById(R.id.fragment_container)!!
    }
}