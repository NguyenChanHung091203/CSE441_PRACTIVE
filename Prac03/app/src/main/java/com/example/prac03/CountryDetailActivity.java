package com.example.prac03;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class CountryDetailActivity extends AppCompatActivity {
    private ImageView imageViewFlag;
    private TextView textViewCountryNameDetail;
    private TextView textViewPopulationDetail;
    private TextView textViewDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_detail);

        imageViewFlag = findViewById(R.id.imageView_flag);
        textViewCountryNameDetail = findViewById(R.id.textView_countryNameDetail);
        textViewPopulationDetail = findViewById(R.id.textView_populationDetail);
        textViewDetails = findViewById(R.id.textView_details);

        // Nhận dữ liệu từ Intent
        Intent intent = getIntent();
        String countryName = intent.getStringExtra("countryName");
        long population = intent.getLongExtra("population", 0);
        String details = intent.getStringExtra("details");
        int flagResource = intent.getIntExtra("flagResource", 0);  // Nhận tài nguyên hình ảnh

        // Thiết lập dữ liệu vào các View
        textViewCountryNameDetail.setText(countryName);
        textViewPopulationDetail.setText("Dân số: " + population);
        textViewDetails.setText(details);
        imageViewFlag.setImageResource(flagResource);  // Hiển thị hình ảnh quốc kỳ
    }
}
