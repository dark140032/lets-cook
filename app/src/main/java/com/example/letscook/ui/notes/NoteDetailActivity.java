package com.example.letscook.ui.notes;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.letscook.R;
import com.example.letscook.model.User;


public class NoteDetailActivity extends AppCompatActivity {

    ImageButton btnBackNotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_detail);
        btnBackNotes = findViewById(R.id.btn_back_notes);
        btnBackNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NoteDetailActivity.super.onBackPressed();
            }
        });
        Bundle bundle = getIntent().getExtras();
        if (bundle == null){
            return;
        }
        Note note = (Note) bundle.get("object_note");
        TextView txtTitleNote = findViewById(R.id.txt_title_note);
        txtTitleNote.setText(note.getTitle());
        TextView txtContentNote = findViewById(R.id.txt_content_note);
        txtContentNote.setText(note.getContent());
    }
}
