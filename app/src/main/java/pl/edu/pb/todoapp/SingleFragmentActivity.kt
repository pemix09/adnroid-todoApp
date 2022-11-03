package pl.edu.pb.todoapp

import android.os.Bundle
import android.os.PersistableBundle
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

abstract class SingleFragmentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentManager = supportFragmentManager
        var fragment: Fragment? = fragmentManager.findFragmentById(R.id.fragment_container)

        if(fragment == null) {
            fragment = createFragment()
            fragmentManager.beginTransaction()
                .add(R.id.fragment_container, fragment)
                .commit()
        }
    }

    protected abstract fun createFragment(): Fragment
}