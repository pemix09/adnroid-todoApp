package pl.edu.pb.todoapp

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class TaskHolder(infalter: LayoutInflater, parent: ViewGroup?) :
    RecyclerView.ViewHolder(infalter.inflate(R.layout.list_item_task, parent, false)),
    View.OnClickListener {
    private var task: Task? = null

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
        val intent = Intent(activity, MainActivity::class.java)
        intent.putExtra(TaskListFragment.KEY_EXTRA_TASK_ID, task!!.iD)
        startActivity(intent)
    }
}