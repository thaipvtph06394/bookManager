package vn.edu.poly.bookmanager.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import vn.edu.poly.bookmanager.Constant;
import vn.edu.poly.bookmanager.adapter.BookAdapter;
import vn.edu.poly.bookmanager.model.Book;
import vn.edu.poly.bookmanager.model.TypeBook;
import vn.edu.poly.bookmanager.sqlite.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class BookDAO implements Constant{

    public static BookAdapter adapter;
    private DatabaseHelper databaseHelper;

    public BookDAO(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }


    public void insertBook(Book book){
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(B_COLUMN_BOOK_ID, book.getMaSach());
        contentValues.put(B_COLUMN_TYPEBOOK_ID, book.getMaTheLoai());
        contentValues.put(B_COLUMN_NAMEBOOK, book.getTenSach());
        contentValues.put(B_COLUMN_AUTHOR, book.getTacGia());
        contentValues.put(B_COLUMN_NXB, book.getNXB());
        contentValues.put(B_COLUMN_PRICE, book.getGiaBan());
        contentValues.put(B_COLUMN_SOLUONG, book.getSoLuong());

        //tao cau lenh insert
        long id = db.insert(BOOK_TABLE, null, contentValues);

        if (Constant.isDEBUG) Log.e("insertBook", "insertBook ID : " + id);
        db.close();
    }


    public Book getBook(String book){
        Book book1 = null;

        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        //truyen vao ten bang, array bao gom ten cot, ten khao chinh, gia tri khoa chinh, cac tham so con lai la null

        Cursor cursor = db.query(BOOK_TABLE,
                new String[]{B_COLUMN_BOOK_ID, B_COLUMN_TYPEBOOK_ID, B_COLUMN_NAMEBOOK, B_COLUMN_AUTHOR, B_COLUMN_NXB, B_COLUMN_PRICE, B_COLUMN_SOLUONG},
                B_COLUMN_BOOK_ID + "=?",
                new String[]{String.valueOf(book)}, null, null, null);

        // moveToFirst : kiem tra xem cursor co chua du lieu khong, ham nay tra ve gia tri la true or false
        if (cursor != null && cursor.moveToFirst()) {

            String idbook = cursor.getString(cursor.getColumnIndex(B_COLUMN_BOOK_ID));

            String idtypebook = cursor.getString(cursor.getColumnIndex(B_COLUMN_TYPEBOOK_ID));

            String namebook = cursor.getString(cursor.getColumnIndex(B_COLUMN_NAMEBOOK));

            String author = cursor.getString(cursor.getColumnIndex(B_COLUMN_AUTHOR));

            String nxb = cursor.getString(cursor.getColumnIndex(B_COLUMN_NXB));

            long price = cursor.getLong(cursor.getColumnIndex(B_COLUMN_PRICE));

            Integer soluong = cursor.getInt(cursor.getColumnIndex(B_COLUMN_SOLUONG));

            //khoi tao user voi cac gia tri lay duoc
            book1 = new Book(idbook, idtypebook, namebook, author,nxb, price, soluong );
        }
        cursor.close();
        return book1;

    }


    public List<Book> getAllBook(){
        List<Book> bookList = new ArrayList<>();

        String SELECT_ALL_BOOK = "SELECT * FROM " + BOOK_TABLE;

        Log.e("getAllBook", SELECT_ALL_BOOK);

        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery(SELECT_ALL_BOOK, null);

        cursor.moveToFirst();

        if (cursor != null && cursor.getCount() > 0) {

            do {

                String idbook = cursor.getString(cursor.getColumnIndex(B_COLUMN_BOOK_ID));

                String idtypebook = cursor.getString(cursor.getColumnIndex(B_COLUMN_TYPEBOOK_ID));

                String namebook = cursor.getString(cursor.getColumnIndex(B_COLUMN_NAMEBOOK));

                String author = cursor.getString(cursor.getColumnIndex(B_COLUMN_AUTHOR));

                String nxb = cursor.getString(cursor.getColumnIndex(B_COLUMN_NXB));

                long price = cursor.getLong(cursor.getColumnIndex(B_COLUMN_PRICE));

                Integer soluong = cursor.getInt(cursor.getColumnIndex(B_COLUMN_SOLUONG));

                Book book1 = new Book();
                book1.setMaSach(idbook);
                book1.setMaTheLoai(idtypebook);
                book1.setTenSach(namebook);
                book1.setTacGia(author);
                book1.setNXB(nxb);
                book1.setGiaBan(price);
                book1.setSoLuong(soluong);

                //them typebook vao list<TypeBook>
                bookList.add(book1);

            } while (cursor.moveToNext());

        }else {

        }

        cursor.close();
        sqLiteDatabase.close();

        return bookList;

    }


    public long updateBook(Book book){
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(B_COLUMN_BOOK_ID, book.getMaSach());
        contentValues.put(B_COLUMN_TYPEBOOK_ID, book.getMaTheLoai());
        contentValues.put(B_COLUMN_NAMEBOOK, book.getTenSach());
        contentValues.put(B_COLUMN_AUTHOR, book.getTacGia());
        contentValues.put(B_COLUMN_NXB, book.getNXB());
        contentValues.put(B_COLUMN_PRICE, book.getGiaBan());
        contentValues.put(B_COLUMN_SOLUONG, book.getSoLuong());

        long result = db.update(BOOK_TABLE, contentValues, B_COLUMN_BOOK_ID + " = ?", new String[]{String.valueOf(book.getMaSach())});

        return result;
    }


    public long deleteBook(String bookId){
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        long result = db.delete(BOOK_TABLE, B_COLUMN_BOOK_ID + " = ?", new String[]{String.valueOf(bookId)});
        db.close();
        return result;
    }


}
