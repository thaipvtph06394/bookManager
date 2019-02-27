package vn.edu.poly.bookmanager.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import vn.edu.poly.bookmanager.R;
import vn.edu.poly.bookmanager.adapter.TopbanchayAdapter;
import vn.edu.poly.bookmanager.model.Topbanchay;

public class TopbanchayActivity extends AppCompatActivity {

    private Toolbar toolbar;

    private RecyclerView recyclerView;
    private List<Topbanchay> topbanchays;
    private TopbanchayAdapter adapter;

    private ImageView imgback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topbanchay);

        initViews();
        initActions();

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.recyclerview);
        topbanchays = new ArrayList<>();

        for (int i=0; i<10; i++){
            topbanchays.add(new Topbanchay( "MSS" + i , "số lượng: 1"+i));
        }

        adapter = new TopbanchayAdapter(TopbanchayActivity.this, topbanchays);
        recyclerView.setAdapter(adapter);

        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);

    }

    private void initActions() {
        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
//                Intent intent = new Intent(TopbanchayActivity.this, LoginActivity.class);
//                finish();
//                Toast.makeText(this, "Thoát", Toast.LENGTH_SHORT).show();
//                break;
//        }
//        return super.onOptionsItemSelected(item);
//    }


}
