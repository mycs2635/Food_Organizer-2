package com.example.food_organizer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

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
    public databaseHelper(Context context) {
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
        db.execSQL("DROP TABLE IF EXISTS "+DATABASE_TABLE);
        onCreate(db);
    }
    public boolean addUser(Customer c )
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(KEY_NAME,c.getName());
        cv.put(KEY_GENDER,c.getGender());
        cv.put(KEY_EMAIL,c.getMail());
        cv.put(KEY_PHONE,c.getPhone());
        cv.put(KEY_USERNAME,c.getUserName());
        cv.put(KEY_PASSWORD,c.getPassword());
        long result = db.insert(DATABASE_TABLE,null,cv);
        return result != -1;
        //db.close();
    }
    public boolean checkEmail(String mail){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * from userInfo where email = ?", new String[] {mail});
        return cursor.getCount() > 0;
    }

    public boolean checkPassword(String mail, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * from userInfo where email = ? and password = ?", new String[] {mail,password});
        return cursor.getCount() > 0;
    }

//    public Customer getUser(String mail){
//        SQLiteDatabase db=this.getReadableDatabase();
//        ContentValues cv=new ContentValues();
//        Cursor cursor = db.query(DATABASE_TABLE,
//                new String[] {KEY_ID,KEY_NAME,KEY_GENDER,KEY_EMAIL,KEY_PHONE,KEY_USERNAME,KEY_PASSWORD},
//                KEY_EMAIL+"=?",
//                new String[] {mail},
//                null,
//                null,
//                null,
//                null);
//        if(cursor!=null){
//            cursor.moveToFirst();
//
//        }
//      //  Cursor cursor = db.rawQuery("SELECT * from users where ")
//    }

    public List<Customer> getAllUsers(){
        List<Customer> allUsers = new ArrayList<>();
        String queryString = "SELECT * FROM "+DATABASE_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString,null);
        if(cursor.moveToFirst()){
            do{
                int customerID = cursor.getInt(0);
                String customerName = cursor.getString(1);
                String customerGender = cursor.getString(2);
                String customerEmail = cursor.getString(3);
                String customerPhone = cursor.getString(4);
                String customerUsername = cursor.getString(5);
                String customerPassword = cursor.getString(6);
                Customer user = new Customer(customerName,
                        customerGender,
                        customerEmail,
                        customerPhone,
                        customerUsername,
                        customerPassword
                );

                allUsers.add(user);
            }while (cursor.moveToNext());
        }
        else{
            // do not add anything to list
        }
        cursor.close();
        db.close();
        return allUsers;
    }
}
