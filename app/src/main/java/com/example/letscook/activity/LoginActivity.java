package com.example.letscook.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.letscook.DAO.UserDAO;
<<<<<<< HEAD
import com.example.letscook.Encrypt.AESCrypt;
import com.example.letscook.R;
import com.example.letscook.model.User;
import com.example.letscook.ui.notes.NoteDetailActivity;
import com.example.letscook.ui.recipe.RecipeDetailActivity;
import com.example.letscook.validation.Validation;

import java.util.ArrayList;
import java.util.regex.Pattern;
=======
>>>>>>> 066656332b58d003bf9a4801fe00e1aac4262304


public class LoginActivity extends AppCompatActivity {

    public String emailFromChangePassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        UserDAO userDAO = new UserDAO(getApplicationContext());
        userDAO.open();
        EditText edt_email = findViewById(R.id.edt_email_login);
        EditText edt_password = findViewById(R.id.edt_password_login);

        edt_email.setText(emailFromChangePassword);

        findViewById(R.id.btn_doLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!edt_email.getText().toString().equals("") && !edt_password.getText().toString().equals("")) {
                    if (Validation.isValidMail(edt_email.getText().toString())) {
                        User user = userDAO.getUser(edt_email.getText().toString().trim(), AESCrypt.encrypt(edt_password.getText().toString().trim()));
                        if (user != null) {
                            Intent i = new Intent(LoginActivity.this, MainActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("object_user", user);
                            i.putExtras(bundle);
                            LoginActivity.this.startActivity(i);
                            finish();
                        } else
                            Toast.makeText(getApplicationContext(), "Sai email hoặc mật khẩu, hãy nhập lại !", Toast.LENGTH_LONG).show();
                    } else
                        Toast.makeText(getApplicationContext(), "Hãy nhập lại đúng format mail !", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Hãy nhập đầy đủ thông tin !", Toast.LENGTH_LONG).show();
                }
            }
        });

        findViewById(R.id.btn_back_in_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                finish();
            }
        });
    }
}
