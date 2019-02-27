package vn.edu.poly.bookmanager.dao;

import vn.edu.poly.bookmanager.Constant;

public class ThongkeDAO implements Constant{
//    private DatabaseHelper databaseHelper;
//
//    public ThongkeDAO(DatabaseHelper databaseHelper) {
//        this.databaseHelper = databaseHelper;
//    }
//
//
//    public void testSUM() {
//
//        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
//
//        String testSUM = "SELECT SUM(tongtien) from (SELECT SUM(Books.giaBia * BillDetail.SoLuongMua) as 'tongtien' " +
//                "" + "from " + BILL_TABLE +
//                "" + " INNER JOIN " + HOADONCHITIET_TABLE + " on " + " Bill.MaHoaDon = BillDetail.MaHoaDon  " +
//                "" + " INNER JOIN " + BOOK_TABLE + " on " + " Books.MaSach = BillDetail.MaSach  " +
//                "" + " WHERE strftime(\"%Y-%m-%d\", Bill.NgayMua / 1000, 'unixepoch') = strftime(\"%Y-%m-%d\",'now') " +
//                "" + " GROUP BY BillDetail.MaSach " +
//                ")";
//
//
//        Cursor cursor = sqLiteDatabase.rawQuery(testSUM, null);
//        if (cursor != null) {
//            if (cursor.getCount() > 0) {
//                cursor.moveToFirst();
//                double sum = cursor.getDouble(0);
//                Log.e("SUM", sum + " " + cursor.getCount());
//            }
//        }
//
//    }
//
//
//    public void testDATENow() {
//        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
//        String test = "SELECT * FROM " + BILL_TABLE + " WHERE strftime(\"%Y-%m-%d\", NgayMua / 1000, 'unixepoch' )   = strftime(\"%Y-%m-%d\",'now')";
//        String test2 = "SELECT strftime(\"%Y-%m-%d\", NgayMua / 1000 ,'unixepoch' )  from " + BILL_TABLE;
//        Cursor cursor = sqLiteDatabase.rawQuery(test, null);
//        if (cursor != null) {
//            if (cursor.getCount() > 0) {
//                cursor.moveToFirst();
//
//                String date = cursor.getString(0);
//                Log.e("DATE", date);
//            }
//        }
//
//    }
//
//    public long getStatisticsByDayCach1(long day) {
//        long result = -1;
//        List<Bill> bills = new ArrayList<>();
//
//        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
//
//        String SELECT_ = "SELECT * FROM " + BILL_TABLE;
//
//        Cursor cursor = sqLiteDatabase.rawQuery(SELECT_, null);
//
//        if (cursor != null) {
//            if (cursor.getCount() > 0) {
//                do {
//                    String id = cursor.getString(cursor.getColumnIndex(HD_COLUMN_BILL_ID));
//                    long date = cursor.getLong(cursor.getColumnIndex(HD_COLUMN_DATE));
//
//                    Bill bill = new Bill(id, date);
//
//                    bills.add(bill);
//
//                } while (cursor.moveToNext());
//
//            }
//        }
//
//        // loai bo cac ngay
//        for (int i = 0; i < bills.size(); i++) {
//
//            long date = bills.get(i).datehoadon;
//            if (date != day) {
//                bills.remove(i);
//            }
//
//        }
//
//        List<Hoadonchitiet> x = new ArrayList<>();
//        for (int i = 0; i < bills.size(); i++) {
//            List<Hoadonchitiet> billDetails_ =
//                    new HoadonchitietDAO(databaseHelper).getAllHoadonchitiet(bills.get(i).idhoadon);
//
//            // lay toan bo danh sach Bill Detail theo Bill ID
//            x.addAll(billDetails_);
//
//        }
//
//        for (int i = 0; i < x.size(); i++) {
//
//            int quality = x.get(i).soluong;
//            long price = new BookDAO(databaseHelper).getBook(x.get(i).bo).price;
//
//            long sum_ = quality * price;
//
//            result = result + sum_;
//
//        }
//
//        return result;
//    }
//
//    // example day = 2018-10-09   YY-MM-DD
//
//    public long getStatisticsByDayCach2(String day) {
//        long result = -1;
//        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
//
//        String SELECT_STATISTICS_BY_DAY = "";
//
//        return result;
//
//    }
//
//
//    // format month : %Y-%m  2018-10
//
//    public long getStatisticsByMonth(String month) {
//        long result = -1;
//
//        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
//
//
//        String SELECT_STATISTICS = "SELECT * FROM " + TABLE_BILL + " WHERE strftime('%Y-%m', " + B_DATE + ")  = '" + month + "'";
//        Cursor cursor = sqLiteDatabase.rawQuery(SELECT_STATISTICS, null);
//        if (cursor != null) {
//            if (cursor.getCount() > 0) {
//                Log.e("SIZE", cursor.getCount() + "");
//                cursor.moveToFirst();
//                do {
//                    String text = cursor.getString(0);
//
//                    Log.e("text",
//                            text);
//
//                } while (cursor.moveToNext());
//            } else {
//                Log.e("SIZE=0", "000");
//            }
//        }
//
//        return result;
//    }
//
//
//    // example year = "2018"
//
//    public long getStatisticsByYear(String year) {
//        long result = -1;
//
//        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
//
//
//        String SELECT_STATISTICS = "SELECT * FROM " + TABLE_BILL + " WHERE strftime('%Y', " + B_DATE + "/ 1000, 'unixepoch')  = '" + year + "'";
//        Cursor cursor = sqLiteDatabase.rawQuery(SELECT_STATISTICS, null);
//        if (cursor != null) {
//            if (cursor.getCount() > 0) {
//                Log.e("SIZE", cursor.getCount() + "");
//                cursor.moveToFirst();
//                do {
//                    String text = cursor.getString(0);
//
//                    Log.e("text",
//                            text);
//
//                } while (cursor.moveToNext());
//            } else {
//                Log.e("SIZE=0", "000");
//            }
//        }
//
//
//        return result;
//    }
//
//
//    // YY-MM-DD
//
//
//    public double getStatisticsByDate(String dateFormat) {
//
//        double result = -1;
//
//        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
//
//        String QUERY_DAY = "SELECT SUM(tongtien) FROM (" +
//                "" + "SELECT SUM(Books.giaBia * BillDetail.SoluongMua) as 'tongtien'" +
//                "" + " FROM " + TABLE_BILL +
//                "" + " INNER JOIN " + TABLE_BOOK + " ON " + " Books.MaSach = BillDetail.MaSach " +
//                "" + " INNER JOIN " + TABLE_BILL_DETAIL + " ON " + " Bill.MaHoaDon = BillDetail.MaHoaDon " +
//                "" + " WHERE  strftime(" + dateFormat + ", Bill.NgayMua / 1000 , 'unixepoch') = strftime(" + dateFormat + ",'now') " +
//                "" + " GROUP BY BillDetail.MaSach" +
//                ")";
//
//        Log.e("QUERY_DAY", QUERY_DAY);
//
//        Cursor cursor = sqLiteDatabase.rawQuery(QUERY_DAY, null);
//        if (cursor != null && cursor.getCount() > 0) {
//            cursor.moveToFirst();
//
//            result = cursor.getDouble(0);
//        }
//        return result;
}
