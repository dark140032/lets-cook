package com.example.letscook.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.letscook.DAO.HomeDAO;
import com.example.letscook.R;
import com.example.letscook.databinding.FragmentHomeBinding;
import com.example.letscook.model.Recipe;
import com.example.letscook.model.Theme;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    RecyclerView recyclerView;
    HomeAdapter recipeAdapter;
    HomeDAO homeDAO;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        recyclerView = root.findViewById(R.id.theme_recycleView);
        homeDAO = new HomeDAO(getContext());
        homeDAO.open();
        ArrayList<Theme> themes = homeDAO.getAllTheme();
        recipeAdapter = new HomeAdapter(getContext(),themes);
        recyclerView.setAdapter(recipeAdapter);

      /*  final TextView textView = binding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
        
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