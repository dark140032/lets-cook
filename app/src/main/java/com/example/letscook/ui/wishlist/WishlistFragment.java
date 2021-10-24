package com.example.letscook.ui.wishlist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.letscook.R;
import com.example.letscook.databinding.FragmentWishlistBinding;

import java.util.ArrayList;

public class WishlistFragment extends Fragment {

    private WishlistViewModel wishlistViewModel;
    private FragmentWishlistBinding binding;

    RecyclerView recyclerView;
    ArrayList<CongThuc> listSanPham;
    CongThucAdapter congThucAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        wishlistViewModel =
                new ViewModelProvider(this).get(WishlistViewModel.class);


        binding = FragmentWishlistBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        recyclerView= root.findViewById(R.id.recyclerview);
        listSanPham=new ArrayList<>();
        listSanPham.add(new CongThuc(R.drawable.hambeger, "ga cay"));
        listSanPham.add(new CongThuc(R.drawable.hambeger, "ga cay1"));
        listSanPham.add(new CongThuc(R.drawable.hambeger, "ga cay2"));
        listSanPham.add(new CongThuc(R.drawable.hambeger, "ga cay3"));
        listSanPham.add(new CongThuc(R.drawable.hambeger, "ga cay"));
        listSanPham.add(new CongThuc(R.drawable.hambeger, "ga cay1"));
        listSanPham.add(new CongThuc(R.drawable.hambeger, "ga cay2"));
        listSanPham.add(new CongThuc(R.drawable.hambeger, "ga cay3"));
        congThucAdapter=new CongThucAdapter(getContext(),listSanPham);
        recyclerView.setAdapter(congThucAdapter);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(null, 2);
        recyclerView.setLayoutManager(gridLayoutManager);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}