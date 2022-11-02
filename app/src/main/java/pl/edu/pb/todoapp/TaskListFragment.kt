package pl.edu.pb.todoapp

import androidx.recyclerview.widget.RecyclerView
import pl.edu.pb.todoapp.TaskAdapter
import android.widget.TextView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import pl.edu.pb.todoapp.R
import androidx.recyclerview.widget.LinearLayoutManager
import pl.edu.pb.todoapp.TaskStorage
import android.content.Intent
import android.view.View
import androidx.fragment.app.Fragment

class TaskListFragment : Fragment() {
    override fun onResume() {
        super.onResume()
        updateView()
    }

    private var recycleView: RecyclerView? = null
    private var adapter: TaskAdapter? = null
    private lateinit var nameTextView: TextView
    private lateinit var dateTextView: TextView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_task_list, container, false)
        recycleView = view.findViewById(R.id.task_recycler_view)
        recycleView?.setLayoutManager(LinearLayoutManager(activity))
        return view
    }

    private fun updateView() {
        val taskStorage = TaskStorage.GetInstance()
        val tasks = taskStorage.tasks
        if (adapter == null) {
            adapter = TaskAdapter(this,tasks)
            recycleView!!.adapter = adapter
        } else {
            adapter!!.notifyDataSetChanged()
        }
    }




    companion object {
        var KEY_EXTRA_TASK_ID = "KEY_EXTRA_TASK_ID"
        val tasks: List<Task> = TaskStorage.GetInstance().tasks
    }
}