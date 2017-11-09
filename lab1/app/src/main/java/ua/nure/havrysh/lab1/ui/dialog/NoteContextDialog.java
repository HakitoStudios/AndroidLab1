package ua.nure.havrysh.lab1.ui.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import ua.nure.havrysh.lab1.R;

public class NoteContextDialog extends DialogFragment {
    private static final String NOTE_ID_ARG = "NOTE_ID_ARG";

    public static NoteContextDialog newInstance(long noteId) {
        Bundle args = new Bundle();
        args.putLong(NOTE_ID_ARG, noteId);
        NoteContextDialog fragment = new NoteContextDialog();
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        long noteId = getArguments().getLong(NOTE_ID_ARG);
        return new AlertDialog.Builder(getContext())
                .setItems(R.array.note_context_items, (dialogInterface, i) -> {
                    if (i == 0) {
                        ((NoteContextListener) getTargetFragment()).onEditNote(noteId);
                    } else {
                        ((NoteContextListener) getTargetFragment()).onDeleteNote(noteId);
                    }
                    dismissAllowingStateLoss();
                })
                .create();
    }

    public interface NoteContextListener {
        void onDeleteNote(long noteId);

        void onEditNote(long noteId);
    }
}
