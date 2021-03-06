package com.example.letscook.activity;

import android.os.Bundle;
<<<<<<< HEAD
import android.util.Log;
import android.view.View;
import android.widget.EditText;
=======
import android.view.View;
>>>>>>> 066656332b58d003bf9a4801fe00e1aac4262304
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.letscook.DAO.UserDAO;
<<<<<<< HEAD
import com.example.letscook.Encrypt.AESCrypt;
import com.example.letscook.R;
import com.example.letscook.model.User;
import com.example.letscook.validation.Validation;

import java.util.regex.Pattern;
=======
import com.example.letscook.model.User;
>>>>>>> 066656332b58d003bf9a4801fe00e1aac4262304


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
                                if (userDAO.register(new User(edt_username.getText().toString(), edt_email.getText().toString().trim(), AESCrypt.encrypt(edt_password.getText().toString().trim())))) {
                                    Toast.makeText(getApplicationContext(), "????ng ky?? tha??nh c??ng !", Toast.LENGTH_LONG).show();
                                    finish();
                                    onBackPressed();
                                } else
                                    Toast.makeText(getApplicationContext(), "????ng ky?? th????t ba??i !", Toast.LENGTH_LONG).show();
                            }else
                                Toast.makeText(getApplicationContext(), "M????t kh????u nh????p la??i kh??ng ??u??ng !", Toast.LENGTH_LONG).show();
                        } else
                            Toast.makeText(getApplicationContext(), "Mail ??a?? ????????c ????ng ky??, ha??y nh????p mail kha??c !", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Ha??y nh????p la??i ??u??ng format mail !", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Ha??y nh????p ??????y ??u?? th??ng tin !", Toast.LENGTH_LONG).show();
                }

                userDAO.close();
            }
        });
        findViewById(R.id.img_btn_back_in_register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                finish();
            }
        });
    }
}
