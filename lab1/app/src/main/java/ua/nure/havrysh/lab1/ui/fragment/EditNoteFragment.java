package ua.nure.havrysh.lab1.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.text.format.DateUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.OnClick;
import ua.nure.havrysh.lab1.R;
import ua.nure.havrysh.lab1.db.Note;
import ua.nure.havrysh.lab1.db.Note_Table;
import ua.nure.havrysh.lab1.ui.NoteLevel;
import ua.nure.havrysh.lab1.ui.fragment.base.BaseFragment;
import ua.nure.havrysh.lab1.ui.router.NotesRouter;

import static android.app.Activity.RESULT_OK;

public class EditNoteFragment extends BaseFragment {
    private static final String NOTE_ID_ARG = "NOTE_ID_ARG";
    private static final String VIEW_ONLY_ARG = "VIEW_ONLY_ARG";
    private static final int PICK_IMAGE = 13;

    public static EditNoteFragment newInstance(long noteId, boolean viewOnly) {
        Bundle args = new Bundle();
        args.putLong(NOTE_ID_ARG, noteId);
        args.putBoolean(VIEW_ONLY_ARG, viewOnly);
        EditNoteFragment fragment = new EditNoteFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private Note note;

    @BindView(R.id.note_name_edit_text)
    EditText noteNameEditText;

    @BindView(R.id.description_edit_text)
    EditText noteDescriptionEditText;

    @BindView(R.id.note_photo)
    ImageView notePhotoImageView;

    @BindView(R.id.note_date_text)
    TextView noteDateTextView;

    @BindView(R.id.note_level_spinner)
    Spinner noteLevelSpinner;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ArrayAdapter<NoteLevel> levelsAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, NoteLevel.LEVELS);
        levelsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        noteLevelSpinner.setAdapter(levelsAdapter);

        long noteId = getArguments() != null ? getArguments().getLong(NOTE_ID_ARG) : 0;
        showNote(noteId);
        setHasOptionsMenu(true);
    }

    public void showNote(long id) {
        if (id == 0) {
            note = new Note();
            note.setCreationDate(System.currentTimeMillis());
            noteLevelSpinner.setSelection(0);
            noteNameEditText.setText("");
            noteDescriptionEditText.setText("");
            noteDateTextView.setText("");
            notePhotoImageView.setImageResource(android.R.drawable.ic_menu_camera);
        } else {
            note = SQLite.select()
                    .from(Note.class)
                    .where(Note_Table.id.eq(id))
                    .querySingle();
            noteLevelSpinner.setSelection(note.getLevel());
            noteNameEditText.setText(note.getName());
            noteDescriptionEditText.setText(note.getDescription());
            showPhoto();
            showDate();
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.edit_note_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_save) {
            saveNote();
        }
        return super.onOptionsItemSelected(item);
    }

    private void showDate() {
        noteDateTextView.setText(DateUtils.getRelativeDateTimeString(getContext(),
                note.getCreationDate(),
                DateUtils.SECOND_IN_MILLIS,
                DateUtils.DAY_IN_MILLIS,
                0));
    }

    private void saveNote() {
        note.setLevel(noteLevelSpinner.getSelectedItemPosition());
        note.setName(noteNameEditText.getText().toString());
        note.setDescription(noteDescriptionEditText.getText().toString());
        FlowManager.getModelAdapter(Note.class)
                .save(note);
        if(getParentFragment()==null) {
            getRouter().back();
        }else{
            ((NotesRouter)getParentFragment()).showNoteList();
        }
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_edit_note;
    }

    @OnClick(R.id.set_photo_btn)
    void onSetPhotoClick() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, getString(R.string.select_picture)), PICK_IMAGE);
    }

    private void showPhoto() {
        Picasso.with(getContext())
                .load(note.getPhotoPath())
                .placeholder(android.R.drawable.ic_menu_camera)
                .into(notePhotoImageView);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PICK_IMAGE) {
            if (resultCode == RESULT_OK) {
                note.setPhotoPath(data.getData().toString());
                showPhoto();
            } else {
                //none
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @OnClick(R.id.note_photo)
    void onPhotoClick() {
        ImageView imageView = new ImageView(getContext());
        imageView.setAdjustViewBounds(true);
        Picasso.with(getContext())
                .load(note.getPhotoPath())
                .placeholder(android.R.drawable.ic_menu_camera)
                .into(imageView);
        new AlertDialog.Builder(getContext())
                .setView(imageView)
                .setPositiveButton(R.string.ok, null)
                .create()
                .show();
    }
}
