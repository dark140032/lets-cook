package com.example.letscook.ui.notes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.letscook.R;
import com.example.letscook.ui.wishlist.CongThuc;
import com.example.letscook.ui.wishlist.CongThucAdapter;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder>{
    Context context;
    ArrayList<Note> listNote;

    public NoteAdapter(Context applicationContext, ArrayList<Note> listNote) {
        this.context = context;
        this.listNote = listNote;
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
        holder.txttitlenotes.setText(note.getTitle());
        holder.txtdatenoteslast.setText(note.getDatelast());
        holder.txtcontentnotes.setText(note.getContent());
    }

    @Override
    public int getItemCount() {
        return listNote.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txttitlenotes;
        TextView txtdatenoteslast;
        TextView txtcontentnotes;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txttitlenotes = itemView.findViewById(R.id.txttitlenotes);
            txtdatenoteslast = itemView.findViewById(R.id.txtdatenoteslast);
            txtcontentnotes = itemView.findViewById(R.id.txtcontentnotes);
        }
    }

}
