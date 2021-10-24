package com.example.letscook.ui.wishlist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.letscook.R;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class CongThucAdapter extends RecyclerView.Adapter<CongThucAdapter.ViewHolder>{
    Context context;
    ArrayList<CongThuc> listSanPham;

    public CongThucAdapter(Context applicationContext, ArrayList<CongThuc> listSanPham) {
        this.context = context;
        this.listSanPham = listSanPham;
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
        CongThuc congThuc = listSanPham.get(position);
        holder.anhcongthuc.setImageResource(congThuc.getImage());
        Locale locale = new Locale("vn", "VN");
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
        holder.txtName.setText(congThuc.getName());
    }

    @Override
    public int getItemCount() {
        return listSanPham.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView anhcongthuc;
        TextView txtName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            anhcongthuc = itemView.findViewById(R.id.anhcongthuc);
            txtName = itemView.findViewById(R.id.txtName);
        }
    }
}
