package vn.edu.poly.bookmanager.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import vn.edu.poly.bookmanager.R;


public class RegistrationActivity extends AppCompatActivity {

    private EditText edtname;
    private EditText edtpassword;
    private EditText edtrepassword;
    private Button btndangkymoi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        initViews();
        initActions();

    }

    private void initActions() {
        btndangkymoi.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtname.getText().toString().trim();
                String pass = edtpassword.getText().toString().trim();
                String repass = edtrepassword.getText().toString().trim();
                if (name.equals("")){
                    edtname.setError(getString(R.string.notify_empty_name));
                    return;
                }
                if (pass.equals("")){
                    edtpassword.setError(getString(R.string.notify_empty_name));
                    return;
                }
                if (pass.length() < 6){
                    edtpassword.setError(getString(R.string.notify_empty_pass_work));
                    return;
                }
                if (repass.equals("")){
                    edtrepassword.setError(getString(R.string.notify_empty_name));
                    return;
                }
                if (repass.length() < 6){
                    edtrepassword.setError(getString(R.string.notify_empty_repass_work));
                    return;
                }
                if (repass.equals(pass)){
                    edtrepassword.setError(getString(R.string.notify_empty_repass_work));
                    return;
                }
                Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                startActivity(intent);
                Toast.makeText(RegistrationActivity.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                finish();

            }
        });
    }

    private void initViews() {
        edtname = (EditText) findViewById(R.id.edtname);
        edtpassword = (EditText) findViewById(R.id.edtpassword);
        edtrepassword = (EditText) findViewById(R.id.edtrepassword);
        btndangkymoi = (Button) findViewById(R.id.btndangkymoi);
    }

}

