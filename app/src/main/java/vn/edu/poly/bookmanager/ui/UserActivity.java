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
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import vn.edu.poly.bookmanager.R;
import vn.edu.poly.bookmanager.adapter.UserAdapter;
import vn.edu.poly.bookmanager.dao.UserDAO;
import vn.edu.poly.bookmanager.model.User;
import vn.edu.poly.bookmanager.sqlite.DatabaseHelper;

public class UserActivity extends AppCompatActivity {

    private Toolbar toolbar;

    private RecyclerView recyclerView;
    private List<User> userList;
    private UserAdapter adapter;

    private ImageView imgback;
    private FloatingActionButton fabadd;

    private DatabaseHelper databaseHelper;
    private UserDAO userDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        initViews();
        initActions();


        databaseHelper = new DatabaseHelper(this);
        userDAO = new UserDAO(databaseHelper);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.recyclerview);
        userList = new ArrayList<>();


        userList = userDAO.getAllUser();

        adapter = new UserAdapter(UserActivity.this,userList, userDAO);
        recyclerView.setAdapter(adapter);

        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);



    }

    private void initActions() {
        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });

        fabadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(UserActivity.this);
                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View dialogView = inflater.inflate(R.layout.dialog_add_user, null);
                builder.setView(dialogView);
                final Dialog dialog = builder.show();

                Button add = dialogView.findViewById(R.id.btnadduser);
                add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        final EditText edtusername;
                        final EditText edtpassword;
                        final EditText edtname;
                        final EditText edtphone;

                        edtusername = dialog.findViewById(R.id.edtusername);
                        edtpassword = dialog.findViewById(R.id.edtpassword);
                        edtname = dialog.findViewById(R.id.edtname);
                        edtphone = dialog.findViewById(R.id.edtphone);


                        String username = edtusername.getText().toString().trim();
                        String password = edtpassword.getText().toString().trim();
                        String name = edtname.getText().toString().trim();
                        String phone = edtphone.getText().toString().trim();

                        if (username.equals("")){
                            edtusername.setError(getString(R.string.notify_empty_name));
                            return;
                        }
                        if (password.equals("")){
                            edtpassword.setError(getString(R.string.notify_empty_name));
                            return;
                        }
                        if (password.length() < 6){
                            edtpassword.setError(getString(R.string.notify_empty_pass_work));
                            return;
                        }
                        if (name.equals("")){
                            edtname.setError(getString(R.string.notify_empty_name));
                            return;
                        }
                        if (phone.equals("")){
                            edtphone.setError(getString(R.string.notify_empty_name));
                            return;
                        }

                        User user = new User();
                        user.setUsername(username);
                        user.setPasswork(password);
                        user.setName(name);
                        user.setPhone(phone);

                        userDAO.insertUser(user);
                        Intent it = new Intent(UserActivity.this,UserActivity.class);
                        startActivity(it);
                        userDAO.getAllUser();

                        Toast.makeText(UserActivity.this, "đã thêm", Toast.LENGTH_SHORT).show();
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
////    @Override
////    public boolean onOptionsItemSelected(MenuItem item) {
////        switch (item.getItemId()) {
////            case R.id.action_thoat:
////                Intent intent = new Intent(UserActivity.this, LoginActivity.class);
////                startActivity(intent);
////                Toast.makeText(this, "Thoát", Toast.LENGTH_SHORT).show();
////                break;
////        }
////        return super.onOptionsItemSelected(item);
////    }

}
