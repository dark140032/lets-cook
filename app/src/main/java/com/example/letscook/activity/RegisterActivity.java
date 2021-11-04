package com.example.letscook.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.letscook.DAO.UserDAO;
import com.example.letscook.model.User;


public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        UserDAO userDAO = new UserDAO(getApplicationContext());
        if(userDAO.registerUser(new User(0 + "","Nguyen B", "nguyenc@gmail.com","123456","userAvatar","14/01/2000","IT","nguyenB")) == true)
            Toast.makeText(getApplicationContext(), "tạo ok", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(getApplicationContext(), "tạo fail", Toast.LENGTH_SHORT).show();

        findViewById(R.id.btn_DoRegister).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        findViewById(R.id.btn_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }
}
