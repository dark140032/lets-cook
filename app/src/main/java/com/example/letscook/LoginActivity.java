package com.example.letscook;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.letscook.DAO.UserDAO;


public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        findViewById(R.id.btn_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        findViewById(R.id.btn_DoLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doLogin();
            }
        });
    }
    public void doLogin() {
        EditText edtEmail = findViewById(R.id.edt_email_login);
        EditText edtPassword = findViewById(R.id.edt_password_login);
        UserDAO userDAO = new UserDAO(getApplicationContext());
        if(userDAO.getUser(edtEmail.getText().toString(),edtPassword.getText().toString()) != null){
            Toast.makeText(getApplicationContext(), "login", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getApplicationContext(), "fail", Toast.LENGTH_SHORT).show();
        }
//        if (edtEmail.getText().toString().equals("") || edtPassword.getText().toString().equals("")) {
//            Toast.makeText(getApplicationContext(), "Hãy nhập email và password", Toast.LENGTH_LONG).show();
//        }
//        else {
//            if(edtEmail.getText().toString().equals("admin") && edtPassword.getText().toString().equals("admin"))
//                Toast.makeText(getApplicationContext(), "Đăng nhập thành công", Toast.LENGTH_LONG).show();
//            else
//                Toast.makeText(getApplicationContext(), "Sai tên đăng nhập hoặc mật khẩu "+edtEmail.getText().toString() + " " + edtPassword.getText().toString(), Toast.LENGTH_SHORT).show();
//        }
    }
}
