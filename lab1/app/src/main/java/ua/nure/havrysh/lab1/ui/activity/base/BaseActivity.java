package ua.nure.havrysh.lab1.ui.activity.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import ua.nure.havrysh.lab1.R;

public abstract class BaseActivity extends AppCompatActivity {

    @Nullable
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutRes());
        ButterKnife.bind(this);

        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
    }

    @LayoutRes
    protected abstract int getLayoutRes();
}
