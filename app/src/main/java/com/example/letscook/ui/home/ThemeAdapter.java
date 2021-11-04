package com.example.letscook.ui.home;

import android.content.Context;
import android.content.Intent;
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

import com.example.letscook.R;
import com.example.letscook.model.Recipe;
import com.example.letscook.model.Theme;
import com.example.letscook.ui.recipe.RecipeDetailActivity;

import java.util.ArrayList;

public class ThemeAdapter extends RecyclerView.Adapter<ThemeAdapter.ViewHolder> {

    Context context;
    ArrayList<Recipe> recipes;

    public ThemeAdapter(Context context, ArrayList<Recipe> recipes) {
        this.context = context;
        this.recipes = recipes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.item_theme_detail, parent, false);


        return new ThemeAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            Recipe recipe = recipes.get(position);

            holder.txtThemeDetailNm.setText(recipe.getRecipeName());
            holder.linearLayOut.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(context, RecipeDetailActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("object_recipe", recipe);
                    i.putExtras(bundle);
                    context.startActivity(i);
                }
            });
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtThemeDetailNm;
        ImageButton imgBtnThemeDetailArrowItem;
        View linearLayOut;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            txtThemeDetailNm = itemView.findViewById(R.id.txt_theme_detail_nm);
            imgBtnThemeDetailArrowItem = itemView.findViewById(R.id.img_btn_theme_detail_arrow_item);
            linearLayOut =  itemView.findViewById(R.id.item_theme_detail_list);

        }
    }
}
