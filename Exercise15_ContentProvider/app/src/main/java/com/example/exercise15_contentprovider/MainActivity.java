package com.example.exercise15_contentprovider;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
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

    private EditText edtName, edtPhone;
    private TextView tvResult;
    private Button btnAdd, btnQuery;

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

        edtName = findViewById(R.id.edtName);
        edtPhone = findViewById(R.id.edtPhone);
        tvResult = findViewById(R.id.tvResult);
        btnAdd = findViewById(R.id.btnAdd);
        btnQuery = findViewById(R.id.btnQuery);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addContact();
            }
        });

        btnQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                queryContacts();
            }
        });
    }

    private void addContact() {
        String name = edtName.getText().toString();
        String phone = edtPhone.getText().toString();

        if (name.isEmpty() || phone.isEmpty()) {
            tvResult.setText("Please enter name and phone number!");
        }

        ContentValues values = new ContentValues();
        values.put(DBHelper.COLUMN_NAME, name);
        values.put(DBHelper.COLUMN_PHONE, phone);

        Uri uri = getContentResolver().insert(ContactsProvider.CONTENT_URI, values);

        tvResult.setText("Added Contact: " + uri.toString());
    }

    private void queryContacts() {
        Cursor cursor = getContentResolver().query(ContactsProvider.CONTENT_URI, null, null, null, null);
        if(cursor != null){
            tvResult.setText("");
            while (cursor.moveToNext()){
                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex(DBHelper.COLUMN_ID));
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_NAME));
                @SuppressLint("Range") String phone = cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_PHONE));
                tvResult.append("ID: " + id + ", \n");
                tvResult.append("Name: " + name + ", \n");
                tvResult.append("Phone: " + phone + ". \n");
            }

            cursor.close();
        }
    }
}