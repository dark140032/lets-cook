package com.example.letscook.ui.recipe;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.letscook.DAO.WishlistDAO;
import com.example.letscook.R;
import com.example.letscook.model.Note;
import com.example.letscook.model.Recipe;
import com.example.letscook.ui.wishlist.WishlistFragment;

import java.util.ArrayList;

public class RecipeDetailActivity extends AppCompatActivity {

    WishlistDAO wishlistDAO;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_details);

        Bundle bundle = getIntent().getExtras();
        if (bundle == null){
            return;
        }

        Recipe recipe = (Recipe) bundle.get("object_recipe");
        Log.e("TAG", "txt_recipe_name: " + recipe.getRecipeName() );

        String txt_name = recipe.getRecipeName();
        TextView txtName = findViewById(R.id.txt_recipe_name);
        txtName.setText(txt_name);

        String txt_des = recipe.getRecipeDes();
        TextView txtDes = findViewById(R.id.txt_recipe_des);
        txtDes.setText(txt_des);

        String txt_material = recipe.getRecipeMaterial();
        TextView txtMaterial = findViewById(R.id.txt_recipe_material);
        txtMaterial.setText(txt_material);

        String txt_making = recipe.getRecipeMaking();
        TextView txtMaking = findViewById(R.id.txt_recipe_making);
        txtMaking.setText(txt_making);

        ImageButton btnBack = findViewById(R.id.btn_back_recipe_detail);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        wishlistDAO =new WishlistDAO(getApplication());
        wishlistDAO.open();
        ArrayList<Recipe> listWishlist = wishlistDAO.getAllWishlist("1"); ;

        for (Recipe item: listWishlist) {
            if(item.getRecipeId().equals(recipe.getRecipeId())){
                ImageButton btnAddWishlist = findViewById(R.id.btn_add_wishlist);
                ImageButton btnAddWishlistBlue = findViewById(R.id.btn_add_wishlist_blue);
                btnAddWishlist.setVisibility(View.GONE);
                btnAddWishlistBlue.setVisibility(View.VISIBLE);
                btnAddWishlistBlue.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        btnAddWishlist.setVisibility(View.VISIBLE);
                        btnAddWishlistBlue.setVisibility(View.GONE);
                        wishlistDAO.delete(recipe.getRecipeId(),"1");
                        Toast.makeText(RecipeDetailActivity.this, "xóa Yêu Thích Thành Công!", Toast.LENGTH_SHORT).show();
                    }
                });
                return;
            }else {
                ImageButton btnAddWishlist = findViewById(R.id.btn_add_wishlist);
                ImageButton btnAddWishlistBlue = findViewById(R.id.btn_add_wishlist_blue);
                btnAddWishlist.setVisibility(View.VISIBLE);
                btnAddWishlistBlue.setVisibility(View.GONE);
                btnAddWishlist.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        btnAddWishlist.setVisibility(View.GONE);
                        btnAddWishlistBlue.setVisibility(View.VISIBLE);
                        Toast.makeText(RecipeDetailActivity.this, "Thêm Yêu Thích Thành Công!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }

    }

}
