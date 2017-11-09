package ua.nure.havrysh.lab1.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;
import ua.nure.havrysh.lab1.R;
import ua.nure.havrysh.lab1.db.Note;
import ua.nure.havrysh.lab1.ui.NoteLevel;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NoteViewHolder> {

    private final List<Note> notes = new ArrayList<>();

    private final NoteListener noteListener;

    public NotesAdapter(NoteListener noteListener) {
        this.noteListener = noteListener;
    }

    @Override
    public NoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NoteViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_note, parent, false));
    }

    @Override
    public void onBindViewHolder(NoteViewHolder holder, int position) {
        holder.bind(notes.get(position));
    }

    public void replaceItems(@NonNull List<Note> newItems){
        notes.clear();
        notes.addAll(newItems);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    class NoteViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.note_name_text)
        TextView noteNameView;

        @BindView(R.id.note_photo)
        ImageView notePhotoImageView;

        @BindView(R.id.note_level_image)
        ImageView noteLevelImageView;

        @BindView(R.id.note_date_text)
        TextView noteDateView;

        private Note note;

        public NoteViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(@NonNull Note note) {
            this.note = note;
            noteNameView.setText(note.getName());
            noteDateView.setText(
                    DateUtils.getRelativeDateTimeString(itemView.getContext(),
                            note.getCreationDate(),
                            DateUtils.SECOND_IN_MILLIS,
                            DateUtils.DAY_IN_MILLIS,
                            0));
            Picasso.with(itemView.getContext())
                    .load(note.getPhotoPath())
                    .placeholder(android.R.drawable.ic_menu_camera)
                    .resize(100, 100)
                    .centerCrop()
                    .into(notePhotoImageView);
            noteLevelImageView.setImageResource(NoteLevel.LEVELS.get(note.getLevel()).getLevelIcon());

        }

        @OnClick(R.id.note_root)
        void onNoteClick(){
            noteListener.onNoteSelected(note);
        }

        @OnLongClick(R.id.note_root)
        boolean onNoteLongPress(){
            noteListener.onNoteLongPress(note);
            return true;
        }
    }

    public interface NoteListener{
        void onNoteSelected(@NonNull Note note);

        void onNoteLongPress(@NonNull Note note);
    }
}
