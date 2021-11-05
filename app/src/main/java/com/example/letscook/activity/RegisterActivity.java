package com.example.letscook.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.letscook.DAO.UserDAO;
import com.example.letscook.R;
import com.example.letscook.model.User;


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
                if(userDAO.register(new User("nguyennhan","nguyennhanmail","123456")))
                    Toast.makeText(getApplicationContext(), "register success", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(getApplicationContext(), "register fail", Toast.LENGTH_LONG).show();

                userDAO.close();
            }
        });
    }
}
