package vn.edu.poly.bookmanager.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import vn.edu.poly.bookmanager.Constant;
import vn.edu.poly.bookmanager.model.Bill;
import vn.edu.poly.bookmanager.model.Hoadonchitiet;
import vn.edu.poly.bookmanager.sqlite.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class HoadonchitietDAO implements Constant{

    private DatabaseHelper databaseHelper;

    public HoadonchitietDAO(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }

    public void insertHoadonchitiet(Hoadonchitiet hoadonchitiet){
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(HDCT_COLUMN_HDCT_ID, hoadonchitiet.getMahoadonchitiet());
        contentValues.put(HDCT_COLUMN_BILL_ID, hoadonchitiet.getMahoadon());
        contentValues.put(HDCT_COLUMN_BOOK_ID, hoadonchitiet.getMasach());
        contentValues.put(HDCT_COLUMN_SOLUONG, hoadonchitiet.getMasach());

        db.insert(HOADONCHITIET_TABLE, null, contentValues);
    }


    public Hoadonchitiet getHoadonchitiet(String hoadonchitiet){
        Hoadonchitiet hoadonchitiet1 = null;

        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        Cursor cursor = db.query(HOADONCHITIET_TABLE,
                new String[]{HDCT_COLUMN_HDCT_ID, HDCT_COLUMN_BILL_ID, HDCT_COLUMN_BOOK_ID, HDCT_COLUMN_SOLUONG},
                HDCT_COLUMN_HDCT_ID + "=?",
                new String[]{String.valueOf(hoadonchitiet)},
                null, null, null );

        if (cursor != null && cursor.moveToFirst()){

            String maHDCT = cursor.getString(cursor.getColumnIndex(HDCT_COLUMN_HDCT_ID));
            String mahoadon = cursor.getString(cursor.getColumnIndex(HDCT_COLUMN_BILL_ID));
            String masach = cursor.getString(cursor.getColumnIndex(HDCT_COLUMN_BOOK_ID));
            int soluong = cursor.getInt(cursor.getColumnIndex(HDCT_COLUMN_SOLUONG));

            hoadonchitiet1 = new Hoadonchitiet(maHDCT, mahoadon, masach, soluong);
        }

        cursor.close();
        return hoadonchitiet1;
    }

    public List<Hoadonchitiet> getAllHoadonchitiet(){
        List<Hoadonchitiet> hoadonchitietList = new ArrayList<>();

        String SELECT_ALL_HOADONCHITIET = "SELECT * FROM " + HOADONCHITIET_TABLE;

        Log.e("getAllHoadonchitiet", SELECT_ALL_HOADONCHITIET);

        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        Cursor cursor = db.rawQuery(SELECT_ALL_HOADONCHITIET, null);

        cursor.moveToFirst();

        if (cursor != null && cursor.getCount() > 0){
            do {
                String maHDCT = cursor.getString(cursor.getColumnIndex(HDCT_COLUMN_HDCT_ID));
                String mahoadon = cursor.getString(cursor.getColumnIndex(HDCT_COLUMN_BILL_ID));
                String masach = cursor.getString(cursor.getColumnIndex(HDCT_COLUMN_BOOK_ID));
                int soluong = cursor.getInt(cursor.getColumnIndex(HDCT_COLUMN_SOLUONG));

                Hoadonchitiet hoadonchitiet1 = new Hoadonchitiet();
                hoadonchitiet1.setMahoadonchitiet(maHDCT);
                hoadonchitiet1.setMahoadon(mahoadon);
                hoadonchitiet1.setMasach(masach);
                hoadonchitiet1.setSoluong(soluong);

                //them typebook vao list<TypeBook>
                hoadonchitietList.add(hoadonchitiet1);

            }while (cursor.moveToNext());
        }



        cursor.close();
        db.close();

        return hoadonchitietList;
    }


    public long updateHoadonchitiet(Hoadonchitiet hoadonchitiet){
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(HDCT_COLUMN_BILL_ID, hoadonchitiet.getMahoadon());
        contentValues.put(HDCT_COLUMN_BOOK_ID, hoadonchitiet.getMasach());
        contentValues.put(HDCT_COLUMN_SOLUONG, hoadonchitiet.getMasach());

        long result = db.update(HOADONCHITIET_TABLE, contentValues,
                HDCT_COLUMN_HDCT_ID + "=?", new String[]{hoadonchitiet.getMahoadonchitiet()});


        db.close();
        return result;
    }


    public long deleteHoadonchitiet(String hoadonchitietID){
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        long result = db.delete(HOADONCHITIET_TABLE, HDCT_COLUMN_HDCT_ID + "=?", new String[]{hoadonchitietID});

        db.close();
        return result;

    }



}
