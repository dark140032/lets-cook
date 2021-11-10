package com.example.letscook.ui.notes;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.letscook.DAO.NoteDAO;
import com.example.letscook.R;
import com.example.letscook.model.Note;
import com.example.letscook.model.User;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder>{
    Context context;
    ArrayList<Note> listNote;
    AlertDialog.Builder builder;
    NoteDAO noteDAO;
    String _idUserL;

    public NoteAdapter(Context context, ArrayList<Note> listNote, String _idUserL) {
        this.context = context;
        this.listNote = listNote;
        this._idUserL = _idUserL;
    }

    @NonNull
    @Override
    public NoteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.item_notes, parent, false);

        return new NoteAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteAdapter.ViewHolder holder, int position) {
        Note note = listNote.get(position);
        Locale locale = new Locale("vn", "VN");
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
        holder.txttitlenotes.setText(note.getNoteName());
        holder.txtdatenoteslast.setText(note.getFrstRegistPttm());
        holder.txtcontentnotes.setText(note.getNoteContent());

        holder.layoutItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent1 = new Intent(context,NoteDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("object_note", note);
                intent1.putExtras(bundle);
                context.startActivity(intent1);
            }
        });

        holder.btnDeleteNoteF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder = new AlertDialog.Builder(context);
                //Uncomment the below code to Set the message and title from the strings.xml file
                builder.setMessage(R.string.dialog_message) .setTitle(R.string.dialog_title);

                //Setting message manually and performing action on button click
                builder.setMessage("Bạn có chắc rằng muốn xóa nó ?")
                        .setCancelable(false)
                        .setPositiveButton("Đồng ý!", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                //xóa khỏi danh sách
                                listNote.remove(holder.getAdapterPosition());
                                notifyItemRemoved(holder.getAdapterPosition());
                                String _noteId = note.getNoteId();
                                noteDAO =new NoteDAO(context);
                                noteDAO.open();
                                noteDAO.delete(_noteId,_idUserL);

                                Toast.makeText(context,"Đã xóa khỏi danh sách!",
                                        Toast.LENGTH_SHORT).show();
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
    }

    @Override
    public int getItemCount() {
        return listNote.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout layoutItem;
        TextView txttitlenotes;
        TextView txtdatenoteslast;
        TextView txtcontentnotes;
        ImageButton btnDeleteNoteF;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txttitlenotes = itemView.findViewById(R.id.txttitlenotes);
            txtdatenoteslast = itemView.findViewById(R.id.txtdatenoteslast);
            txtcontentnotes = itemView.findViewById(R.id.txtcontentnotes);
            layoutItem = itemView.findViewById(R.id.layout_item);
            btnDeleteNoteF = itemView.findViewById(R.id.btn_delete_note_f);
        }
    }

    public void release(){
        context = null;
    }

}
