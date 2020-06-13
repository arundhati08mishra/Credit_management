package com.example.credit_management;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class TransferActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    EditText credits_input;
    TextView name_txt_2, sender_current_credits_txt;
    Button transfer_button;

    String id, name, email, credits , receiver_name;
    Integer  entered_credits, sender_credits_new,receiver_current_credits;
    Integer receiver_credits_new,sender_current_credits;

    Spinner mySpinner;
    MyDatabaseHelper myDB;
    ArrayList<String> user_name;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer);
        user_name = new ArrayList<>();

        name_txt_2 = findViewById(R.id.user_name_txt_2);
        sender_current_credits_txt = findViewById(R.id.user_credits_txt_2);
        mySpinner = findViewById(R.id.spinner);
        mySpinner.setOnItemSelectedListener(this);

        myDB = new MyDatabaseHelper(TransferActivity.this);
        ArrayList<String> UserNames = getNames();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.spinner_layout,R.id.spinner_text,UserNames);
        mySpinner.setAdapter(adapter);
        credits_input = findViewById(R.id.credits_input2);
        transfer_button = findViewById(R.id.transfer_button);
        getAndSetIntentData();

        transfer_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(TransferActivity.this);



                if(credits_input.getText().toString().isEmpty()){

                    Toast.makeText(TransferActivity.this, "Please enter amount", Toast.LENGTH_SHORT).show();
                }
                else {
                        entered_credits = Integer.parseInt(credits_input.getText().toString());
                        sender_current_credits = Integer.parseInt(sender_current_credits_txt.getText().toString());

                        if (entered_credits >Integer.parseInt(sender_current_credits_txt.getText().toString()) ){

                            Toast.makeText(TransferActivity.this, " Insufficient credits",Toast.LENGTH_SHORT).show();
                        }

                        else {

                            sender_credits_new = Integer.parseInt(sender_current_credits_txt.getText().toString()) - entered_credits;
                            String sender_credits_new_txt = sender_credits_new.toString();
                            myDB.updateCredits(name, sender_credits_new_txt);
                            sender_current_credits_txt.setText(sender_credits_new_txt);
                            String receiver_credits_new_txt = Integer.toString(receiver_current_credits + entered_credits);
                            receiver_credits_new = receiver_current_credits + entered_credits;

                            myDB.updateCredits(receiver_name, receiver_credits_new_txt);
                            myDB.addTransaction(name,receiver_name,sender_current_credits, sender_credits_new,receiver_current_credits,receiver_credits_new);




                        }


                }



            }
        });



    }

    void getAndSetIntentData() {
        if (getIntent().hasExtra("id") && getIntent().hasExtra("name") &&
                getIntent().hasExtra("email") && getIntent().hasExtra("credits")){
            //Getting data from Intent
            id= getIntent().getStringExtra("id");
            name= getIntent().getStringExtra("name");
            email= getIntent().getStringExtra("email");
            credits= getIntent().getStringExtra("credits");

            //Setting data from Intent
            name_txt_2.setText(name);
            sender_current_credits_txt.setText(credits);

        }
        else{
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }
    }


    public ArrayList<String> getNames() {
        Cursor cursor = myDB.readUserData();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                user_name.add(cursor.getString(1));


            }
        }
        return user_name;

    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
       receiver_name = parent.getItemAtPosition(position).toString();
       receiver_current_credits = myDB.getReceiverCredits(receiver_name);

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
