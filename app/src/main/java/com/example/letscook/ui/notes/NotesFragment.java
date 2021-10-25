package com.example.letscook.ui.notes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.letscook.R;
import com.example.letscook.databinding.FragmentNotesBinding;
import com.example.letscook.ui.wishlist.CongThuc;
import com.example.letscook.ui.wishlist.CongThucAdapter;

import java.util.ArrayList;

public class NotesFragment extends Fragment {

    private NotesViewModel homeViewModel;
    private FragmentNotesBinding binding;

    RecyclerView recyclerView;
    ArrayList<Note> listNote;
    NoteAdapter noteAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(NotesViewModel.class);

        binding = FragmentNotesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        recyclerView= root.findViewById(R.id.recyclerviewnotes);
        listNote=new ArrayList<>();
        listNote.add(new Note("Note 1", "10/10/2021","Alaba trap!"));
        listNote.add(new Note("Note 2", "10/10/2021","Alaba trap!"));

        noteAdapter=new NoteAdapter(getContext(),listNote);
        recyclerView.setAdapter(noteAdapter);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}