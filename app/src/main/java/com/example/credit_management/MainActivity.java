package com.example.credit_management;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private Button ViewUsersBtn , ViewTransfersBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewUsersBtn = findViewById(R.id.ViewUsersBtn);
        ViewTransfersBtn = findViewById(R.id.ViewTransfersBtn);
        ViewUsersBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(MainActivity.this, Users.class);
                startActivity(intent);
            }
        });

        ViewTransfersBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(MainActivity.this, TransactionsActivity.class);
                startActivity(intent);
            }
        });
    }



}

