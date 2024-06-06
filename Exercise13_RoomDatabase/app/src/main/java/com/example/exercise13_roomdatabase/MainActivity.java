package com.example.exercise13_roomdatabase;

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

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Button btnAdd;
    private List<Course> courses = new ArrayList<>();
    private CourseAdapter courseAdapter;
    private CourseDatabase db;

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

        db = CourseDatabase.getInstance(this);

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
                final Course course = courses.get(position);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        db.courseDao().delete(course);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                courses.remove(position);
                                courseAdapter.notifyItemRemoved(position);
                            }
                        });
                    }
                }).start();
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

    private void loadCourse(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                courses.clear();
                courses.addAll(db.courseDao().getAllCourses());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        courseAdapter.notifyDataSetChanged();
                    }
                });
            }
        }).start();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadCourse();
    }
}