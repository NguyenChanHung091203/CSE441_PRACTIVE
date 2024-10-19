package com.example.androidcrud;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.firestore.FirebaseFirestore;

public class EditStudentActivity extends AppCompatActivity {

    private EditText etMssv, etName, etClass, etGpa;
    private Button btnSave;
    private FirebaseFirestore db;
    private String mssv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        etMssv = findViewById(R.id.etMssv);
        etName = findViewById(R.id.etName);
        etClass = findViewById(R.id.etClass);
        etGpa = findViewById(R.id.etGpa);
        btnSave = findViewById(R.id.btnSave);
        db = FirebaseFirestore.getInstance();

        // Nhận thông tin sinh viên từ Intent
        Student student = (Student) getIntent().getSerializableExtra("student");
        if (student != null) {
            mssv = student.getMssv();
            etMssv.setText(mssv);
            etName.setText(student.getName());
            etClass.setText(student.getStudentClass());
            etGpa.setText(String.valueOf(student.getGpa()));
        }

        btnSave.setOnClickListener(v -> saveStudent());
    }

    private void saveStudent() {
        String name = etName.getText().toString().trim();
        String studentClass = etClass.getText().toString().trim();
        String gpaStr = etGpa.getText().toString().trim();

        if (name.isEmpty() || studentClass.isEmpty() || gpaStr.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            return;
        }

        double gpa = Double.parseDouble(gpaStr);

        // Cập nhật thông tin sinh viên trong Firestore
        db.collection("students").document(mssv)
                .update("name", name, "studentClass", studentClass, "gpa", gpa)
                .addOnSuccessListener(aVoid -> {
                    Toast.makeText(EditStudentActivity.this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                    finish(); // Đóng activity
                })
                .addOnFailureListener(e ->
                        Toast.makeText(EditStudentActivity.this, "Cập nhật thất bại: " + e.getMessage(), Toast.LENGTH_SHORT).show()
                );
    }
}
