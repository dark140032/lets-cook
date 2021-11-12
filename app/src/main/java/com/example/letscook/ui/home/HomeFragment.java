package com.example.letscook.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.letscook.DAO.HomeDAO;
import com.example.letscook.R;
import com.example.letscook.databinding.FragmentHomeBinding;
import com.example.letscook.model.Category;
import com.example.letscook.model.Theme;
import com.example.letscook.model.User;
import com.example.letscook.ui.notes.NoteDetailActivity;
import com.example.letscook.ui.recipe.RecipeDetailActivity;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    RecyclerView recyclerView;
    HomeAdapter recipeAdapter;
    CategoryAdapter categoryAdapter;
    HomeDAO homeDAO;
    RecyclerView recyclerViewCategory;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);


        //Get user đã đăng nhập tại code này
        Intent intent = getActivity().getIntent();
        Bundle bundle = intent.getExtras();
        User user = (User) bundle.getSerializable("object_user");

        Log.e("TAG", "id user: " + user.getUserId() );

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        recyclerView = root.findViewById(R.id.theme_recycleView);
        homeDAO = new HomeDAO(getContext());
        homeDAO.open();
        ArrayList<Theme> themes = homeDAO.getAllTheme();
        recipeAdapter = new HomeAdapter(getContext(),themes, user.getUserId());
        recyclerView.setAdapter(recipeAdapter);

        // Đưa item_recycleview_category vào adapter
        recyclerViewCategory = root.findViewById(R.id.recycleview_home_category);
        homeDAO = new HomeDAO(getContext());
        homeDAO.open();
        ArrayList<Category> categories = homeDAO.getAllCategory();
        categoryAdapter = new CategoryAdapter(getContext(),categories, user.getUserId());
        recyclerViewCategory.setAdapter(categoryAdapter);
        recyclerViewCategory.setLayoutManager(new LinearLayoutManager(getContext()));

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
        if (recipeAdapter != null){
            recipeAdapter.release();
        }
    }
}