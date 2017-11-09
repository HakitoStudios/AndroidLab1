package ua.nure.havrysh.lab1.ui.router;

import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import ua.nure.havrysh.lab1.R;

public class DefaultRouter implements Router {
    @IdRes
    private int fragmentContainer = R.id.fragment_container;

    @NonNull
    private final FragmentManager fragmentManager;

    @NonNull
    private final AppCompatActivity activity;

    public DefaultRouter(@NonNull AppCompatActivity activity) {
        this.fragmentManager = activity.getSupportFragmentManager();
        this.activity = activity;
    }

    @Override
    public void setRootFragment(@NonNull Fragment fragment) {
        fragmentManager.beginTransaction()
                .replace(fragmentContainer, fragment)
                .commit();
    }

    @Override
    public void showFragment(@NonNull Fragment fragment) {
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void replaceFragment(@NonNull Fragment fragment) {
        fragmentManager.popBackStackImmediate();
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }

    @Override
    public void back() {
        if (fragmentManager.popBackStackImmediate()) {
            // TODO: 28.09.2017 log
        } else {
            activity.finish();
        }
    }
}
