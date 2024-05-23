package com.example.exercise8_recycleview;

import android.os.Bundle;

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
    private AnimalAdapter animalAdapter;
    private List<Animal> animalList;

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
        animalList = new ArrayList<>();
        animalAdapter = new AnimalAdapter(this, animalList);
        recyclerView.setAdapter(animalAdapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        animalList.add(new Animal(1, "Lion", "The lion is a species in the family Felidae."));
        animalList.add(new Animal(2, "Tiger", "The tiger is the largest living cat species."));
        animalList.add(new Animal(3, "Elephant", "Elephants are the largest existing land animals."));
    }
}