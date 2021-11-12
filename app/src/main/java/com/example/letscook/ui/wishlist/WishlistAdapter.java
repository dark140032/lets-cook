package com.example.letscook.ui.wishlist;

import android.app.AlertDialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.recyclerview.widget.RecyclerView;

import com.example.letscook.DAO.NoteDAO;
import com.example.letscook.DAO.WishlistDAO;
import com.example.letscook.R;
import com.example.letscook.model.Recipe;
import com.example.letscook.ui.notes.NoteDetailActivity;
import com.example.letscook.ui.recipe.RecipeDetailActivity;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.InputStream;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;


public class WishlistAdapter extends RecyclerView.Adapter<WishlistAdapter.ViewHolder>{
    Context context;
    ArrayList<Recipe> listRecipe;
    WishlistDAO wishlistDAO;
    public static String idRecipe;
    String _idUserL;
    AlertDialog.Builder builder;


    public WishlistAdapter(Context context, ArrayList<Recipe> listRecipe, String _idUserL) {
        this.context = context;
        this.listRecipe = listRecipe;
        this._idUserL = _idUserL;
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

        holder.recipeAvatar.setImageDrawable(getImage(recipe.getRecipeAvatar()));

        Locale locale = new Locale("vn", "VN");
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
        holder.txtName.setText(recipe.getRecipeName());
        wishlistDAO =new WishlistDAO(context);
        wishlistDAO.open();

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

        holder.btnDeleteWishlistItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder = new AlertDialog.Builder(context);
                //Uncomment the below code to Set the message and title from the strings.xml file
                builder.setMessage(R.string.dialog_message) .setTitle(R.string.dialog_title);

                //Setting message manually and performing action on button click
                builder.setMessage("Bạn có chắc rằng muốn xóa nó ?")
                        .setCancelable(false)
                        .setPositiveButton("Đồng ý!", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                //xóa khỏi danh sách
                                listRecipe.remove(holder.getAdapterPosition());
                                notifyItemRemoved(holder.getAdapterPosition());
                                String temp_recipeId = recipe.getRecipeId();
                                Log.e("TAG", "id recipe:  " + temp_recipeId );
                                idRecipe = temp_recipeId;
                                wishlistDAO.delete(recipe.getRecipeId(),_idUserL);
                                Toast.makeText(context, "xóa Yêu Thích Thành Công!", Toast.LENGTH_SHORT).show();

                                Toast.makeText(context,"Đã xóa khỏi danh sách!",
                                        Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("Không!", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //  Action for 'NO' Button
                                dialog.cancel();
                            }
                        });
                //Creating dialog box
                AlertDialog alert = builder.create();
                //Setting the title manually
                alert.setTitle("Thông báo");
                alert.show();
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
            recycleview = itemView.findViewById(R.id.layout_item_wishlist);
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
