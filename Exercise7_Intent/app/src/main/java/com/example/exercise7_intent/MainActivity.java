package com.example.exercise7_intent;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    public TextView edtTextName;
    public EditText edtTextUrl;
    public Button btnClickMe;
    public Button btnDial;
    public Button btnURL;

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

        edtTextName = (EditText) findViewById(R.id.edtTextName);
        edtTextUrl = findViewById(R.id.edtTextURL);
        btnClickMe = findViewById(R.id.btnClickMe);
        btnDial = findViewById(R.id.btnDial);
        btnURL = findViewById(R.id.btnURL);

        btnClickMe.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                String yourName = edtTextName.getText().toString();
                intent.putExtra("MESSAGE", yourName);
                startActivity(intent);
            }
        });

        btnDial.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(Intent.ACTION_DIAL);
                startActivity(intent);
            }
        });

        btnURL.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String url = edtTextUrl.getText().toString();
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            }
        });
    }
}