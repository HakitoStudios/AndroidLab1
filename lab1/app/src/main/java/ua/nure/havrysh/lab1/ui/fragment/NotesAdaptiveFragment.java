package ua.nure.havrysh.lab1.ui.fragment;

import android.os.Bundle;
import android.provider.FontsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.view.View;

import ua.nure.havrysh.lab1.R;
import ua.nure.havrysh.lab1.ui.fragment.base.BaseFragment;
import ua.nure.havrysh.lab1.ui.router.NotesRouter;

public class NotesAdaptiveFragment extends BaseFragment implements NotesRouter {


    private NotesFragment notesFragment;

    private EditNoteFragment editNoteFragment;

    private FragmentManager fragmentManager;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        fragmentManager = getChildFragmentManager();
        notesFragment = (NotesFragment) fragmentManager.findFragmentById(R.id.notes_fragment);
        editNoteFragment = (EditNoteFragment) fragmentManager.findFragmentById(R.id.edit_note_fragment);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_notes_adaptive;
    }

    @Override
    public void showNote(long id) {
        if (editNoteFragment != null) {
            editNoteFragment.showNote(id);
        } else {
            getRouter().showFragment(EditNoteFragment.newInstance(id, false));
        }
    }

    @Override
    public void showNoteList() {
        if (editNoteFragment != null) {
            notesFragment.fetchNotes();
        }
        else{
            //none
        }
    }
}
