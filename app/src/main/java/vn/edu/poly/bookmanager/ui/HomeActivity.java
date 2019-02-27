package vn.edu.poly.bookmanager.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import vn.edu.poly.bookmanager.R;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private ImageView imgnguoidung;
    private ImageView imghoadon;
    private ImageView imgsach;
    private ImageView imgtheloai;
    private ImageView imgthongke;
    private ImageView imgtop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initViews();
        initActions();
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void initActions() {
        imgnguoidung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, UserActivity.class);
                startActivity(intent);
                Toast.makeText(HomeActivity.this, "người dùng", Toast.LENGTH_SHORT).show();
            }
        });

        imghoadon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, BillActivity.class);
                startActivity(intent);
                Toast.makeText(HomeActivity.this, "hóa đơn", Toast.LENGTH_SHORT).show();
            }
        });

        imgsach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, BookActivity.class);
                startActivity(intent);
                Toast.makeText(HomeActivity.this, "book", Toast.LENGTH_SHORT).show();
            }
        });

        imgtheloai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, TypebookActivity.class);
                startActivity(intent);
                Toast.makeText(HomeActivity.this, "thể loại", Toast.LENGTH_SHORT).show();
            }
        });

        imgthongke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, ThongkeActivity.class);
                startActivity(intent);
                Toast.makeText(HomeActivity.this, "thống kê", Toast.LENGTH_SHORT).show();
            }
        });

        imgtop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(HomeActivity.this, TopbanchayActivity.class);
                startActivity(intent1);
            }
        });


    }

    private void initViews() {
        imgnguoidung = findViewById(R.id.imgnguoidung);
        imghoadon = findViewById(R.id.imghoadon);
        imgsach = findViewById(R.id.imgsach);
        imgtheloai = findViewById(R.id.imgtheloai);
        imgthongke = findViewById(R.id.imgthongke);
        imgtop = findViewById(R.id.imgtop);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.home, menu);
//        return true;
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_thoat) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

//        if (id == R.id.action_thoat) {
//
//            Toast.makeText(this, "Thoát", Toast.LENGTH_SHORT).show();
//            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
//            startActivity(intent);
//            finish();
//        }
//         else if (id == R.id.nav_caidat) {
//
//        } else if (id == R.id.nav_gioithieu) {
//
//        }
         if (id == R.id.nav_dangxuat) {
            Toast.makeText(this, "Ban da dang xuat!!!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
            finish();
        }
      else if (id == R.id.nav_thoat) {

            finish();
//            Toast.makeText(this, "Chuc ban mot ngay tot lanh <3", Toast.LENGTH_LONG).show();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
