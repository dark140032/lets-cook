package com.example.letscook.ui.home;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.letscook.R;
import com.example.letscook.model.Recipe;
import com.example.letscook.ui.recipe.RecipeDetailActivity;

import java.util.ArrayList;

public class ItemCategoryAdapter extends RecyclerView.Adapter<ItemCategoryAdapter.ViewHolder>{

    Context context;
    ArrayList<Recipe> recipeArrayList;
    CategoryAdapter categoryAdapter;
    String _idUserL;

    public ItemCategoryAdapter(Context context, ArrayList<Recipe> recipeArrayList, String _idUserL) {
        this.context = context;
        this.recipeArrayList = recipeArrayList;
        this._idUserL = _idUserL;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.item_theme, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Recipe recipe = recipeArrayList.get(position);

        holder.imgv.setImageDrawable(getImage(recipe.getRecipeAvatar()));
        holder.txtThemeDetailNm.setText(recipe.getRecipeName());

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
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
        return recipeArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtThemeDetailNm;
        View linearLayout;
        ImageView imgv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtThemeDetailNm = itemView.findViewById(R.id.txt_theme);
            linearLayout = itemView.findViewById(R.id.theme_list);
            imgv = itemView.findViewById(R.id.img_theme_item);
        }
    }

    private Drawable getImage(String nombreFile) {
        Drawable res1 = null;
        String uri1 = null;
        try {
            //First image
            uri1 = "@drawable/" + nombreFile;
            int imageResource1 = context.getResources().getIdentifier(uri1, null,context.getPackageName());
            res1 = context.getResources().getDrawable(imageResource1);
        } catch (Exception e) {

        }
        return res1;
    }
}
