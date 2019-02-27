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
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import vn.edu.poly.bookmanager.R;
import vn.edu.poly.bookmanager.adapter.TypeBookAdapter;
import vn.edu.poly.bookmanager.dao.TypeBookDAO;
import vn.edu.poly.bookmanager.model.TypeBook;
import vn.edu.poly.bookmanager.sqlite.DatabaseHelper;

public class TypebookActivity extends AppCompatActivity {

    private Toolbar toolbar;

    private RecyclerView recyclerView;
    private List<TypeBook> typeBookList;
    private TypeBookAdapter adapter;

    private ImageView imgback;
    private FloatingActionButton fabadd;

    private DatabaseHelper databaseHelper;
    private TypeBookDAO typeBookDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theloai);
        initViews();
        initActions();

        databaseHelper = new DatabaseHelper(this);
        typeBookDAO = new TypeBookDAO(databaseHelper);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.recyclerview);
        typeBookList = new ArrayList<>();

        typeBookList = typeBookDAO.getAllTypeBook();

        adapter = new TypeBookAdapter(TypebookActivity.this, typeBookList, typeBookDAO);
        recyclerView.setAdapter(adapter);

        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);

    }

    private void initActions() {
        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TypebookActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });

        fabadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(TypebookActivity.this);
                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View dialogView = inflater.inflate(R.layout.dialog_add_theloai, null);
                builder.setView(dialogView);
                final Dialog dialog = builder.show();

                final EditText edtmatheloai;
                final EditText edttentheloai;
                final EditText edtmotatheloai;
                final EditText edtvitritheloai;
                final Button btnthemtheloai;
                final Button btncanceltheloai;

                edtmatheloai = dialog.findViewById(R.id.edtmatheloai);
                edttentheloai = dialog.findViewById(R.id.edttentheloai);
                edtmotatheloai = dialog.findViewById(R.id.edtmotatheloai);
                edtvitritheloai = dialog.findViewById(R.id.edtvitritheloai);
                btnthemtheloai = dialog.findViewById(R.id.btnthemtheloai);
                btncanceltheloai = dialog.findViewById(R.id.btncanceltheloai);

                btnthemtheloai.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String id = edtmatheloai.getText().toString().trim();
                        String name = edttentheloai.getText().toString().trim();
                        String description = edtmotatheloai.getText().toString().trim();
                        String position = edtvitritheloai.getText().toString().trim();

                        if (id.equals("")){
                            edtmatheloai.setError(getString(R.string.notify_empty_name));
                            return;
                        }
                        if (name.equals("")){
                            edttentheloai.setError(getString(R.string.notify_empty_name));
                            return;
                        }
                        if (name.trim().length() > 3) {
                            edttentheloai.setError(getString(R.string.notify_empty_name_spinner));
                        }
                        if (description.equals("")){
                            edtmotatheloai.setError(getString(R.string.notify_empty_name));
                            return;
                        }
                        if (position.equals("")){
                            edtvitritheloai.setError(getString(R.string.notify_empty_name));
                            return;
                        }


                        TypeBook typeBook1 = new TypeBook();
                        typeBook1.setId(id);
                        typeBook1.setName(name);
                        typeBook1.setDescription(description);
                        typeBook1.setPosition(Integer.parseInt(edtvitritheloai.getText().toString()));

                        typeBookDAO.insertTypeBook(typeBook1);

                        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(edttentheloai.getWindowToken(), 0);

                        Intent it = new Intent(TypebookActivity.this,TypebookActivity.class);
                        startActivity(it);
                        typeBookDAO.getAllTypeBook();

                        Toast.makeText(TypebookActivity.this, "đã thêm", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });

                btncanceltheloai.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
            }


        });

    }

    private void initViews() {
        imgback =  findViewById(R.id.imgback);
        fabadd =  findViewById(R.id.fabadd);
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
//                Intent intent = new Intent(TypebookActivity.this, LoginActivity.class);
//                finish();
//                Toast.makeText(this, "Thoát", Toast.LENGTH_SHORT).show();
//                break;
//        }
//        return super.onOptionsItemSelected(item);
//    }

}
