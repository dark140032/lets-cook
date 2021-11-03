package com.example.letscook.ui.wishlist;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.letscook.DAO.WishlistDAO;
import com.example.letscook.R;
import com.example.letscook.model.Recipe;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class WishlistAdapter extends RecyclerView.Adapter<WishlistAdapter.ViewHolder>{
    Context context;
    ArrayList<Recipe> listRecipe;
    WishlistDAO wishlistDAO;

    public WishlistAdapter(Context applicationContext, ArrayList<Recipe> listRecipe) {
        this.context = context;
        this.listRecipe = listRecipe;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.item_wishlist, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Recipe recipe = listRecipe.get(position);
//        holder.recipeAvatar.setImageResource("@drawable/" + recipe.getRecipeAvatar());
        holder.recipeAvatar.setImageResource(R.drawable.hambeger);
        Locale locale = new Locale("vn", "VN");
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
        holder.txtName.setText(recipe.getRecipeName());

        holder.btnDeleteWishlistItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                listRecipe.remove(holder.getAdapterPosition());
//                notifyItemRemoved(holder.getAdapterPosition());
                String temp_recipeId = recipe.getRecipeId();
                Log.e("TAG", "id recipe:  " + temp_recipeId );
                String temp_userId = "1";
                wishlistDAO.delete(temp_recipeId,temp_userId);

            }
        });

    }

    @Override
    public int getItemCount() {
        return listRecipe.size();
    }

//    public void delete(String _iduser, String _idrecipe) {
//        wishlistDAO.delete(_idrecipe, _iduser);
//    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView recipeAvatar;
        TextView txtName;
        ImageButton btnDeleteWishlistItem;
        View recycleview;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            recipeAvatar = itemView.findViewById(R.id.anhcongthuc);
            txtName = itemView.findViewById(R.id.txtName);
            btnDeleteWishlistItem = itemView.findViewById(R.id.btn_delete_wishlist_item);
            recycleview = itemView.findViewById(R.id.btn_delete_wishlist_item);
        }
    }

    // method for filtering our recyclerview items.
    public void filterList(ArrayList<Recipe> filterllist) {
        // below line is to add our filtered
        // list in our course array list.
        listRecipe = filterllist;
        // below line is to notify our adapter
        // as change in recycler view data.
        notifyDataSetChanged();
    }

}
