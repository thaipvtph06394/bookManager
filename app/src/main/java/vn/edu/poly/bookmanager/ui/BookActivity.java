package vn.edu.poly.bookmanager.ui;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import vn.edu.poly.bookmanager.R;
import vn.edu.poly.bookmanager.adapter.BookAdapter;
import vn.edu.poly.bookmanager.dao.BookDAO;
import vn.edu.poly.bookmanager.dao.TypeBookDAO;
import vn.edu.poly.bookmanager.model.Book;
import vn.edu.poly.bookmanager.model.TypeBook;
import vn.edu.poly.bookmanager.sqlite.DatabaseHelper;

public class BookActivity extends AppCompatActivity {

    private Toolbar toolbar;

    private RecyclerView recyclerView;
    private List<Book> bookList;
    private List<String> stringList = new ArrayList<>();
    private List<TypeBook> typeBookList;
    private BookAdapter adapter;
    private TypeBookDAO typeBookDAO;
    private ImageView imgback;
    private FloatingActionButton fabadd;
    private Spinner spmatheloai;
    private DatabaseHelper databaseHelper;
    private BookDAO bookDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        initViews();
        initActions();

        databaseHelper = new DatabaseHelper(this);
        bookDAO = new BookDAO(databaseHelper);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.recyclerview);
        bookList = new ArrayList<>();


        bookList = bookDAO.getAllBook();

        adapter = new BookAdapter(BookActivity.this, bookList, bookDAO);
        recyclerView.setAdapter(adapter);

        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);


    }

    private void initActions() {
        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BookActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });

        fabadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(BookActivity.this);
                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final View dialogView = inflater.inflate(R.layout.dialog_add_book, null);
                builder.setView(dialogView);
                final Dialog dialog = builder.show();

                spmatheloai = dialogView.findViewById(R.id.spmatheloai);
                typeBookDAO = new TypeBookDAO(databaseHelper);
                // Spinner Drop down elements
                typeBookList = typeBookDAO.getAllTypeBook();
                Log.e("TAG", "" + typeBookList.size());
                for (int i = 0; i < typeBookList.size(); i++) {
                    TypeBook typeBook = typeBookList.get(i);
                    stringList.add(typeBook.getName());
                }

                ArrayAdapter dataAdapter = new ArrayAdapter<>(BookActivity.this, android.R.layout.simple_spinner_item, stringList);

                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                spmatheloai.setAdapter(dataAdapter);

                spmatheloai.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        String chon = parent.getItemAtPosition(position).toString();
                        Toast.makeText(parent.getContext(), "You selected: " + chon, Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

                final EditText edtmasach;
                final EditText edttensach;
                final EditText edttacgia;
                final EditText edtnhaxuatban;
                final EditText edtgiaban;
                final EditText edtsoluong;

                edtmasach = dialogView.findViewById(R.id.edtmasach);
                edttensach = dialogView.findViewById(R.id.edttensach);
                edttacgia = dialogView.findViewById(R.id.edttacgia);
                edtnhaxuatban = dialogView.findViewById(R.id.edtnhaxuatban);
                edtgiaban = dialogView.findViewById(R.id.edtgiaban);
                edtsoluong = dialogView.findViewById(R.id.edtsoluong);


                Button add = dialogView.findViewById(R.id.btnthemsach);
                add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String masach = edtmasach.getText().toString().trim();
                        String tensach = edttensach.getText().toString().trim();
                        String tacgia = edttacgia.getText().toString().trim();
                        String nhaxuatban = edtnhaxuatban.getText().toString().trim();
                        String giaban = edtgiaban.getText().toString().trim();
                        String soluong = edtsoluong.getText().toString().trim();

                        if (masach.equals("")) {
                            edtmasach.setError(getString(R.string.notify_empty_name));
                            return;
                        }
                        if (tensach.equals("")) {
                            edttensach.setError(getString(R.string.notify_empty_name));
                            return;
                        }
                        if (tacgia.equals("")) {
                            edttacgia.setError(getString(R.string.notify_empty_name));
                            return;
                        }
                        if (nhaxuatban.equals("")) {
                            edtnhaxuatban.setError(getString(R.string.notify_empty_name));
                            return;
                        }
                        if (edtgiaban.getText().toString().equals("")) {
                            edtgiaban.setError(getString(R.string.notify_empty_name));
                            return;
                        }
                        if (edtsoluong.getText().toString().equals("")) {
                            edtsoluong.setError(getString(R.string.notify_empty_name));
                            return;
                        }


                        Book book = new Book();
                        book.setMaSach(masach);
                        book.setTenSach(tensach);
                        book.setTacGia(tacgia);
                        book.setNXB(nhaxuatban);
                        book.setGiaBan(Long.parseLong(giaban));
                        book.setSoLuong(Integer.parseInt(soluong));
                        book.setImganhbook(1);
                        book.setMaTheLoai("");


                        bookDAO.insertBook(book);
                        bookDAO.getAllBook();

                        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(edtmasach.getWindowToken(), 0);

                        Intent intent = new Intent(BookActivity.this, BookActivity.class);
                        Bundle b = new Bundle();
                        b.putString("MASACH", edtmasach.getText().toString());
                        intent.putExtras(b);
                        startActivity(intent);

                        Toast.makeText(BookActivity.this, "đã thêm", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });

                Button cancel = dialogView.findViewById(R.id.btncancel);
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

            }
        });

    }


    private void initViews() {
        imgback = findViewById(R.id.imgback);
        fabadd = findViewById(R.id.fabadd);
    }


//    //khi click vao menu hoac icon menu se goi pt nay de tao giao dien
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.home, menu);
//
//        return super.onCreateOptionsMenu(menu);
//    }
//
//    //xay dung phuong thuc de bat su kien nguoi dung click vao 1 item nao do
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.action_thoat:
//                Intent intent = new Intent(BookActivity.this, LoginActivity.class);
//                finish();
//                Toast.makeText(this, "Thoát", Toast.LENGTH_SHORT).show();
//                break;
//        }
//        return super.onOptionsItemSelected(item);
//    }


}
