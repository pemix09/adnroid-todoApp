package pl.edu.pb.todoapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment

class TaskFragment : Fragment() {

    private lateinit var task: Task
    private lateinit var nameField: EditText
    private lateinit var dateButton: Button
    private lateinit var isDoneCheckBox: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        task = Task()
        nameField = requireView().findViewById(R.id.task_name)
        nameField.doAfterTextChanged {
            task.name = nameField.toString()
        }

        dateButton = requireView().findViewById<Button>(R.id.task_date)
        dateButton.text = task.date.toString()
        dateButton.isEnabled = false

        isDoneCheckBox = requireView().findViewById(R.id.task_done)
        isDoneCheckBox.isChecked = task.isDone
        isDoneCheckBox.setOnCheckedChangeListener{
            task.isDone = isDoneCheckBox.isChecked
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_task, container, false)
    }
}