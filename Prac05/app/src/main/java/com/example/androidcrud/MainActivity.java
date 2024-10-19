package com.example.androidcrud;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private StudentAdapter studentAdapter;
    private List<Student> studentList;
    private FirebaseFirestore db;
    private Button btnAddStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        btnAddStudent = findViewById(R.id.btnAddStudent);
        studentList = new ArrayList<>();
        db = FirebaseFirestore.getInstance();

        // Tạo StudentAdapter với OnStudentClickListener
        studentAdapter = new StudentAdapter(studentList, new StudentAdapter.OnStudentClickListener() {
            @Override
            public void onEditStudent(Student student) {
                Intent intent = new Intent(MainActivity.this, EditStudentActivity.class);
                intent.putExtra("student", student); // Gửi thông tin sinh viên để chỉnh sửa
                startActivity(intent);
            }

            @Override
            public void onDeleteStudent(Student student) {
                db.collection("students").document(student.getMssv())
                        .delete()
                        .addOnSuccessListener(aVoid -> {
                            studentList.remove(student);
                            studentAdapter.notifyDataSetChanged();
                            Toast.makeText(MainActivity.this, "Đã xóa sinh viên", Toast.LENGTH_SHORT).show();
                        })
                        .addOnFailureListener(e ->
                                Toast.makeText(MainActivity.this, "Xóa thất bại: " + e.getMessage(), Toast.LENGTH_SHORT).show()
                        );
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(studentAdapter);

        // Lấy dữ liệu từ Firestore
        getStudentsFromFirestore();

        btnAddStudent.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddStudentActivity.class);
            startActivity(intent); // Mở Activity thêm sinh viên
        });
    }

    private void getStudentsFromFirestore() {
        db.collection("students")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        studentList.clear(); // Xóa danh sách cũ trước khi thêm mới
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            String mssv = document.getString("mssv");
                            String name = document.getString("name");
                            String studentClass = document.getString("studentClass");
                            double gpa = document.getDouble("gpa");

                            Student student = new Student(mssv, name, studentClass, gpa, ""); // Thay thế "" với avatarUrl nếu cần
                            studentList.add(student);
                        }
                        studentAdapter.notifyDataSetChanged(); // Cập nhật adapter
                    } else {
                        Toast.makeText(MainActivity.this, "Lấy dữ liệu thất bại: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
