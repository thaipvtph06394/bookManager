package vn.edu.poly.bookmanager.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import vn.edu.poly.bookmanager.R;
import vn.edu.poly.bookmanager.adapter.HoadonchitietAdapter;
import vn.edu.poly.bookmanager.dao.HoadonchitietDAO;
import vn.edu.poly.bookmanager.model.Hoadonchitiet;
import vn.edu.poly.bookmanager.sqlite.DatabaseHelper;

public class HoadonchitietActivity extends AppCompatActivity {

    private Toolbar toolbar;

    private RecyclerView recyclerView;
    private List<Hoadonchitiet> hoadonchitietList;
    private HoadonchitietAdapter adapter;

    private ImageView imgback;

    private DatabaseHelper databaseHelper;
    private HoadonchitietDAO hoadonchitietDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoadonchitiet);
        initViews();
        initActions();

        databaseHelper = new DatabaseHelper(this);
        hoadonchitietDAO = new HoadonchitietDAO(databaseHelper);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.recyclerview);
        hoadonchitietList = new ArrayList<>();



        Bundle bundle = getIntent().getExtras();
        String title = bundle.getString("MAHOADON", "5");

        Log.e("title", title + "");


        adapter = new HoadonchitietAdapter(HoadonchitietActivity.this, hoadonchitietList, hoadonchitietDAO);
        recyclerView.setAdapter(adapter);

        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);

    }

    private void initActions() {
        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HoadonchitietActivity.this, ThanhtoanActivity.class);
                startActivity(intent);
                finish();
            }
        });



    }

    private void initViews() {
        imgback =  findViewById(R.id.imgback);
    }


    //khi click vao menu hoac icon menu se goi pt nay de tao giao dien
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
//                Intent intent = new Intent(HoadonchitietActivity.this, LoginActivity.class);
//                finish();
//                Toast.makeText(this, "Thoát", Toast.LENGTH_SHORT).show();
//                break;
//        }
//        return super.onOptionsItemSelected(item);
//    }

}
