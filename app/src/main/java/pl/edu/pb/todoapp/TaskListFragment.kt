package pl.edu.pb.todoapp

import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Paint
import android.os.Bundle
import android.view.*
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat

class TaskListFragment : Fragment() {

    var data: List<Task> = TaskStorage.GetInstance().tasks
    var adapter: TaskAdapter = TaskAdapter(data)
    lateinit var recyclerView: RecyclerView;
    var subtitleIsVisible: Boolean = false

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.new_task -> {
                var task = Task()
                TaskStorage.AddTask(task)
                var intent = Intent(activity, EditTask().javaClass)
                intent.putExtra(MainActivity.taks_id_name, task?.iD)
                startActivity(intent)
                true
            }
            //TODO - show subtitle
            R.id.show_subtitle -> {
                var subtitle = getString(R.string.subtitle_format, TaskStorage.TaskToDoLeft())
                activity?.title = subtitle
                this.subtitleIsVisible = true
                activity?.invalidateOptionsMenu()
                true
            }
            R.id.hide_subtitle -> {
                activity?.title = getString(R.string.app_name)
                this.subtitleIsVisible = false
                activity?.invalidateOptionsMenu()
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        data = TaskStorage.GetInstance().tasks
        adapter.notifyDataSetChanged()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.fragment_task_menu, menu)
        if(this.subtitleIsVisible){
            var subtitle = getString(R.string.subtitle_format, TaskStorage.TaskToDoLeft())
            activity?.title = subtitle
            inflater.inflate(R.menu.hide_subtitle, menu)
        }else{
            inflater.inflate(R.menu.show_subtitle, menu)
        }
    }

    inner class TaskHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.list_item_task, parent, false)),
        View.OnClickListener {
        private var task: Task? = null
        var nameTextView: TextView = itemView.findViewById(R.id.task_item_name)
        var dateTextView: TextView = itemView.findViewById(R.id.task_item_date)
        private val iconItemView: ImageView = itemView.findViewById(R.id.category_image_view)
        val checkBox: CheckBox = itemView.findViewById(R.id.task_checkbox_done)
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
            if(task.isDone){
                nameTextView.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                dateTextView.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            }
            iconItemView.setImageResource(if(task.category == Category.Home) R.drawable.home else R.drawable.study)
        }

        override fun onClick(view: View) {

            try{
                var intent: Intent = Intent(view.context, EditTask().javaClass)
                intent.putExtra(MainActivity.taks_id_name, task?.iD)
                startActivity(intent)
                activity?.invalidateOptionsMenu()
            }
            catch(e: ActivityNotFoundException){
                Toast.makeText(context,e.toString(),Toast.LENGTH_LONG).show();
                print(e.toString())
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
                TaskStorage.ChangeItem(tasks[holder.bindingAdapterPosition])
                activity?.invalidateOptionsMenu()
                if(isChecked){
                    holder.nameTextView.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                    holder.dateTextView.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                }else{
                    holder.nameTextView.paintFlags = Paint.ANTI_ALIAS_FLAG
                    holder.dateTextView.paintFlags = Paint.ANTI_ALIAS_FLAG
                }
            }
        }

        override fun getItemCount(): Int {
            return tasks.size
        }
    }
}