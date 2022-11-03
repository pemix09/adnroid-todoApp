package pl.edu.pb.todoapp

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class TaskListFragment : Fragment() {

    var adapter: TaskAdapter = TaskAdapter(TaskStorage.GetInstance().tasks)
    lateinit var recyclerView: RecyclerView;

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_task_list, container, false)
        recyclerView = view.findViewById(R.id.task_recycler_view)
        recyclerView.adapter = this.adapter
        recyclerView.layoutManager = LinearLayoutManager(activity)
        return view
    }


    inner class TaskHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.list_item_task, parent, false)),
        View.OnClickListener {
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

            try{
                var intent: Intent = Intent(view.context, EditTask().javaClass)
                intent.putExtra(MainActivity.taks_id_name, task?.iD)
                startActivity(intent)
            }
            catch(e: ActivityNotFoundException){
                Toast.makeText(context,e.toString(),Toast.LENGTH_LONG).show();
                System.out.print(e.toString())
            }


        }
    }
    inner class TaskAdapter(private val tasks: List<Task>) : RecyclerView.Adapter<TaskHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskHolder {
            val layoutInflater = LayoutInflater.from(activity)
            return TaskHolder(layoutInflater, parent)
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