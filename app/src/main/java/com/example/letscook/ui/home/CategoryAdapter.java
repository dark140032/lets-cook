package com.example.letscook.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.letscook.DAO.HomeDAO;
import com.example.letscook.R;
import com.example.letscook.model.Category;
import com.example.letscook.model.Recipe;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder>{

    Context context;
    ArrayList<Category> categories;

    public String _id;
    String _idUserL;

    public CategoryAdapter(Context context, ArrayList<Category> categories, String _idUserL) {
        this.context = context;
        this.categories = categories;
        this._idUserL = _idUserL;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.iteam_recycleview_home, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Category category = categories.get(position);

        holder.txtNameCate.setText(category.getCategoryName());

        _id = category.getCategoryId();

        HomeDAO homeDAO = new HomeDAO(context);
        homeDAO.open();
        ArrayList<Recipe> recipes = homeDAO.getAllRecipeByCategoryId(_id);

        ItemCategoryAdapter itemCategoryAdapter;
        itemCategoryAdapter = new ItemCategoryAdapter(context,recipes, _idUserL);
        holder.recyclerView.setAdapter(itemCategoryAdapter);
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        View linearLayOut;
        TextView txtNameCate;
        RecyclerView recyclerView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            linearLayOut = itemView.findViewById(R.id.layout_item_category);
            txtNameCate = itemView.findViewById(R.id.txt_cate_name);
            recyclerView = itemView.findViewById(R.id.recyclerview_item_category);

        }
    }

}
