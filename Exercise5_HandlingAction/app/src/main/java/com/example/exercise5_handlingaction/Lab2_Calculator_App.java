package com.example.exercise5_handlingaction;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Lab2_Calculator_App extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lab2_calculator_app);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void Add(View view) {
        EditText num1 = findViewById(R.id.edtNum1);
        EditText num2 = findViewById(R.id.edtNum2);
        EditText result = findViewById(R.id.edtResult);

        try{
            double num1Value = Double.parseDouble(num1.getText().toString());
            double num2Value = Double.parseDouble(num2.getText().toString());
            double resultValue = num1Value + num2Value;
            result.setText("RESULT: " + String.valueOf(resultValue));
        }
        catch(NumberFormatException e){
            result.setText("ERROR: INVALID INPUT");
        }
    }

    public void Subtract(View view) {
        EditText num1 = findViewById(R.id.edtNum1);
        EditText num2 = findViewById(R.id.edtNum2);
        EditText result = findViewById(R.id.edtResult);

        try {
            double num1Value = Double.parseDouble(num1.getText().toString());
            double num2Value = Double.parseDouble(num2.getText().toString());
            double resultValue = num1Value - num2Value;
            result.setText("RESULT: " + String.valueOf(resultValue));
        }
        catch(NumberFormatException e){
            result.setText("ERROR: INVALID INPUT");
        }
    }

    public void Multiply(View view) {
        EditText num1 = findViewById(R.id.edtNum1);
        EditText num2 = findViewById(R.id.edtNum2);
        EditText result = findViewById(R.id.edtResult);

        try {
            double num1Value = Double.parseDouble(num1.getText().toString());
            double num2Value = Double.parseDouble(num2.getText().toString());
            double resultValue = num1Value * num2Value;
            result.setText("RESULT: " + String.valueOf(resultValue));
        }
        catch(NumberFormatException e){
            result.setText("ERROR: INVALID INPUT");
        }
    }

    public void Divide(View view) {
        EditText num1 = findViewById(R.id.edtNum1);
        EditText num2 = findViewById(R.id.edtNum2);
        EditText result = findViewById(R.id.edtResult);

        try {
            double num1Value = Double.parseDouble(num1.getText().toString());
            double num2Value = Double.parseDouble(num2.getText().toString());

            if (num2Value == 0) {
                result.setText("ERROR: DIVISION BY ZERO");
                return;
            }
            else {
                double resultValue = num1Value / num2Value;
                result.setText("RESULT: " + String.valueOf(resultValue));
            }
        }
        catch(NumberFormatException e){
            result.setText("ERROR: INVALID INPUT");
        }
    }
}