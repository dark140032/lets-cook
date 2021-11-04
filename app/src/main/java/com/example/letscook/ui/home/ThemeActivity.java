package com.example.letscook.ui.home;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.letscook.R;
import com.example.letscook.model.Theme;

public class ThemeActivity extends AppCompatActivity {

    TextView txtThemeNm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.theme_detail);


        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            return;
        }

        Theme theme = (Theme) bundle.get("object_theme");

        if (theme != null){
            String themeNm = theme.getThemeName();
            txtThemeNm = findViewById(R.id.txt_theme_nm);
            txtThemeNm.setText(themeNm);

        }
    }
}
