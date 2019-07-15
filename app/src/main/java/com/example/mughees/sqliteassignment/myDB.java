package com.example.mughees.sqliteassignment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class myDB extends SQLiteOpenHelper {
    public static final int dbversion = 1;

    public myDB(Context context) {
        super(context, "Login.db", null, dbversion);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table user(email text Primary key,password text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists user");
    }
    //Register user
    public boolean insert(String email,String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email",email);
        contentValues.put("password",password);
        long ins = db.insert("user",null,contentValues);
        if (ins==-1)return false;
        else return true;
    }

    //Check mail if already exist
    public boolean checkmail(String email){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from user where email=?",new String []{email});
        if (cursor.getCount()>0)return false;
        else return true;
    }
    //Login user
    public  boolean loginemailpassword(String email,String password){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from user where email=? and password=? ",new String[]{email,password});
        if (cursor.getCount()>0)return true;
        else return false;
    }
    public boolean deleteuser(String email){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("user",  "email=?", new String[]{email}) > 0;
    }
    //update user
    public boolean updateuser(String email ,String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues  contentValues=  new ContentValues();
        contentValues.put("email",email);
        contentValues.put("password",password);
        db.update("user",contentValues,"email=? ",new String[]{email});
        return true;
    }
    public Cursor allData(){
        SQLiteDatabase db =this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user",null);
        return cursor;
    }
}
