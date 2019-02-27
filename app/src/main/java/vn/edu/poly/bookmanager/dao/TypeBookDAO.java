package vn.edu.poly.bookmanager.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import vn.edu.poly.bookmanager.Constant;
import vn.edu.poly.bookmanager.model.TypeBook;
import vn.edu.poly.bookmanager.model.User;
import vn.edu.poly.bookmanager.sqlite.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class TypeBookDAO implements Constant{

    private DatabaseHelper databaseHelper;

    public TypeBookDAO(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }

    public void insertTypeBook(TypeBook typeBook){
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(TB_COLUMN_TYPEBOOK_ID, typeBook.getId());
        contentValues.put(TB_COLUMN_TYPE_NAME, typeBook.getName());
        contentValues.put(TB_COLUMN_DESCRIPTION, typeBook.getDescription());
        contentValues.put(TB_COLUMN_POSITION, typeBook.getPosition());

        //tao cau lenh insert
        long id = db.insert(TYPE_BOOK_TABLE, null, contentValues);

        if (Constant.isDEBUG) Log.e("insertTypeBook", "insertTypeBook ID : " + id);
        db.close();
    }


    public long deleteTypeBook(String typeId){
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        long result = db.delete(TYPE_BOOK_TABLE, TB_COLUMN_TYPEBOOK_ID + " = ?", new String[]{String.valueOf(typeId)});
        db.close();
        return result;
    }

    public long updateTypeBook(TypeBook typeBook){
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(TB_COLUMN_TYPEBOOK_ID, typeBook.getId());
        contentValues.put(TB_COLUMN_TYPE_NAME, typeBook.getName());
        contentValues.put(TB_COLUMN_DESCRIPTION, typeBook.getDescription());
        contentValues.put(TB_COLUMN_POSITION, typeBook.getPosition());

        long result = db.update(TYPE_BOOK_TABLE, contentValues, TB_COLUMN_TYPEBOOK_ID + " = ?", new String[]{String.valueOf(typeBook.getId())});

        return result;
    }



    public TypeBook getTypeBook(String typeBook){
        TypeBook typeBook1 = null;

        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        //truyen vao ten bang, array bao gom ten cot, ten khao chinh, gia tri khoa chinh, cac tham so con lai la null

        Cursor cursor = db.query(TYPE_BOOK_TABLE,
                new String[]{TB_COLUMN_TYPEBOOK_ID, TB_COLUMN_TYPE_NAME, TB_COLUMN_DESCRIPTION, TB_COLUMN_POSITION},
                TB_COLUMN_TYPEBOOK_ID + "=?",
                new String[]{String.valueOf(typeBook)}, null, null, null);

        // moveToFirst : kiem tra xem cursor co chua du lieu khong, ham nay tra ve gia tri la true or false
        if (cursor != null && cursor.moveToFirst()) {

            String id = cursor.getString(cursor.getColumnIndex(TB_COLUMN_TYPEBOOK_ID));

            String name = cursor.getString(cursor.getColumnIndex(TB_COLUMN_TYPE_NAME));

            String description = cursor.getString(cursor.getColumnIndex(TB_COLUMN_DESCRIPTION));

            Integer position = cursor.getInt(cursor.getColumnIndex(TB_COLUMN_POSITION));

            //khoi tao user voi cac gia tri lay duoc
            typeBook1 = new TypeBook(id, name, description, position);
        }
        cursor.close();
        return typeBook1;

    }

    public List<TypeBook> getAllTypeBook(){
        List<TypeBook> typeBookList = new ArrayList<>();

        String SELECT_ALL_TYPEBOOK = "SELECT * FROM " + TYPE_BOOK_TABLE;

        Log.e("getAllTypeBook", SELECT_ALL_TYPEBOOK);

        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery(SELECT_ALL_TYPEBOOK, null);

        cursor.moveToFirst();

        if (cursor != null && cursor.getCount() > 0){
            do {

                String id = cursor.getString(cursor.getColumnIndex(TB_COLUMN_TYPEBOOK_ID));

                String name = cursor.getString(cursor.getColumnIndex(TB_COLUMN_TYPE_NAME));

                String description = cursor.getString(cursor.getColumnIndex(TB_COLUMN_DESCRIPTION));

                Integer position = cursor.getInt(cursor.getColumnIndex(TB_COLUMN_POSITION));

                TypeBook typeBook1 = new TypeBook();
                typeBook1.setId(id);
                typeBook1.setName(name);
                typeBook1.setDescription(description);
                typeBook1.setPosition(position);

                //them typebook vao list<TypeBook>
                typeBookList.add(typeBook1);

            }while (cursor.moveToNext());
        }else {

        }



        cursor.close();
        sqLiteDatabase.close();

        return typeBookList;

    }


}
