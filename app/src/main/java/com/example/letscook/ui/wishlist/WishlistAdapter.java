package com.example.letscook.ui.wishlist;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.letscook.R;
import com.example.letscook.model.Recipe;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class WishlistAdapter extends RecyclerView.Adapter<WishlistAdapter.ViewHolder>{
    Context context;
    ArrayList<Recipe> listRecipe;

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
    }

    @Override
    public int getItemCount() {
        return listRecipe.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView recipeAvatar;
        TextView txtName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            recipeAvatar = itemView.findViewById(R.id.anhcongthuc);
            txtName = itemView.findViewById(R.id.txtName);
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
