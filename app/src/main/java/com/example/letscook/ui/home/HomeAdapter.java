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
import com.example.letscook.model.Theme;
import com.example.letscook.ui.recipe.RecipeDetailActivity;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    Context context;
    ArrayList<Theme> themes;
    String _idUserL;


    public HomeAdapter(Context context, ArrayList<Theme> themes, String _idUserL) {
        this.context = context;
        this.themes = themes;
        this._idUserL = _idUserL;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.item_theme, parent, false);


        return new HomeAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeAdapter.ViewHolder holder, int position) {
        Theme theme = themes.get(position);

        holder.imgThemeItem.setImageDrawable(getImage(theme.getThemeImage()));
        holder.txtTheme.setText(theme.getThemeName());

        holder.linearLayOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, ThemeActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("object_theme", theme);
                bundle.putString("_idUserL", _idUserL);
                i.putExtras(bundle);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return themes.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTheme;
        ImageView imgThemeItem;
        View linearLayOut;


        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            txtTheme = itemView.findViewById(R.id.txt_theme);
            imgThemeItem = itemView.findViewById(R.id.img_theme_item);
            linearLayOut =  itemView.findViewById(R.id.theme_list);

        }
    }

    public void release() {
        context = null;
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
