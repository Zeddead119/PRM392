package com.example.exercise12_sqlitedb;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddCourseActivity extends AppCompatActivity {

    private EditText edtId, edtName, edtDescription;
    private Button btnSave;
    private DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_course);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        edtId = findViewById(R.id.edtId);
        edtName = findViewById(R.id.edtName);
        edtDescription = findViewById(R.id.edtDescription);
        btnSave = findViewById(R.id.btnSave);

        dbHandler = new DBHandler(this);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = edtId.getText().toString();
                String name = edtName.getText().toString();
                String description = edtDescription.getText().toString();

                if (id.isEmpty() || name.isEmpty() || description.isEmpty()) {
                    Toast.makeText(AddCourseActivity.this, "Please fill in all fields!", Toast.LENGTH_SHORT).show();
                    return;
                }

                Course course = new Course(id, name, description);
                dbHandler.addCourse(course);
                Toast.makeText(AddCourseActivity.this, "Course added successfully!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}