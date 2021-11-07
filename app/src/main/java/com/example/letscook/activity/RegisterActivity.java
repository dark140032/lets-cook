package com.example.letscook.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.letscook.DAO.UserDAO;
import com.example.letscook.R;
import com.example.letscook.model.User;
import com.example.letscook.validation.Validation;

import java.util.regex.Pattern;


public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        findViewById(R.id.btn_doRegister).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                UserDAO userDAO = new UserDAO(getApplicationContext());
                userDAO.open();

                EditText edt_email = findViewById(R.id.edt_mail_register);
                EditText edt_username = findViewById(R.id.edt_username_register);
                EditText edt_password = findViewById(R.id.edt_password_register);
                EditText edt_confirmPassword = findViewById(R.id.edt_confirmPassword_register);

                if (!edt_email.getText().toString().equals("") && !edt_username.getText().toString().equals("")
                        && !edt_password.getText().toString().equals("") && !edt_confirmPassword.getText().toString().equals("")) {
                    if (Validation.isValidMail(edt_email.getText().toString())) {
                        if (!userDAO.isExistMail(edt_email.getText().toString())) {
                            if(edt_password.getText().toString().equals(edt_confirmPassword.getText().toString())){
                                if (userDAO.register(new User(edt_username.getText().toString(), edt_email.getText().toString(), edt_password.getText().toString()))) {
                                    Toast.makeText(getApplicationContext(), "Đăng ký thành công !", Toast.LENGTH_LONG).show();
                                    finish();
                                    onBackPressed();
                                } else
                                    Toast.makeText(getApplicationContext(), "Đăng ký thất bại !", Toast.LENGTH_LONG).show();
                            }else
                                Toast.makeText(getApplicationContext(), "Mật khẩu nhập lại không đúng !", Toast.LENGTH_LONG).show();
                        } else
                            Toast.makeText(getApplicationContext(), "Mail đã được đăng ký, hãy nhập mail khác !", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Hãy nhập lại đúng format mail !", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Hãy nhập đầy đủ thông tin !", Toast.LENGTH_LONG).show();
                }

                userDAO.close();
            }
        });
        findViewById(R.id.img_btn_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                finish();
            }
        });
    }
}
