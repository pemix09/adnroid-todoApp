package pl.edu.pb.todoapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TaskListFragment extends Fragment {

    @Override
    public void onResume() {
        super.onResume();
        updateView();
    }

    private RecyclerView recycleView;
    private TaskAdapter adapter;
    private TextView nameTextView;
    private TextView dateTextView;
    public static String KEY_EXTRA_TASK_ID = "KEY_EXTRA_TASK_ID";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_task_list, container, false);
        recycleView = view.findViewById(R.id.task_recycler_view);
        recycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }

    private void updateView(){
        TaskStorage taskStorage = TaskStorage.GetInstance();
        List<Task> tasks = taskStorage.getTasks();

        if(adapter == null){
            adapter = new TaskAdapter(tasks);
            recycleView.setAdapter(adapter);
        }
        else{
            adapter.notifyDataSetChanged();
        }
    }

    private class TaskHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private Task task;

        public TaskHolder(LayoutInflater infalter, ViewGroup parent){
            super(infalter.inflate(R.layout.list_item_task, parent, false));
            itemView.setOnClickListener(this);

            nameTextView = itemView.findViewById(R.id.task_item_name);
            dateTextView = itemView.findViewById(R.id.task_item_date);
        }

        public void Bind(Task task){
            this.task = task;
            nameTextView.setText(task.GetName());
            dateTextView.setText(task.GetDate().toString());
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(getActivity(), MainActivity.class);
            intent.putExtra(KEY_EXTRA_TASK_ID, task.getID());
            startActivity(intent);
        }
    }

    private class TaskAdapter extends RecyclerView.Adapter<TaskHolder>{
        private List<Task> tasks;

        public TaskAdapter(List<Task> tasks){
            this.tasks = tasks;
        }

        @NonNull
        @Override
        public TaskHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new TaskHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(@NonNull TaskHolder holder, int position) {
            Task task = tasks.get(position);
            holder.Bind(task);
        }


        @Override
        public int getItemCount() {
            return tasks.size();
        }
    }
}
