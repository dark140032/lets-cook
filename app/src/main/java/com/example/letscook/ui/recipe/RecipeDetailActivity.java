package com.example.letscook.ui.recipe;

import android.content.Intent;
import android.graphics.drawable.Drawable;
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
import com.example.letscook.model.User;
import com.example.letscook.model.Wishlist;
import com.example.letscook.ui.wishlist.WishlistFragment;

import java.util.ArrayList;

public class RecipeDetailActivity extends AppCompatActivity {

    WishlistDAO wishlistDAO;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_details);

        ImageButton btnBack = findViewById(R.id.btn_back_recipe_detail);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        Bundle bundle = getIntent().getExtras();
        if (bundle == null){
            return;
        }

        Recipe recipe = (Recipe) bundle.get("object_recipe");
        Log.e("TAG", "txt_recipe_name: " + recipe.getRecipeName() );

        String _id = (String) bundle.get("_idUserL") ;
        Log.e("TAG", "_idL: " + _id );

        ImageView image = findViewById(R.id.img_recipe_avatar);
        image.setImageDrawable(getImage(recipe.getRecipeAvatar()));

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

        wishlistDAO =new WishlistDAO(getApplication());
        wishlistDAO.open();
        ArrayList<Recipe> listWishlist = wishlistDAO.getAllWishlist(_id);

        if (listWishlist.size() == 0){
            ImageButton btnAddWishlist = findViewById(R.id.btn_add_wishlist);
            ImageButton btnAddWishlistBlue = findViewById(R.id.btn_add_wishlist_blue);
            btnAddWishlist.setVisibility(View.VISIBLE);
            btnAddWishlistBlue.setVisibility(View.GONE);
            btnAddWishlist.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    btnAddWishlist.setVisibility(View.GONE);
                    btnAddWishlistBlue.setVisibility(View.VISIBLE);
                    wishlistDAO.insertWishlist(new Wishlist("",_id, recipe.getRecipeId()));
                    Toast.makeText(RecipeDetailActivity.this, "Thêm Yêu Thích Thành Công!", Toast.LENGTH_SHORT).show();
                }
            });

            btnAddWishlistBlue.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    btnAddWishlist.setVisibility(View.VISIBLE);
                    btnAddWishlistBlue.setVisibility(View.GONE);
                    wishlistDAO.delete(recipe.getRecipeId(),_id);
                    Toast.makeText(RecipeDetailActivity.this, "xóa Yêu Thích Thành Công!", Toast.LENGTH_SHORT).show();
                }
            });
            return;
        }else {
            for (Recipe item: listWishlist) {
                Log.e("TAG", "ID1: " + item.getRecipeId() );
                Log.e("TAG", "ID2: " + recipe.getRecipeId() );
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
                            wishlistDAO.delete(recipe.getRecipeId(),_id);
                            Toast.makeText(RecipeDetailActivity.this, "xóa Yêu Thích Thành Công!", Toast.LENGTH_SHORT).show();
                        }
                    });

                    btnAddWishlist.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            btnAddWishlist.setVisibility(View.GONE);
                            btnAddWishlistBlue.setVisibility(View.VISIBLE);
                            wishlistDAO.insertWishlist(new Wishlist("",_id, recipe.getRecipeId()));
                            Toast.makeText(RecipeDetailActivity.this, "Thêm Yêu Thích Thành Công!", Toast.LENGTH_SHORT).show();
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
                            wishlistDAO.insertWishlist(new Wishlist("",_id, recipe.getRecipeId()));
                            Toast.makeText(RecipeDetailActivity.this, "Thêm Yêu Thích Thành Công!", Toast.LENGTH_SHORT).show();
                        }
                    });

                    btnAddWishlistBlue.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            btnAddWishlist.setVisibility(View.VISIBLE);
                            btnAddWishlistBlue.setVisibility(View.GONE);
                            wishlistDAO.delete(recipe.getRecipeId(),_id);
                            Toast.makeText(RecipeDetailActivity.this, "xóa Yêu Thích Thành Công!", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
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
