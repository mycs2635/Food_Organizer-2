package com.example.food_organizer;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class databaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME="users";
    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_TABLE="userInfo";
    public static final String KEY_ID="id";
    public static final String KEY_NAME="name";
    public static final String KEY_GENDER="gender";
    public static final String KEY_EMAIL="email";
    public static final String KEY_PHONE="phone";
    public static final String KEY_USERNAME="username";
    public static final String KEY_PASSWORD="password";
    public databaseHelper(@Nullable Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String userinfo="CREATE TABLE "+DATABASE_TABLE+"("+KEY_ID+" INTEGER PRIMARY KEY, "+KEY_NAME+" TEXT, "+KEY_GENDER+" TEXT, "+
                KEY_EMAIL+" TEXT, "+KEY_PHONE+" TEXT, "+KEY_USERNAME+" TEXT, "+KEY_PASSWORD+" TEXT "+" )";
        db.execSQL(userinfo);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }
    public void addUser(Customer c )
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(KEY_NAME,c.getName());
        cv.put(KEY_GENDER,c.getGender());
        cv.put(KEY_EMAIL,c.getMail());
        cv.put(KEY_PHONE,c.getPhone());
        cv.put(KEY_USERNAME,c.getUserName());
        cv.put(KEY_PASSWORD,c.getPassword());
        db.insert(DATABASE_TABLE,null,cv);
        db.close();
    }
}
