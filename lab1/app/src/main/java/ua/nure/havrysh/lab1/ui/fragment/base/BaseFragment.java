package ua.nure.havrysh.lab1.ui.fragment.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import ua.nure.havrysh.lab1.ui.activity.base.BaseActivity;
import ua.nure.havrysh.lab1.ui.activity.base.BaseFragmentActivity;
import ua.nure.havrysh.lab1.ui.router.Router;

public abstract class BaseFragment extends Fragment {

    @Nullable
    private Unbinder unbinder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutRes(), container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        if (unbinder != null) {
            unbinder.unbind();
        }
        super.onDestroyView();
    }

    @LayoutRes
    protected abstract int getLayoutRes();

    @NonNull
    private BaseFragmentActivity getBaseFragmentActivity() {
        return (BaseFragmentActivity) getActivity();
    }

    @NonNull
    protected Router getRouter(){
        return getBaseFragmentActivity().getRouter();
    }
}
