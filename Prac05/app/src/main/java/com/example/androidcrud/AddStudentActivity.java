package com.example.androidcrud;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.androidcrud.Student;
import com.google.firebase.firestore.FirebaseFirestore;

public class AddStudentActivity extends AppCompatActivity {

    private EditText etName, etMssv, etClass, etGpa;
    private ImageView imgAvatar;
    private Button btnSave;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        etName = findViewById(R.id.etName);
        etMssv = findViewById(R.id.etMssv);
        etClass = findViewById(R.id.etClass);
        etGpa = findViewById(R.id.etGpa);
        imgAvatar = findViewById(R.id.imgAvatar);
        btnSave = findViewById(R.id.btnSave);

        // Khởi tạo Firestore
        db = FirebaseFirestore.getInstance();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveStudentToFirestore();
            }
        });
    }

    private void saveStudentToFirestore() {
        String name = etName.getText().toString();
        String mssv = etMssv.getText().toString();
        String studentClass = etClass.getText().toString();
        double gpa = Double.parseDouble(etGpa.getText().toString());

        // Tạo đối tượng Student
        Student newStudent = new Student(mssv, name, studentClass, gpa, ""); // Thay thế "" với avatarUrl khi cần

        // Lưu đối tượng vào Firestore
        db.collection("students") // Tên bộ sưu tập
                .add(newStudent)
                .addOnSuccessListener(documentReference -> {
                    Toast.makeText(AddStudentActivity.this, "Thêm sinh viên thành công", Toast.LENGTH_SHORT).show();
                    // Quay lại Activity trước
                    setResult(RESULT_OK, new Intent());
                    finish();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(AddStudentActivity.this, "Thêm sinh viên thất bại: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }
}
