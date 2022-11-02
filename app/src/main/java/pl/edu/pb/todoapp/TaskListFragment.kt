package pl.edu.pb.todoapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class TaskListFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val recyclerView = view?.findViewById<RecyclerView>(R.id.task_recycler_view)
        recyclerView?.layoutManager = LinearLayoutManager(activity)
        return recyclerView
    }

    inner class TaskHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        private var task: Task? = null
        private var nameTextView: TextView
        private var dateTextView: TextView

        init {
            itemView.setOnClickListener(this)
            nameTextView = itemView.findViewById(R.id.task_item_name)
            dateTextView = itemView.findViewById(R.id.task_item_date)
        }

        fun Bind(task: Task) {
            this.task = task
            nameTextView.text = task.name
            dateTextView.text = task.date.toString()
        }

        override fun onClick(view: View) {

            var intent: Intent = Intent(activity, MainActivity.javaClass)
            intent.putExtra(MainActivity.taks_id_name, task?.iD)
            startActivity(intent)

        }
    }
    inner class TaskAdapter(private val context: Context, private val tasks: List<Task>) : RecyclerView.Adapter<TaskHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskHolder {
            val layoutInflater = LayoutInflater.from(context)
            val view: View = layoutInflater.inflate(R.layout.list_item_task, parent, false)
            return TaskHolder(view)
        }

        override fun onBindViewHolder(holder: TaskHolder, position: Int) {
            val task = tasks[position]
            holder.Bind(task)
        }

        override fun getItemCount(): Int {
            return tasks.size
        }
    }
}