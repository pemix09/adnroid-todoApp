package pl.edu.pb.todoapp

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.DatePicker
import android.widget.EditText
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import java.text.SimpleDateFormat
import java.util.*

class TaskFragment(taskId: UUID) : Fragment(), OnDateSetListener {

    private val task: Task = TaskStorage.GetInstance().GetById(taskId)
    private val calendar: Calendar = Calendar.getInstance()
    private lateinit var nameField: EditText
    private lateinit var isDoneCheckBox: CheckBox
    private lateinit var dateField: EditText
    private val datePattern = "E, dd MMM yyyy HH:mm"
    private val dateFormat: SimpleDateFormat = SimpleDateFormat(datePattern)


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view: View = inflater.inflate(R.layout.fragment_task, container, false)
        nameField = view.findViewById(R.id.task_name)
        nameField.setText(task.name)
        nameField.doAfterTextChanged {
            task.name = nameField.text.toString()
            TaskStorage.ChangeItem(task)
        }


        isDoneCheckBox = view.findViewById(R.id.task_done)
        isDoneCheckBox.isChecked = task.isDone
        isDoneCheckBox.setOnCheckedChangeListener{ buttonView, isChecked ->
            task.isDone = isChecked
            TaskStorage.ChangeItem(task)
        }

        dateField = view.findViewById(R.id.task_date)
        dateField.setText(dateFormat.format(task.date))
        OnDateSetListener { view, year, month, dayOfMonth ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            setupDateFieldValue(calendar.time)
            task.date = calendar.time
        }

        dateField.setOnClickListener{view ->
            DatePickerDialog(requireContext(), this, calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show()
        }

        return view
    }

    fun setupDateFieldValue(date: Date){
        var locale = Locale("pl", "PL")
        var dateFormat = SimpleDateFormat("dd.MM.yyyy", locale)
        dateField.setText(dateFormat.format(date))
    }

    companion object{
        fun newInstance(taskId: UUID) : TaskFragment{
            var newInstance = TaskFragment(taskId)
            return newInstance
        }
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        this.task.date = Date(year, month, dayOfMonth)
        dateField.setText(dateFormat.format(task.date))
        TaskStorage.ChangeItem(task)
    }
}