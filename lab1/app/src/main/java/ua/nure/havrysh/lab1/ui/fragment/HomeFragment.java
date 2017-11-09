package ua.nure.havrysh.lab1.ui.fragment;

import android.content.Intent;

import butterknife.OnClick;
import ua.nure.havrysh.lab1.R;
import ua.nure.havrysh.lab1.ui.activity.PreferenceActivity;
import ua.nure.havrysh.lab1.ui.fragment.base.BaseFragment;

public class HomeFragment extends BaseFragment {
    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_home;
    }

    @OnClick(R.id.colors_btn)
    void onColorsClick(){
        getRouter().showFragment(new ColorsFragment());
    }

    @OnClick(R.id.calc_btn)
    void onCalcClick(){
        getRouter().showFragment(new CalcFragment());
    }

    @OnClick(R.id.notes_btn)
    void onNotesClick(){
        getRouter().showFragment(new NotesAdaptiveFragment());
    }

    @OnClick(R.id.settings_btn)
    void onSettingsClick(){
        startActivity(new Intent(getContext(), PreferenceActivity.class));
    }
}
