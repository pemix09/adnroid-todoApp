package pl.edu.pb.todoapp


import android.widget.EditText
import android.widget.CheckBox
import android.os.Bundle
import android.text.TextWatcher
import android.text.Editable
import android.widget.CompoundButton
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import pl.edu.pb.todoapp.TaskFragment
import java.util.*

class TaskFragment : Fragment() {
    private var task: Task? = null
    private var nameField: EditText? = null
    private var dateButton: Button? = null
    private var doneCheckBox: CheckBox? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        val taskId = requireArguments().getSerializable(TaskListFragment.KEY_EXTRA_TASK_ID) as UUID
        task = TaskStorage.GetInstance().GetById(taskId)
        nameField = requireView().findViewById(R.id.task_name)
        nameField?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                task?.name = s.toString()
            }

            override fun afterTextChanged(s: Editable) {}
        })

        dateButton = requireView().findViewById(R.id.task_date)
        dateButton?.text = task?.date.toString()
        dateButton?.isEnabled = false
        doneCheckBox = requireView().findViewById(R.id.task_done)
        doneCheckBox?.isChecked = task!!.isDone
        doneCheckBox?.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { buttonView: CompoundButton?, isChecked: Boolean ->
            task?.isDone = isChecked
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        nameField!!.setText(task!!.name)
        dateButton!!.text = task!!.date.toString()
        doneCheckBox!!.isChecked = task!!.isDone
        return inflater.inflate(R.layout.fragment_task, container, false)
    }

    companion object {
        fun newInstance(taskId: UUID?): TaskFragment {
            val bundle = Bundle()
            bundle.putSerializable(TaskListFragment.KEY_EXTRA_TASK_ID, taskId)
            val taskFragment = TaskFragment()
            taskFragment.arguments = bundle
            return taskFragment
        }
    }
}