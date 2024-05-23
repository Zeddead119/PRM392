package com.example.exercise8_recycleview;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ItemDetailActivity extends AppCompatActivity {

    private TextView tvId;
    private TextView tvName;
    private TextView tvDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.item_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tvId = findViewById(R.id.tvDetailId);
        tvName = findViewById(R.id.tvDetailName);
        tvDescription = findViewById(R.id.tvDetailDescription);

        int id = getIntent().getIntExtra("id", -1);
        String name = getIntent().getStringExtra("name");
        String description = getIntent().getStringExtra("description");

        tvId.setText(String.valueOf(id));
        tvName.setText(name);
        tvDescription.setText(description);
    }
}