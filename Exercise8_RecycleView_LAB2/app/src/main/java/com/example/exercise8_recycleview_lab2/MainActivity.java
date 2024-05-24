package com.example.exercise8_recycleview_lab2;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProductAdapter adapter;
    private List<Product> products;

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
        recyclerView.setHasFixedSize(true);
        products = new ArrayList<>();
        adapter = new ProductAdapter(products, this);
        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));

        products.add(new Product(1, "pharmacy", "Pharmacy Product Description", R.drawable.pharmacy));
        products.add(new Product(2, "registry", "Registry Product Description", R.drawable.registry));
        products.add(new Product(3, "cartwheel", "Cartwheel Product Description", R.drawable.cartwheel));
        products.add(new Product(4, "clothing", "Clothing Product Description", R.drawable.clothing));
        products.add(new Product(5, "shoes", "Shoes Product Description", R.drawable.shoes));
        products.add(new Product(6, "accessories", "Accessories Product Description", R.drawable.accessories));
        products.add(new Product(7, "baby", "Baby Product Description", R.drawable.baby));
        products.add(new Product(8, "home", "Home Product Description", R.drawable.home));
        products.add(new Product(9, "patio & garden", "Patio & Garden Product Description", R.drawable.patio_garden));
    }
}