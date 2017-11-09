package ua.nure.havrysh.lab1.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import butterknife.BindView;
import ua.nure.havrysh.lab1.R;
import ua.nure.havrysh.lab1.db.Note;
import ua.nure.havrysh.lab1.db.Note_Table;
import ua.nure.havrysh.lab1.ui.NoteLevel;
import ua.nure.havrysh.lab1.ui.adapter.NotesAdapter;
import ua.nure.havrysh.lab1.ui.dialog.NoteContextDialog;
import ua.nure.havrysh.lab1.ui.fragment.base.BaseFragment;
import ua.nure.havrysh.lab1.ui.router.NotesRouter;

public class NotesFragment extends BaseFragment implements NotesAdapter.NoteListener,
        NoteContextDialog.NoteContextListener {
    @BindView(R.id.notes_recycler_view)
    RecyclerView notesRecyclerView;

    private SearchView searchView;

    private Spinner noteLevelSpinner;

    private final NotesAdapter notesAdapter = new NotesAdapter(this);

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        notesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        notesRecyclerView.setAdapter(notesAdapter);

        setHasOptionsMenu(true);
    }

    @Override
    public void onResume() {
        super.onResume();
        fetchNotes();
    }

    public void fetchNotes() {
        String query = searchView != null ? searchView.getQuery().toString().trim() : "";
        int minLevel = noteLevelSpinner != null ? noteLevelSpinner.getSelectedItemPosition() : 0;

        notesAdapter.replaceItems(SQLite.select()
                .from(Note.class)
                .where(Note_Table.name.like("%" + query + "%"))
                .and(Note_Table.level.greaterThanOrEq(minLevel))
                .queryList());
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.notes_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);

        searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                fetchNotes();
                return false;
            }
        });

        noteLevelSpinner = (Spinner) menu.findItem(R.id.note_level_spinner).getActionView();
        ArrayAdapter<NoteLevel> levelsAdapter = new ArrayAdapter<>(getActivity().getApplicationContext(),
                android.R.layout.simple_spinner_item,
                NoteLevel.LEVELS);
        levelsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        noteLevelSpinner.setAdapter(levelsAdapter);
        noteLevelSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                fetchNotes();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_add) {
            ((NotesRouter)getParentFragment()).showNote(0);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_notes;
    }

    @Override
    public void onNoteSelected(@NonNull Note note) {
        ((NotesRouter)getParentFragment()).showNote(note.getId());
    }

    @Override
    public void onNoteLongPress(@NonNull Note note) {
        NoteContextDialog dialog = NoteContextDialog.newInstance(note.getId());
        dialog.setTargetFragment(this, 0);
        dialog.show(getFragmentManager(), null);
    }

    @Override
    public void onDeleteNote(long noteId) {
        FlowManager.getModelAdapter(Note.class)
                .delete(new Note(noteId));
        fetchNotes();
    }

    @Override
    public void onEditNote(long noteId) {
        ((NotesRouter)getParentFragment()).showNote(noteId);
    }
}
