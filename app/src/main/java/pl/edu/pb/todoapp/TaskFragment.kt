package pl.edu.pb.todoapp

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import java.util.UUID

class TaskFragment(taskId: UUID) : Fragment() {

    private val task: Task = TaskStorage.GetInstance().GetById(taskId)
    private lateinit var nameField: EditText
    private lateinit var dateButton: Button
    private lateinit var isDoneCheckBox: CheckBox


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

        dateButton = view.findViewById<Button>(R.id.task_date)
        dateButton.text = task.date.toString()
        dateButton.isEnabled = false

        isDoneCheckBox = view.findViewById(R.id.task_done)
        isDoneCheckBox.isChecked = task.isDone
        isDoneCheckBox.setOnCheckedChangeListener{ buttonView, isChecked ->
            task.isDone = isChecked
            TaskStorage.ChangeItem(task)
        }
        return view
    }

    companion object{
        fun newInstance(taskId: UUID) : TaskFragment{
            var newInstance = TaskFragment(taskId)
            return newInstance
        }
    }
}