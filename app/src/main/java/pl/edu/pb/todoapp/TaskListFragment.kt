package pl.edu.pb.todoapp

import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat

class TaskListFragment : Fragment() {

    var data: List<Task> = TaskStorage.GetInstance().tasks
    var adapter: TaskAdapter = TaskAdapter(data)
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

    override fun onResume() {
        super.onResume()
        data = TaskStorage.GetInstance().tasks
        adapter.notifyDataSetChanged()
    }


    inner class TaskHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.list_item_task, parent, false)),
        View.OnClickListener {
        private var task: Task? = null
        private var nameTextView: TextView = itemView.findViewById(R.id.task_item_name)
        private var dateTextView: TextView = itemView.findViewById(R.id.task_item_date)
        private val iconItemView: ImageView = itemView.findViewById(R.id.category_image_view)
        public val checkBox: CheckBox = itemView.findViewById(R.id.task_checkbox_done)
        var datePattern = "E, dd MMM yyyy HH:mm"
        var dateFormat: SimpleDateFormat = SimpleDateFormat(datePattern)

        init {
            itemView.setOnClickListener(this)
        }

        fun Bind(task: Task) {
            this.task = task
            nameTextView.text = task.name
            dateTextView.text = dateFormat.format(task.date)
            checkBox.isChecked = task.isDone
            iconItemView.setImageResource(if(task.category == Category.Home) R.drawable.home else R.drawable.study)
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
            var checkbox = holder.checkBox
            checkbox.setOnCheckedChangeListener { buttonView, isChecked ->
                tasks[holder.bindingAdapterPosition].isDone = isChecked
            }
        }

        override fun getItemCount(): Int {
            return tasks.size
        }
    }
}