package com.example.letscook.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.letscook.DAO.HomeDAO;
import com.example.letscook.R;
import com.example.letscook.databinding.FragmentHomeBinding;
import com.example.letscook.model.Recipe;
import com.example.letscook.model.Theme;

import java.util.ArrayList;

public class ThemeActivity extends AppCompatActivity {

    TextView txtThemeNm;
    RecyclerView themeDetailRecyclerView;
    ThemeAdapter themeAdapter;
    HomeDAO homeDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.theme_detail);

        homeDAO = new HomeDAO(getApplicationContext());
        homeDAO.open();

        themeDetailRecyclerView = findViewById(R.id.theme_detail_recycleView);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        Bundle bundle = getIntent().getExtras();

        if (bundle == null) {
            return;
        }

        Theme theme = (Theme) bundle.get("object_theme");

        if (theme != null){
            String themeId = theme.getThemeId();
            String themeNm = theme.getThemeName();
            txtThemeNm = findViewById(R.id.txt_theme_nm);
            txtThemeNm.setText(themeNm);
            ArrayList<Recipe> recipes = homeDAO.getAllRecipeByThemeId(themeId);
            themeAdapter = new ThemeAdapter(getApplicationContext(),recipes);
            Log.e("abc", "onCreate: " );
            themeDetailRecyclerView.setAdapter(themeAdapter);
        }
    }
}
