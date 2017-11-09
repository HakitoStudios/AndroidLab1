package ua.nure.havrysh.lab1.ui.router;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

public interface Router {
    void setRootFragment(@NonNull Fragment fragment);

    void showFragment(@NonNull Fragment fragment);

    void replaceFragment(@NonNull Fragment fragment);

    void back();
}
