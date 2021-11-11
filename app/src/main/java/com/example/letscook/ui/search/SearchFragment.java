package com.example.letscook.ui.search;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.letscook.DAO.SearchDAO;
import com.example.letscook.DAO.WishlistDAO;
import com.example.letscook.R;
import com.example.letscook.databinding.FragmentSearchBinding;
import com.example.letscook.model.Recipe;
import com.example.letscook.ui.wishlist.WishlistAdapter;

import java.util.ArrayList;

public class SearchFragment extends Fragment {

    SearchDAO searchDAO;
    RecyclerView recyclerView;
    SearchAdapter searchAdapter;

    private SearchViewModel searchViewModel;
    private FragmentSearchBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        searchViewModel =
                new ViewModelProvider(this).get(SearchViewModel.class);

        binding = FragmentSearchBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        searchDAO =new SearchDAO(getContext());
        searchDAO.open();
        ArrayList<Recipe> listRecipeSearch = searchDAO.getAllRecipe();
        recyclerView = root.findViewById(R.id.recycleview_search);

        listRecipeSearch.size();

        searchAdapter=new SearchAdapter(getContext(),listRecipeSearch);
        recyclerView.setAdapter(searchAdapter);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(null, 2);
        recyclerView.setLayoutManager(gridLayoutManager);

        SearchView searchView = root.findViewById(R.id.search_recipe_search);
        // below line is to call set on query text listener method.
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                ArrayList<Recipe> filteredlist = new ArrayList<>();
                // running a for loop to compare elements.

                for (Recipe item : listRecipeSearch) {
                    // checking if the entered string matched with any item of our recycler view.
                    if (item.getRecipeName().toLowerCase().contains(newText.toLowerCase())) {
                        // if the item is matched we are
                        // adding it to our filtered list.
                        filteredlist.add(item);
                    }
                }
                if (filteredlist.isEmpty()) {
                    // if no item is added in filtered list we are
                    // displaying a toast message as no data found.
                    searchAdapter.filterList(filteredlist);
                    Toast.makeText(getContext(), "Không tìm thấy!", Toast.LENGTH_SHORT).show();

                } else {
                    // at last we are passing that filtered
                    // list to our adapter class.
                    searchAdapter.filterList(filteredlist);
                }

                return false;
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}