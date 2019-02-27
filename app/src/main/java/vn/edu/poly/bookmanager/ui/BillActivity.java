package vn.edu.poly.bookmanager.ui;

import android.app.DatePickerDialog;
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
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import vn.edu.poly.bookmanager.R;
import vn.edu.poly.bookmanager.adapter.BillAdapter;
import vn.edu.poly.bookmanager.dao.BillDAO;
import vn.edu.poly.bookmanager.model.Bill;
import vn.edu.poly.bookmanager.sqlite.DatabaseHelper;

public class BillActivity extends AppCompatActivity {

    private Toolbar toolbar;

    private RecyclerView recyclerView;
    private List<Bill> billList;
    private BillAdapter adapter;

    private ImageView imgback;
    private FloatingActionButton fabadd;

    private DatabaseHelper databaseHelper;
    private BillDAO billDAO;

    private TextView tvdatebill;
    long datePicker = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);
        initViews();
        initActions();

        databaseHelper = new DatabaseHelper(this);
        billDAO = new BillDAO(databaseHelper);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.recyclerview);
        billList = new ArrayList<>();

        billList = billDAO.getAllBill();


        Log.e("billList", billList.size() + "");
        adapter = new BillAdapter(BillActivity.this, billList, billDAO);
        recyclerView.setAdapter(adapter);

        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);

    }

    private void initActions() {
        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BillActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });

        fabadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(BillActivity.this);
                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View dialogView = inflater.inflate(R.layout.dialog_add_bill, null);
                builder.setView(dialogView);
                final Dialog dialog = builder.show();

                final EditText edtmahoadon;
                final Button btndatebill;

                edtmahoadon = dialogView.findViewById(R.id.edtmahoadon);
                tvdatebill = dialogView.findViewById(R.id.tvdatebill);
                btndatebill = dialogView.findViewById(R.id.btndatebill);

                Button add = dialogView.findViewById(R.id.btnthemhoadon);
                add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String idbill = edtmahoadon.getText().toString().trim();
//                        long date = Long.parseLong(tvdatebill.getText().toString().trim());

                        try {
                            if (idbill.length() < 0) {
                                Toast.makeText(getApplicationContext(), "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                            } else {
                                Bill bill = new Bill();



                            }
                        } catch (Exception ex) {
                            Log.e("Error", ex.toString());
                        }


                        Bill bill2 = new Bill(idbill, datePicker);
                        bill2.setIdhoadon(idbill);
                        bill2.setDatehoadon(datePicker);

                        billDAO.insertBill(bill2);

                        startActivity(new Intent(BillActivity.this, BillActivity.class));
                        billDAO.getAllBill();

                        Toast.makeText(getApplicationContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BillActivity.this, ThanhtoanActivity.class);
                        Bundle b = new Bundle();
                        b.putString("MAHOADON", edtmahoadon.getText().toString());
                        intent.putExtras(b);
                        startActivity(intent);

                        Log.e("MAHOADON", edtmahoadon.getText().toString());

                        dialog.dismiss();

                    }

                });

                Button cancel = dialogView.findViewById(R.id.btncancelhoadon);
                cancel.setOnClickListener(new View.OnClickListener()

                {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                Button chonbill = dialog.findViewById(R.id.btndatebill);
                chonbill.setOnClickListener(new View.OnClickListener()

                {
                    @Override
                    public void onClick(View v) {
                        showDatePicker();
                    }
                });

                dialog.show();

            }

        });

    }


    public void showDatePicker() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        // thiet lap thong tin cho date picker
        DatePickerDialog datePicker = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, dayOfMonth);

                long startTime = calendar.getTimeInMillis();
                BillActivity.this.datePicker = calendar.getTimeInMillis();
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                tvdatebill.setText(dateFormat.format(startTime));

            }
        }, year, month, day);

        datePicker.show();
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
//                Intent intent = new Intent(BillActivity.this, LoginActivity.class);
//                finish();
//                Toast.makeText(this, "Thoát", Toast.LENGTH_SHORT).show();
//                break;
//        }
//        return super.onOptionsItemSelected(item);
//    }


}
