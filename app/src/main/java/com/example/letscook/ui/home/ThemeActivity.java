package com.example.letscook.ui.home;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.letscook.DAO.HomeDAO;
import com.example.letscook.R;
import com.example.letscook.model.Recipe;
import com.example.letscook.model.Theme;

import java.util.ArrayList;

public class ThemeActivity extends AppCompatActivity {

    TextView txtThemeNm;
    RecyclerView themeDetailRecyclerView;
    ThemeAdapter themeAdapter;
    HomeDAO homeDAO;
    ImageButton btnBackThemeDetail;
    ImageView imgv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.theme_detail);

        homeDAO = new HomeDAO(getApplicationContext());
        homeDAO.open();

        themeDetailRecyclerView = findViewById(R.id.theme_detail_recycleView);
        Bundle bundle = getIntent().getExtras();

        if (bundle == null) {
            return;
        }

        btnBackThemeDetail = findViewById(R.id.btn_back_theme_detail);
        btnBackThemeDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ThemeActivity.super.onBackPressed();
            }
        });

        Theme theme = (Theme) bundle.get("object_theme");
        String _id = (String) bundle.get("_idUserL") ;

        if (theme != null){
            String themeId = theme.getThemeId();
            String themeNm = theme.getThemeName();
            txtThemeNm = findViewById(R.id.txt_theme_nm);
            txtThemeNm.setText(themeNm);

            imgv = findViewById(R.id.imgvTheme);
            imgv.setImageDrawable(getImage(theme.getThemeImage()));

            ArrayList<Recipe> recipes = homeDAO.getAllRecipeByThemeId(themeId);
            themeAdapter = new ThemeAdapter(getApplicationContext(),recipes, _id);
            themeDetailRecyclerView.setAdapter(themeAdapter);
            themeDetailRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        }
    }
    private Drawable getImage(String nombreFile) {
        Drawable res1 = null;
        String uri1 = null;
        try {
            //First image
            uri1 = "@drawable/" + nombreFile;
            int imageResource1 = getResources().getIdentifier(uri1, null,getPackageName());
            res1 = getResources().getDrawable(imageResource1);
        } catch (Exception e) {

        }
        return res1;
    }
}
