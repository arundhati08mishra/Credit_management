package com.example.credit_management;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;




class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context context;

    private static final String DATABASE_NAME = "Credits.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME1 = "Users";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_Name = "name";
    private static final String COLUMN_email = "email";
    private static final String COLUMN_credits = "credits";

    private static final String TABLE_NAME2 = "Transfer";
    private static final String COLUMN_TID = "t_id";
    private static final String COLUMN_sender = "sender";
    private static final String COLUMN_receiver = "receiver";
    private static final String COLUMN_sender_b1 = "sender_b1";
    private static final String COLUMN_sender_b2 = "sender_b2";
    private static final String COLUMN_receiver_b1 = "receive_b1r";
    private static final String COLUMN_receiver_b2 = "receiver_b2";



    MyDatabaseHelper( Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //Create Table Users
        String query1 = "CREATE TABLE " + TABLE_NAME1 +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_Name + " TEXT, " +
                COLUMN_email + " TEXT, " +
                COLUMN_credits + " INTEGER);";

        //Create Table Transfer
        String query2= "CREATE TABLE " + TABLE_NAME2 +
                " (" + COLUMN_TID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_sender + " TEXT, " +
                COLUMN_receiver + " TEXT, " +
                COLUMN_sender_b1 + " INTEGER, " +
                COLUMN_sender_b2 + " INTEGER, " +
                COLUMN_receiver_b1 + " INTEGER, " +
                COLUMN_receiver_b2 + " INTEGER);";


        db.execSQL(query1);
        db.execSQL(query2);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME1);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME2);
        onCreate(db);

    }

    void addUser(String name, String email, int credits){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put(COLUMN_Name, name);
        cv.put(COLUMN_email, email);
        cv.put(COLUMN_credits, credits);
        long result = db.insert(TABLE_NAME1,null, cv);
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Added Successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    void addTransaction(String sender, String receiver, int sender_b1,int sender_b2,int receiver_b1,int receiver_b2){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put(COLUMN_sender, sender);
        cv.put(COLUMN_receiver, receiver);
        cv.put(COLUMN_sender_b1, sender_b1);
        cv.put(COLUMN_sender_b2, sender_b2);
        cv.put(COLUMN_receiver_b1, receiver_b1);
        cv.put(COLUMN_receiver_b2, receiver_b2);


        long result = db.insert(TABLE_NAME2,null, cv);
        if(result == -1){
            Toast.makeText(context, "Failed transfer", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "transfer Successful!", Toast.LENGTH_SHORT).show();
        }
    }




    Cursor readUserData(){
        String query = "SELECT * FROM " + TABLE_NAME1;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    public int getReceiverCredits(String name)
    {
        //String query = "SELECT credits FROM " + TABLE_NAME1 +" WHERE name = name";
        SQLiteDatabase db = this.getWritableDatabase();
        String[] columns = {COLUMN_credits};
        String[] selectionArgs = {name};
        String selection = COLUMN_Name+"=?";

        //Cursor cursor = db.query(TABLE_NAME1, columns, "=?", new String[] { name }, null, null, null);
        Cursor cursor = db.query(TABLE_NAME1,columns,selection,selectionArgs,null,null,null);
        int count = 0;
        int currentCredit = 0;
        while (cursor.moveToNext() && count != 1)
        {
            currentCredit = cursor.getInt(cursor.getColumnIndex(COLUMN_credits));
            count++;
        }
        return  currentCredit;



    }



    Cursor readTransferData(){
        String query = "SELECT * FROM " + TABLE_NAME2;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor2 = null;
        if(db != null){
            cursor2 = db.rawQuery(query, null);
        }
        return cursor2;
    }



    void updateCredits(String name, String credits){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_credits, credits);
        db.update(TABLE_NAME1, cv, "name=?", new String[]{name});


    }





}
