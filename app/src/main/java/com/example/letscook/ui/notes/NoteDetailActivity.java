package com.example.letscook.ui.notes;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.letscook.DAO.NoteDAO;
import com.example.letscook.R;
import com.example.letscook.db.MyDB;
import com.example.letscook.model.Note;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NoteDetailActivity extends AppCompatActivity {

    ImageButton btnBackNotes;
    TextView btnSaveNote;
    TextView txtTitleNote;
    TextView txtContentNote;
    NoteDAO noteDAO;
    ImageButton btn_deleteDetail;

    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_detail);

        noteDAO = new NoteDAO(this);
        noteDAO.open();

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
        if (note != null){
            String titleNote = note.getNoteName();
            String contentNote = note.getNoteContent();
            TextView txtTitleNote = findViewById(R.id.txt_title_note);
            txtTitleNote.setText(titleNote);
            TextView txtContentNote = findViewById(R.id.txt_content_note);
            txtContentNote.setText(contentNote);
            String _id = note.getNoteId();

            btn_deleteDetail = findViewById(R.id.btn_delete_note_detail);
            btn_deleteDetail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    noteDAO.delete(_id,"1");
                    Toast.makeText(NoteDetailActivity.this, "Delete success!",Toast.LENGTH_LONG);
                    onBackPressed();
                }
            });

            btnSaveNote =findViewById(R.id.btn_save_note);
            btnSaveNote.setText("Sửa");
            txtTitleNote.setEnabled(false);
            btnSaveNote.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String title = txtTitleNote.getText().toString();
                    String content = txtContentNote.getText().toString();
                    noteDAO.update(_id,title,content,"1");
                    onBackPressed();
                }
            });

        }else {
            Intent intent = getIntent();
            Boolean add = intent.getBooleanExtra("add",true);
            txtTitleNote = findViewById(R.id.txt_title_note);
            txtContentNote = findViewById(R.id.txt_content_note);
            btnSaveNote =findViewById(R.id.btn_save_note);
            btnSaveNote.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String title = txtTitleNote.getText().toString();
                    String content = txtContentNote.getText().toString();
                    Date d = new Date();
                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");
                    String date= formatter.format(d);
                    noteDAO.insert(new Note("",title, content,date,"1"));
                    onBackPressed();
                }
            });
            btn_deleteDetail = findViewById(R.id.btn_delete_note_detail);
            btn_deleteDetail.setVisibility(View.INVISIBLE);
        }
    }



}
