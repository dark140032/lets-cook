package com.example.letscook.ui.search;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.letscook.DAO.SearchDAO;
import com.example.letscook.DAO.WishlistDAO;
import com.example.letscook.R;
import com.example.letscook.model.Recipe;
import com.example.letscook.ui.recipe.RecipeDetailActivity;
import com.example.letscook.ui.wishlist.WishlistAdapter;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder>{

    Context context;
    ArrayList<Recipe> listRecipesearch;
    SearchDAO searchDAO;
    String _idUserL;

    public SearchAdapter(Context context, ArrayList<Recipe> listRecipesearch, String _idUserL) {
        this.context = context;
        this.listRecipesearch = listRecipesearch;
        this._idUserL = _idUserL;
    }

    @NonNull
    @Override
    public SearchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.item_search, parent, false);

        return new SearchAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.ViewHolder holder, int position) {
        Recipe recipe = listRecipesearch.get(position);
        holder.recipeAvatar.setImageResource(R.drawable.hambeger);
        holder.txtName.setText(recipe.getRecipeName());

        searchDAO =new SearchDAO(context);
        searchDAO.open();

        holder.recycleview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, RecipeDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("object_recipe", recipe);
                bundle.putString("_idUserL", _idUserL);
                i.putExtras(bundle);
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listRecipesearch.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView recipeAvatar;
        TextView txtName;
        View recycleview;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            recipeAvatar = itemView.findViewById(R.id.img_avatar_search);
            txtName = itemView.findViewById(R.id.txt_recipe_name_rearch);
            recycleview = itemView.findViewById(R.id.layout_item_Search);
        }
    }

    // method for filtering our recyclerview items.
    public void filterList(ArrayList<Recipe> filterllist) {
        // below line is to add our filtered
        // list in our course array list.
        listRecipesearch = filterllist;
        // below line is to notify our adapter
        // as change in recycler view data.
        notifyDataSetChanged();
    }
}
