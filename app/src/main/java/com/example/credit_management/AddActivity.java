package com.example.credit_management;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {
    EditText  name_input, email_input, credits_input;
    Button add_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        name_input = findViewById(R.id.name_input);
        email_input = findViewById(R.id.email_input);
        credits_input = findViewById(R.id.credits_input);
        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MyDatabaseHelper myDB = new MyDatabaseHelper(AddActivity.this);

                if(name_input.getText().toString().isEmpty() || email_input.getText().toString().isEmpty() || credits_input.getText().toString().isEmpty() ) {

                    Toast.makeText(AddActivity.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                }
                else {
                    myDB.addUser(name_input.getText().toString().trim(),
                            email_input.getText().toString().trim(),
                            Integer.valueOf(credits_input.getText().toString().trim()));
                    Intent intent = new Intent(AddActivity.this,Users.class);
                    startActivity(intent);
                }
            }
        });
    }
}
