package com.example.androidcrud;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {

    private List<Student> studentList;
    private OnStudentClickListener listener;

    public interface OnStudentClickListener {
        void onEditStudent(Student student);
        void onDeleteStudent(Student student);
    }

    public StudentAdapter(List<Student> studentList, OnStudentClickListener listener) {
        this.studentList = studentList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_item, parent, false);
        return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        Student student = studentList.get(position);
        holder.tvName.setText(student.getName());
        holder.tvMssv.setText(student.getMssv());
        holder.tvClass.setText(student.getStudentClass());
        holder.tvGpa.setText(String.valueOf(student.getGpa()));

        holder.btnEdit.setOnClickListener(v -> listener.onEditStudent(student));
        holder.btnDelete.setOnClickListener(v -> listener.onDeleteStudent(student));
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    public static class StudentViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvMssv, tvClass, tvGpa;
        Button btnEdit, btnDelete;

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvMssv = itemView.findViewById(R.id.tvMssv);
            tvClass = itemView.findViewById(R.id.tvClass);
            tvGpa = itemView.findViewById(R.id.tvGpa);
            btnEdit = itemView.findViewById(R.id.btnEdit);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }
}
