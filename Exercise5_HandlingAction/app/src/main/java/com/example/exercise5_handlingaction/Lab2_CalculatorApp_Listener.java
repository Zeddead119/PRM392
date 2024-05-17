package com.example.exercise5_handlingaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Lab2_CalculatorApp_Listener extends AppCompatActivity {

    public Button btnAdd;
    public Button btnSub;
    public Button btnMul;
    public Button btnDiv;
    public EditText num1;
    public EditText num2;
    public EditText result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lab2_calculatorapp_listener);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnSub = (Button) findViewById(R.id.btnSub);
        btnMul = (Button) findViewById(R.id.btnMul);
        btnDiv = (Button) findViewById(R.id.btnDiv);
        num1 = findViewById(R.id.edtNum1);
        num2 = findViewById(R.id.edtNum2);
        result = findViewById(R.id.edtResult);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
        });

        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
        });

        btnMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
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
        });

        btnDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
        });
    }
}