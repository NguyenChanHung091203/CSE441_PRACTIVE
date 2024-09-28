package com.example.prac01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ChildActivity extends AppCompatActivity {

    private EditText etFirstName, etLastName, etGPA;
    private Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child);

        etFirstName = findViewById(R.id.etFirstName);
        etLastName = findViewById(R.id.etLastName);
        etGPA = findViewById(R.id.etGPA);
        btnSubmit = findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String firstName = etFirstName.getText().toString().trim();
                String lastName = etLastName.getText().toString().trim();
                String gpaStr = etGPA.getText().toString().trim();
                double gpa = 0.0;

                if(!gpaStr.isEmpty()){
                    try {
                        gpa = Double.parseDouble(gpaStr);
                    } catch (NumberFormatException e){
                        e.printStackTrace();
                        // Xử lý nếu nhập sai định dạng số
                    }
                }

                Intent resultIntent = new Intent();
                resultIntent.putExtra("firstName", firstName);
                resultIntent.putExtra("lastName", lastName);
                resultIntent.putExtra("gpa", gpa);

                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });
    }
}