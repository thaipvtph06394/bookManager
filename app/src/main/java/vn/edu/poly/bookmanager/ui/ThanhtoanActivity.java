package vn.edu.poly.bookmanager.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import vn.edu.poly.bookmanager.R;
import vn.edu.poly.bookmanager.dao.BillDAO;
import vn.edu.poly.bookmanager.dao.BookDAO;
import vn.edu.poly.bookmanager.dao.HoadonchitietDAO;
import vn.edu.poly.bookmanager.dao.TypeBookDAO;
import vn.edu.poly.bookmanager.model.Book;
import vn.edu.poly.bookmanager.model.TypeBook;
import vn.edu.poly.bookmanager.sqlite.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class ThanhtoanActivity extends AppCompatActivity {

    private TextView tvmahoadon;
    private Spinner spmasach;
    private EditText edtsoluong;
    private Button btnthanhtoan;

    private Toolbar toolbar;
    private ImageView imgback;

    private DatabaseHelper databaseHelper;
    private HoadonchitietDAO hoadonchitietDAO;
    private BillDAO billDAO;
    private BookDAO bookDAO;

    private List<Book> bookList=new ArrayList<>();
    private List<String> stringList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanhtoan);
        initViews();
        initActions();

        databaseHelper = new DatabaseHelper(this);
        hoadonchitietDAO = new HoadonchitietDAO(databaseHelper);
        billDAO = new BillDAO(databaseHelper);
        bookDAO = new BookDAO(databaseHelper);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        loadSpinnerData();

    }

    private void initViews() {
        tvmahoadon = findViewById(R.id.tvmahoadon);
        spmasach = findViewById(R.id.spmasach);
        edtsoluong = findViewById(R.id.edtsoluong);
        btnthanhtoan = findViewById(R.id.btnthanhtoan);
        imgback =  findViewById(R.id.imgback);
    }


    private void initActions() {

        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ThanhtoanActivity.this, BillActivity.class);
                startActivity(intent);
                finish();
            }
        });

        Intent intent = getIntent();
        Bundle bundle = getIntent().getExtras();
        tvmahoadon.setText(intent.getStringExtra("MAHOADON"));



        spmasach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String chon = parent.getItemAtPosition(position).toString();
                Toast.makeText(parent.getContext(), "You selected: " + chon, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }


    private void loadSpinnerData() {
        databaseHelper = new DatabaseHelper(getApplicationContext());
        hoadonchitietDAO = new HoadonchitietDAO(databaseHelper);
        billDAO = new BillDAO(databaseHelper);
        bookDAO = new BookDAO(databaseHelper);
        bookList = bookDAO.getAllBook();

        Log.e("TAG", "" + bookList.size());
        for (int i = 0; i < bookList.size(); i++) {
            Book book = bookList.get(i);
            stringList.add(book.getMaSach());
        }
        // Creating adapter for spinner
        ArrayAdapter dataAdapter = new ArrayAdapter<>(ThanhtoanActivity.this, android.R.layout.simple_spinner_item, stringList);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        Log.e("dataAdapter", "dataAdapter");
        spmasach.setAdapter(dataAdapter);
    }


}
