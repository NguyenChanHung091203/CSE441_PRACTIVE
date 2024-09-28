package com.example.prac02;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.prac02.model.Employee;
import com.example.prac02.viewmodel.EmployeeViewModel;
import com.example.prac02.adapter.EmployeeAdapter;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EmployeeViewModel employeeViewModel;
    private EmployeeAdapter employeeAdapter;
    private Button buttonAddEmployee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Khởi tạo ViewModel
        employeeViewModel = new ViewModelProvider(this).get(EmployeeViewModel.class);

        // Thiết lập RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerView_employees);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        employeeAdapter = new EmployeeAdapter();
        recyclerView.setAdapter(employeeAdapter);

        // Quan sát LiveData từ ViewModel
        employeeViewModel.getEmployees().observe(this, new Observer<List<Employee>>() {
            @Override
            public void onChanged(List<Employee> employees) {
                // Cập nhật Adapter khi dữ liệu thay đổi
                employeeAdapter.setEmployeeList(employees);
            }
        });

        // Xử lý sự kiện khi nhấn nút thêm nhân viên
        buttonAddEmployee = findViewById(R.id.button_add_employee);
        buttonAddEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddEmployeeDialog();
            }
        });

        // Thêm một vài nhân viên mẫu
        if (employeeViewModel.getEmployees().getValue().isEmpty()) {
            employeeViewModel.addEmployee(new Employee("Nguyễn Văn A", "Giám đốc", "Quản trị"));
            employeeViewModel.addEmployee(new Employee("Trần Thị B", "Kế toán", "Tài chính"));
        }
    }

    private void showAddEmployeeDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Thêm Nhân Viên Mới");

        // Layout chứa các EditText
        View viewInflated = getLayoutInflater().inflate(R.layout.dialog_add_employee, null);

        final EditText inputName = viewInflated.findViewById(R.id.editText_name);
        final EditText inputPosition = viewInflated.findViewById(R.id.editText_position);
        final EditText inputDepartment = viewInflated.findViewById(R.id.editText_department);

        builder.setView(viewInflated);

        // Các nút trong dialog
        builder.setPositiveButton("Thêm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Lấy dữ liệu từ các EditText
                String name = inputName.getText().toString().trim();
                String position = inputPosition.getText().toString().trim();
                String department = inputDepartment.getText().toString().trim();

                if (!name.isEmpty() && !position.isEmpty() && !department.isEmpty()) {
                    // Thêm nhân viên vào ViewModel
                    employeeViewModel.addEmployee(new Employee(name, position, department));
                    Toast.makeText(MainActivity.this, "Thêm nhân viên thành công!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Vui lòng điền đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
                }

                dialog.dismiss();
            }
        });

        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }
}