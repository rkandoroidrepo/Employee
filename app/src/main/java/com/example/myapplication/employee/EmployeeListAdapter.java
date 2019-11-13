package com.example.myapplication.employee;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.modal.Employee;

import java.util.List;


public class EmployeeListAdapter extends RecyclerView.Adapter<EmployeeListAdapter.EmployeeHolder> {
    private final List<Employee> employeeList;
    private final ItemClickListener listener;
    private Context context;

    public EmployeeListAdapter(List<Employee> employeeList, ItemClickListener listener) {
        this.employeeList = employeeList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public EmployeeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_employee_layout,
                parent, false);
        this.context = parent.getContext();
        return new EmployeeHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeHolder holder, int position) {
        holder.bind(employeeList.get(position));
        holder.edit.setOnClickListener(v -> listener.onEdit(employeeList.get(position)));
        holder.delete.setOnClickListener(v -> listener.onDelete(employeeList.get(position).getId()));
    }


    @Override
    public int getItemCount() {
        if (employeeList != null) {
            return employeeList.size();
        } else {
            return 0;
        }
    }

    public interface ItemClickListener {
        void onEdit(Employee employee);

        void onDelete(String id);
    }

    class EmployeeHolder extends RecyclerView.ViewHolder {
        private final TextView id;
        private final TextView name;
        private final TextView age;
        private final TextView salary;
        private final ImageButton edit;
        private final ImageButton delete;

        EmployeeHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.text_employee_id);
            name = itemView.findViewById(R.id.text_employee_name);
            age = itemView.findViewById(R.id.text_employee_age);
            salary = itemView.findViewById(R.id.text_employee_salary);
            edit = itemView.findViewById(R.id.btn_update_employee);
            delete = itemView.findViewById(R.id.btn_delete);

        }

        void bind(Employee employee) {
            id.setText(String.format("%s%s", context.getString(R.string.employee_id), employee.getId()));
            name.setText(String.format("%s%s", context.getString(R.string.title_employee_name), employee.getName()));
            age.setText(String.format("%s%s", context.getString(R.string.title_employee_age), employee.getAge()));
            salary.setText(String.format("%s%s", context.getString(R.string.title_employee_salary), employee.getSalary()));
        }

    }
}
