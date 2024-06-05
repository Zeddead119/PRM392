package com.example.exercise12_sqlitedb;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Button btnAdd;
    private List<Course> courses;
    private CourseAdapter courseAdapter;
    private DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recyclerView = findViewById(R.id.recyclerView);
        btnAdd = findViewById(R.id.btnAdd);
        dbHandler = new DBHandler(this);
        courses = dbHandler.getAllCourses();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        courseAdapter = new CourseAdapter(courses);
        recyclerView.setAdapter(courseAdapter);

        courseAdapter.setOnItemClickListener(new CourseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(MainActivity.this, UpdateCourseActivity.class);
                intent.putExtra("course_id", courses.get(position).getId());
                startActivity(intent);
            }

            @Override
            public void onEditClick(int position) {
                Intent intent = new Intent(MainActivity.this, UpdateCourseActivity.class);
                intent.putExtra("course_id", courses.get(position).getId());
                startActivity(intent);
            }

            @Override
            public void onDeleteClick(int position) {
                dbHandler.deleteCourse(courses.get(position));
                courses.remove(position);
                courseAdapter.notifyItemRemoved(position);
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddCourseActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        courses.clear();
        courses.addAll(dbHandler.getAllCourses());
        courseAdapter.notifyDataSetChanged();
    }
}