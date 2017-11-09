package ua.nure.havrysh.lab1.ui.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import ua.nure.havrysh.lab1.R;
import ua.nure.havrysh.lab1.ui.activity.base.BaseFragmentActivity;
import ua.nure.havrysh.lab1.ui.fragment.HomeFragment;
import ua.nure.havrysh.lab1.ui.fragment.base.BaseFragment;

public class MainActivity extends BaseFragmentActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        // TODO: 22.10.2017 Move strings to res
        String theme = pref.getString("theme", "Theme1");
        if (theme.equals("Theme1")) {
            setTheme(R.style.AppTheme1);
        } else if (theme.equals("Theme2")) {
            setTheme(R.style.AppTheme2);
        }
        String textSize = pref.getString("textSize", "Medium");
        switch (textSize) {
            case "Small":
                getTheme().applyStyle(R.style.SmallTextSize, false);
                break;
            case "Medium":
                getTheme().applyStyle(R.style.MediumTextSize, false);
                break;
            case "Large":
                getTheme().applyStyle(R.style.LargeTextSize, false);
                break;
        }
        super.onCreate(savedInstanceState);
    }

    @NonNull
    @Override
    protected BaseFragment getFirstFragment() {
        return new HomeFragment();
    }
}
