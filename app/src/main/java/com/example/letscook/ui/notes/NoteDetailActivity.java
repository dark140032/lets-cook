package com.example.letscook.ui.notes;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.letscook.DAO.NoteDAO;
import com.example.letscook.R;
import com.example.letscook.activity.MainActivity;
import com.example.letscook.model.Note;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class NoteDetailActivity extends AppCompatActivity {

    ImageButton btnBackNotes;
    TextView btnSaveNote;
    TextView txtTitleNote;
    TextView txtContentNote;
    NoteDAO noteDAO;
    ImageButton btn_deleteDetail;
    AlertDialog.Builder builder;
    RecyclerView recyclerView;
    NoteAdapter noteAdapter;
    String istemp = "no";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_detail);

        noteDAO = new NoteDAO(this);
        noteDAO.open();



        //Get user đã đăng nhập tại code này
        Intent intent1 = this.getIntent();

        String _idUser= intent1.getStringExtra("_idUser");

        Log.e("TAG", "ID USER: " + _idUser );

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
                    builder = new AlertDialog.Builder(NoteDetailActivity.this);
                    //Uncomment the below code to Set the message and title from the strings.xml file
                    builder.setMessage(R.string.dialog_message) .setTitle(R.string.dialog_title);

                    //Setting message manually and performing action on button click
                    builder.setMessage("Bạn có chắc rằng muốn xóa nó ?")
                            .setCancelable(false)
                            .setPositiveButton("Đồng ý!", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    noteDAO.delete(_id,note.getUserId());
                                    onBackPressed();
                                    Toast.makeText(NoteDetailActivity.this, "Xóa thành công!",Toast.LENGTH_LONG).show();
                                }
                            })
                            .setNegativeButton("Không!", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    //  Action for 'NO' Button
                                    dialog.cancel();
                                }
                            });
                    //Creating dialog box
                    AlertDialog alert = builder.create();
                    //Setting the title manually
                    alert.setTitle("Thông báo");
                    alert.show();
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
                    noteDAO.update(_id,title,content,note.getUserId());
                    onBackPressed();
                }
            });

            btnBackNotes = findViewById(R.id.btn_back_notes);
            btnBackNotes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    builder = new AlertDialog.Builder(NoteDetailActivity.this);
                    //Uncomment the below code to Set the message and title from the strings.xml file
                    builder.setMessage(R.string.dialog_message) .setTitle(R.string.dialog_title);

                    //Setting message manually and performing action on button click
                    builder.setMessage("Bạn có lưu bài này không ?")
                            .setCancelable(false)
                            .setPositiveButton("Đồng ý!", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {

                                    String title = txtTitleNote.getText().toString();
                                    String content = txtContentNote.getText().toString();
                                    noteDAO.update(_id,title,content,note.getUserId());
                                    onBackPressed();

                                                                    }
                            })
                            .setNegativeButton("Không!", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    onBackPressed();
                                }
                            });
                    //Creating dialog box
                    AlertDialog alert = builder.create();
                    //Setting the title manually
                    alert.setTitle("Thông báo");
                    alert.show();
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
                    Log.e("TAG", "ID alo: " + _idUser );
                    if (title.isEmpty()){
                        Toast.makeText(getApplicationContext(),"Bạn không thể để tiêu đề trống!",Toast.LENGTH_LONG).show();
                    }else{

                        ArrayList<Note> notelist = noteDAO.getAll(_idUser);
                        for (Note item:notelist) {
                            if (item.getNoteName().equals(title)){

                                istemp = "yes";
                            }
                        }
                        if (istemp.equals("no")){
                            noteDAO.insert(new Note("",title, content,date,_idUser));
                            Toast.makeText(getApplicationContext(),"Lưu thành công!",Toast.LENGTH_LONG).show();
                            onBackPressed();
                        }else {
                            Toast.makeText(getApplicationContext(),"Tiêu đề đã tồn tại!",Toast.LENGTH_LONG).show();
                            istemp = "no";
                        }

                    }
                }
            });
            btn_deleteDetail = findViewById(R.id.btn_delete_note_detail);
            btn_deleteDetail.setVisibility(View.INVISIBLE);

            btnBackNotes = findViewById(R.id.btn_back_notes);
            btnBackNotes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    builder = new AlertDialog.Builder(NoteDetailActivity.this);
                    //Uncomment the below code to Set the message and title from the strings.xml file
                    builder.setMessage(R.string.dialog_message) .setTitle(R.string.dialog_title);

                    //Setting message manually and performing action on button click
                    builder.setMessage("Bạn có lưu bài này không ?")
                            .setCancelable(false)
                            .setPositiveButton("Đồng ý!", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {

                                    String title = txtTitleNote.getText().toString();
                                    String content = txtContentNote.getText().toString();
                                    Date d = new Date();
                                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");
                                    String date= formatter.format(d);
                                    Log.e("TAG", "ID alo: " + _idUser );
                                    if (title.isEmpty()){
                                        Toast.makeText(getApplicationContext(),"Bạn không thể để tiêu đề trống!",Toast.LENGTH_LONG).show();
                                    }else{

                                        ArrayList<Note> notelist = noteDAO.getAll(_idUser);
                                        for (Note item:notelist) {
                                            if (item.getNoteName().equals(title)){

                                                istemp = "yes";
                                            }
                                        }
                                        if (istemp.equals("no")){
                                            noteDAO.insert(new Note("",title, content,date,_idUser));
                                            Toast.makeText(getApplicationContext(),"Lưu thành công!",Toast.LENGTH_LONG).show();
                                            onBackPressed();
                                        }else {
                                            Toast.makeText(getApplicationContext(),"Tiêu đề đã tồn tại!",Toast.LENGTH_LONG).show();
                                            istemp = "no";
                                        }

                                    }

                                }
                            })
                            .setNegativeButton("Không!", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    onBackPressed();
                                }
                            });
                    //Creating dialog box
                    AlertDialog alert = builder.create();
                    //Setting the title manually
                    alert.setTitle("Thông báo");
                    alert.show();
                }
            });
        }
    }



}
