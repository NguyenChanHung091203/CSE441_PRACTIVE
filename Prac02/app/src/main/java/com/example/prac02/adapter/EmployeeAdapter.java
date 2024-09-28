package com.example.prac02.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.prac02.model.Employee;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.prac02.R;

import java.util.List;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder> {

    private List<Employee> employeeList;

    public void setEmployeeList(List<Employee> employees) {
        this.employeeList = employees;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public EmployeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_employee, parent, false);
        return new EmployeeViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeViewHolder holder, int position) {
        if (employeeList != null) {
            Employee current = employeeList.get(position);
            holder.name.setText(current.getName());
            holder.position.setText("Vị trí: " + current.getPosition());
            holder.department.setText("Phòng ban: " + current.getDepartment());
        }
    }

    @Override
    public int getItemCount() {
        if (employeeList != null)
            return employeeList.size();
        else return 0;
    }

    public static class EmployeeViewHolder extends RecyclerView.ViewHolder {
        private final TextView name;
        private final TextView position;
        private final TextView department;

        public EmployeeViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.textView_name);
            position = itemView.findViewById(R.id.textView_position);
            department = itemView.findViewById(R.id.textView_department);
        }
    }
}

