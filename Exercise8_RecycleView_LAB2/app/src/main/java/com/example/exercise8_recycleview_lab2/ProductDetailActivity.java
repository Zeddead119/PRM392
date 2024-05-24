package com.example.exercise8_recycleview_lab2;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ProductDetailActivity extends AppCompatActivity {

    private TextView tvId;
    private TextView tvName;
    private TextView tvDescription;
    private ImageView ivImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.product_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tvId = findViewById(R.id.tvIdDetail);
        tvName = findViewById(R.id.tvNameDetail);
        tvDescription = findViewById(R.id.tvDescriptionDetail);
        ivImage = findViewById(R.id.ivImageDetail);

        int id = getIntent().getIntExtra("id", -1);
        String name = getIntent().getStringExtra("name");
        String description = getIntent().getStringExtra("description");
        int imageResId = getIntent().getIntExtra("imageResId", -1);

        tvId.setText(String.valueOf(id));
        tvName.setText(name);
        tvDescription.setText(description);
        ivImage.setImageResource(imageResId);
    }
}