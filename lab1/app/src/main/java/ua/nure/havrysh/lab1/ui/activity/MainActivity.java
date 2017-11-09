package ua.nure.havrysh.lab1.ui.activity;

import android.support.annotation.NonNull;

import ua.nure.havrysh.lab1.ui.activity.base.BaseFragmentActivity;
import ua.nure.havrysh.lab1.ui.fragment.HomeFragment;
import ua.nure.havrysh.lab1.ui.fragment.base.BaseFragment;

public class MainActivity extends BaseFragmentActivity {

    @NonNull
    @Override
    protected BaseFragment getFirstFragment() {
        return new HomeFragment();
    }
}
