package ua.nure.havrysh.lab1.ui.activity.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import ua.nure.havrysh.lab1.R;
import ua.nure.havrysh.lab1.ui.fragment.base.BaseFragment;
import ua.nure.havrysh.lab1.ui.router.DefaultRouter;
import ua.nure.havrysh.lab1.ui.router.Router;

public abstract class BaseFragmentActivity extends BaseActivity {

    private Router router;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: 28.09.2017 inject
        router = new DefaultRouter(this);
        replaceRootFragment(getFirstFragment());
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_base_fragment;
    }

    @NonNull
    protected abstract BaseFragment getFirstFragment();

    public void replaceRootFragment(@NonNull BaseFragment fragment) {
        router.setRootFragment(fragment);
    }

    public Router getRouter() {
        return router;
    }
}
