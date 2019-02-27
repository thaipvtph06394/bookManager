package vn.edu.poly.bookmanager;

public interface Constant {

    public final static boolean isDEBUG = true;


    /*bang user*/
     String USER_TABLE = "users";

    //ten cot
     String COLUMN_USERNAME = "Username";
     String COLUMN_PASSWORK = "Passswork";
     String COLUMN_NAME = "Name";
     String COLUMN_PHONE_NUMBER = "Phone_number";

    //cau lenh tao bang
     String CREATE_USER_TABLE = "CREATE TABLE " + USER_TABLE + "(" +
            COLUMN_USERNAME + " NVARCHAR PRIMARY KEY, " +
            COLUMN_PASSWORK + " NVARCHAR, " +
            COLUMN_NAME + " NVARCHAR, " +
            COLUMN_PHONE_NUMBER + " NVARCHAR " +
            ")";


    /*Bang hoa don*/
    String BILL_TABLE = "hoadon";

    String HD_COLUMN_BILL_ID = "Mahoadon";
    String HD_COLUMN_DATE = "Ngaymua";

    String CREATE_BILL_TABLE = "CREATE TABLE " + BILL_TABLE + "(" +
            HD_COLUMN_BILL_ID + " NCHAR(7) PRIMARY KEY NOT NULL, " +
            HD_COLUMN_DATE + " LONG NOT NULL " +
            ")";


    /*Bang hoa don chi tiet*/
    String HOADONCHITIET_TABLE = "hoadonchitiet";

    String HDCT_COLUMN_HDCT_ID = "Mahdct";
    String HDCT_COLUMN_BILL_ID = "Mahoadon";
    String HDCT_COLUMN_BOOK_ID = "Masach";
    String HDCT_COLUMN_SOLUONG = "Soluong";

    String CREATE_HOADONCHITIET_TABLE = "CREATE TABLE " + HOADONCHITIET_TABLE + "(" +
            "" + HDCT_COLUMN_HDCT_ID + " NCHAR PRIMARY KEY, " +
            "" + HDCT_COLUMN_BILL_ID + " NCHAR, " +
            "" + HDCT_COLUMN_BOOK_ID + " NCHAR, " +
            "" + HDCT_COLUMN_SOLUONG + " INT NOT NULL " +
            ")";




    /*Bang book*/
    String BOOK_TABLE = "Books";

    String B_COLUMN_BOOK_ID = "Masach";
    String B_COLUMN_TYPEBOOK_ID = "Matheloai";
    String B_COLUMN_NAMEBOOK = "Tensach";
    String B_COLUMN_AUTHOR = "Tacgia";
    String B_COLUMN_NXB = "Nhaxuatban";
    String B_COLUMN_PRICE = "Giaban";
    String B_COLUMN_SOLUONG = "Soluong";

    String CREATE_BOOK_TABLE = "CREATE TABLE " + BOOK_TABLE + "(" +
            "" + B_COLUMN_BOOK_ID + " NCHAR(5) PRIMARY KEY NOT NULL, " +
            "" + B_COLUMN_TYPEBOOK_ID + " NCHAR(50) NOT NULL, " +
            "" + B_COLUMN_AUTHOR + " NVARCHAR(50), " +
            "" + B_COLUMN_NAMEBOOK + " NVARCHAR, " +
            "" + B_COLUMN_NXB + " NVARCHAR(50), " +
            "" + B_COLUMN_PRICE + " LONG NOT NULL, " +
            "" + B_COLUMN_SOLUONG + " INT NOT NULL " +
            ")";




    /*Bang book type*/
    String TYPE_BOOK_TABLE = "TypeBooks";

    String TB_COLUMN_TYPEBOOK_ID = "Matheloai";
    String TB_COLUMN_TYPE_NAME = "Typename";
    String TB_COLUMN_DESCRIPTION = "Description";
    String TB_COLUMN_POSITION = "Position";

    String CREATE_TYPE_BOOK_TABLE = "CREATE TABLE " + TYPE_BOOK_TABLE + "(" +
            "" + TB_COLUMN_TYPEBOOK_ID + " CHAR(5) PRIMARY KEY NOT NULL, " +
            "" + TB_COLUMN_TYPE_NAME + " NVARCHAR(50) NOT NULL, " +
            "" + TB_COLUMN_DESCRIPTION + " NVARCHAR(255), " +
            "" + TB_COLUMN_POSITION + " INT " +
            ")";



}
