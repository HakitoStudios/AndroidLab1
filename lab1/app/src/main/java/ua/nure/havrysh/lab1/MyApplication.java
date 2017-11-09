package ua.nure.havrysh.lab1;

import android.app.Application;

import com.raizlabs.android.dbflow.config.FlowManager;

import ua.nure.havrysh.lab1.ui.NoteLevel;

public class MyApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        FlowManager.init(this);
        for (NoteLevel noteLevel:NoteLevel.LEVELS){
            noteLevel.setName(getString(noteLevel.getLevelName()));
        }
    }
}
