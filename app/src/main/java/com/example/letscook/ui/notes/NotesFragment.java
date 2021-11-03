package com.example.letscook.ui.notes;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.letscook.DAO.NoteDAO;
import com.example.letscook.R;
import com.example.letscook.databinding.FragmentNotesBinding;

import java.util.ArrayList;
import com.example.letscook.model.Note;

public class NotesFragment extends Fragment {

    private NotesViewModel homeViewModel;
    private FragmentNotesBinding binding;
    private NoteDAO noteDAO;
    RecyclerView recyclerView;
    ImageButton btnDeleteNote;

    NoteAdapter noteAdapter;
    ImageButton btnAddNote;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        Log.e("TAG", "rfirefdsf: " );
        homeViewModel =
                new ViewModelProvider(this).get(NotesViewModel.class);

        binding = FragmentNotesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        btnAddNote = root.findViewById(R.id.btn_add_note);
        btnAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), NoteDetailActivity.class);
                intent.putExtra("add", true);
                v.getContext().startActivity(intent);
            }
        });

        noteDAO =new NoteDAO(getContext());
        noteDAO.open();
        ArrayList<Note> listNote = noteDAO.getAll();
        recyclerView= root.findViewById(R.id.recyclerviewnotes);

        noteAdapter=new NoteAdapter(getContext(),listNote);
        recyclerView.setAdapter(noteAdapter);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
        if (noteAdapter != null){
            noteAdapter.release();
        }
    }

}