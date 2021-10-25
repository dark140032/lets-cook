package com.example.letscook.ui.wishlist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.appcompat.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.letscook.MainActivity;
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

        SearchView searchView = root.findViewById(R.id.searchWishlist);
        // below line is to call set on query text listener method.
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText);
                return false;
            }
        });
        return root;
    }

    private void filter(String text) {
        // creating a new array list to filter our data.
        ArrayList<CongThuc> filteredlist = new ArrayList<>();

        // running a for loop to compare elements.
        for (CongThuc item : listSanPham) {
            // checking if the entered string matched with any item of our recycler view.
            if (item.getName().toLowerCase().contains(text.toLowerCase())) {
                // if the item is matched we are
                // adding it to our filtered list.
                filteredlist.add(item);
            }
        }
        if (filteredlist.isEmpty()) {
            // if no item is added in filtered list we are
            // displaying a toast message as no data found.
            congThucAdapter.filterList(listSanPham);

        } else {
            // at last we are passing that filtered
            // list to our adapter class.
            congThucAdapter.filterList(filteredlist);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}