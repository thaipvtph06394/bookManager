package vn.edu.poly.bookmanager.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import vn.edu.poly.bookmanager.model.User;
import vn.edu.poly.bookmanager.sqlite.DatabaseHelper;
import vn.edu.poly.bookmanager.Constant;

import java.util.ArrayList;
import java.util.List;

public class UserDAO implements Constant{

    private DatabaseHelper databaseHelper;

    public UserDAO(DatabaseHelper databaseHelper){
        this.databaseHelper = databaseHelper;
    }

    public void insertUser(User user) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_USERNAME, user.getUsername());
        contentValues.put(COLUMN_PASSWORK, user.getPasswork());
        contentValues.put(COLUMN_NAME, user.getName());
        contentValues.put(COLUMN_PHONE_NUMBER, user.getPhone());

        //tao cau lenh insert
        long id = db.insert(USER_TABLE, null, contentValues);

        if (Constant.isDEBUG) Log.e("insertUser", "insertUser ID : " + id);
        db.close();
    }


    public User getUser(String username) {

        User user = null;

        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        //truyen vao ten bang, array bao gom ten cot, ten khao chinh, gia tri khoa chinh, cac tham so con lai la null

        Cursor cursor = db.query(USER_TABLE,
                new String[]{ COLUMN_USERNAME, COLUMN_PASSWORK, COLUMN_NAME, COLUMN_PHONE_NUMBER},
                COLUMN_USERNAME + "=?",
                new String[]{String.valueOf(username)}, null, null, null);

        // moveToFirst : kiem tra xem cursor co chua du lieu khong, ham nay tra ve gia tri la true or false
        if (cursor != null && cursor.moveToFirst()) {

            String user_name = cursor.getString(cursor.getColumnIndex(COLUMN_USERNAME));

            String passwork = cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORK));

            String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));

            String phoneNumber = cursor.getString(cursor.getColumnIndex(COLUMN_PHONE_NUMBER));

            //khoi tao user voi cac gia tri lay duoc
            user = new User( user_name, passwork, name, phoneNumber);
        }
        cursor.close();
        return user;

    }


    public List<User> getAllUser() {

        List<User> userList = new ArrayList<>();

        String SELECT_ALL_USER = "SELECT * FROM " + USER_TABLE;

        Log.e("getAllUsers", SELECT_ALL_USER);

        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery(SELECT_ALL_USER, null);

        cursor.moveToFirst();

        do {

            String user_name = cursor.getString(cursor.getColumnIndex(COLUMN_USERNAME));

            String passwork = cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORK));

            String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));

            String phoneNumber = cursor.getString(cursor.getColumnIndex(COLUMN_PHONE_NUMBER));

            User user = new User();
            user.setUsername(user_name);
            user.setPasswork(passwork);
            user.setName(name);
            user.setPhone(phoneNumber);

            //them user vao list<User>
            userList.add(user);

        }while (cursor.moveToNext());

        cursor.close();
        sqLiteDatabase.close();

        return userList;

    }

    public int UpdateUser(User user) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUMN_PASSWORK, user.getPasswork());
        values.put(COLUMN_NAME, user.getName());
        values.put(COLUMN_PHONE_NUMBER, user.getPhone());

        return db.update(USER_TABLE, values, COLUMN_USERNAME + " = ?", new String[]{user.getUsername()});

    }

    public void deleteUser(String username) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        db.delete(USER_TABLE, COLUMN_USERNAME + " = ?", new String[]{username});
        db.close();

    }

}
