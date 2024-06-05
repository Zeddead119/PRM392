package com.example.exercise12_sqlitedb;

import android.content.Intent;
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

public class UpdateCourseActivity extends AppCompatActivity {

    private EditText edtId, edtName, edtDescription;
    private Button btnUpdate, btnDelete;
    private DBHandler dbHandler;

    private Course course;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_update_course);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        edtId = findViewById(R.id.edtId);
        edtName = findViewById(R.id.edtName);
        edtDescription = findViewById(R.id.edtDescription);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);

        dbHandler = new DBHandler(this);

        Intent intent = getIntent();
        String courseID = intent.getStringExtra("course_id");
        course = dbHandler.getCourseByID(courseID);

        if (course != null) {
            edtId.setText(course.getId());
            edtName.setText(course.getName());
            edtDescription.setText(course.getDescription());
        }

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                course.setName(edtName.getText().toString());
                course.setDescription(edtDescription.getText().toString());
                dbHandler.updateCourse(course);
                Toast.makeText(UpdateCourseActivity.this, "Course updated successfully!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHandler.deleteCourse(course);
                Toast.makeText(UpdateCourseActivity.this, "Course deleted successfully!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}