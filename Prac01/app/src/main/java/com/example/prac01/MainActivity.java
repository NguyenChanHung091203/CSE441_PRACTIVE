package com.example.prac01;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_CHILD = 1;
    private Button btnOpenChild;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnOpenChild = findViewById(R.id.btnOpenChild);
        tvResult = findViewById(R.id.tvResult);

        btnOpenChild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ChildActivity.class);
                startActivityForResult(intent, REQUEST_CODE_CHILD);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_CODE_CHILD && resultCode == RESULT_OK && data != null){
            String firstName = data.getStringExtra("firstName");
            String lastName = data.getStringExtra("lastName");
            double gpa = data.getDoubleExtra("gpa", 0.0);

            String result = "Họ: " + firstName + "\nTên: " + lastName + "\nĐiểm TB: " + gpa;
            tvResult.setText(result);
        } else {
            Toast.makeText(this, "Không nhận được dữ liệu từ Activity con", Toast.LENGTH_SHORT).show();
        }
    }
}