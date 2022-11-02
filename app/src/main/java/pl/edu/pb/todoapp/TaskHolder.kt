package pl.edu.pb.todoapp

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TaskHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
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
//        val intent = Intent(activity, MainActivity::class.java)
//        intent.putExtra(TaskListFragment.KEY_EXTRA_TASK_ID, task!!.iD)
//        startActivity(intent)
    }
}