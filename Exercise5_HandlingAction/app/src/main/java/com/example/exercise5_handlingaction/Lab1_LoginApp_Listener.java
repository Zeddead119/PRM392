package com.example.exercise5_handlingaction;

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

public class Lab1_LoginApp_Listener extends AppCompatActivity {

    public Button myButton1;
    public EditText myUser1;
    public EditText myPass1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lab1_login_app_listener);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        myButton1 = (Button) findViewById(R.id.btnOk1);
        myUser1 = (EditText) findViewById(R.id.editUser1);
        myPass1 = (EditText) findViewById(R.id.editPassword1);

        myButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(myUser1.getText().toString().equals("admin") && myPass1.getText().toString().equals("12345"))
                {
                    Toast.makeText(Lab1_LoginApp_Listener.this, "Login Successful", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(Lab1_LoginApp_Listener.this, "Login Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}