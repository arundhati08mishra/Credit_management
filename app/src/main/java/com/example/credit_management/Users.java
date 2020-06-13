package com.example.credit_management;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Users extends AppCompatActivity {
    RecyclerView recyclerView;
    FloatingActionButton add_button;
    MyDatabaseHelper myDB;
    ArrayList<String>  user_id, user_name, user_email, user_credits;
    CustomAdapter customAdapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);
        recyclerView = findViewById(R.id.recyclerView);
        add_button = findViewById(R.id.add_button);


        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Users.this, AddActivity.class);
                startActivity(intent);
            }
        });
        myDB = new MyDatabaseHelper(Users.this);
        user_id = new ArrayList<>();
        user_name = new ArrayList<>();
        user_email = new ArrayList<>();
        user_credits = new ArrayList<>();
        storeDataInArrays();

        customAdapter= new CustomAdapter(Users.this, this, user_id, user_name, user_email, user_credits);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(Users.this));



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1){
            recreate();
        }
    }

    void storeDataInArrays() {
        Cursor cursor = myDB.readUserData();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                user_id.add(cursor.getString(0));
                user_name.add(cursor.getString(1));
                user_email.add(cursor.getString(2));
                user_credits.add(cursor.getString(3));

            }
        }

    }


    public void onBackPressed() {
       Intent intent = new Intent(Users.this, MainActivity.class);
       startActivity(intent);

    }
}
