package com.example.credit_management;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class TransactionsActivity extends AppCompatActivity {
    RecyclerView recyclerView2;
    MyDatabaseHelper myDB;
    ArrayList<String> T_id, sender, receiver, sender_b1,sender_b2,receiver_b1,receiver_b2;
    TransactionsAdapter transactionsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transactions);
        recyclerView2 = findViewById(R.id.recyclerView2);
        myDB = new MyDatabaseHelper(TransactionsActivity.this);
        T_id = new ArrayList<>();
        sender = new ArrayList<>();
        receiver = new ArrayList<>();
        sender_b1 = new ArrayList<>();
        sender_b2 = new ArrayList<>();
        receiver_b1 = new ArrayList<>();
        receiver_b2 = new ArrayList<>();
        TransactionsData();
        transactionsAdapter= new TransactionsAdapter(TransactionsActivity.this, T_id, sender,receiver, sender_b1,sender_b2,receiver_b1,receiver_b2 );
        recyclerView2.setAdapter(transactionsAdapter);
        recyclerView2.setLayoutManager(new LinearLayoutManager(TransactionsActivity.this));


    }
    void TransactionsData(){
        Cursor cursor = myDB.readTransferData();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                T_id.add(cursor.getString(0));
                sender.add(cursor.getString(1));
                receiver.add(cursor.getString(2));
                sender_b1.add(cursor.getString(3));
                sender_b2.add(cursor.getString(4));
                receiver_b1.add(cursor.getString(5));
                receiver_b2.add(cursor.getString(6));

            }
        }
    }
}
