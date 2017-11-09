package ua.nure.havrysh.lab1.ui;

import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import ua.nure.havrysh.lab1.R;

public class NoteLevel {

    public static final List<NoteLevel> LEVELS = Collections.unmodifiableList(
            Arrays.asList(new NoteLevel(R.drawable.ic_motorbike, R.string.low),
                    new NoteLevel(android.R.drawable.stat_notify_error, R.string.mid),
                    new NoteLevel(android.R.drawable.stat_notify_more, R.string.high)
            )
    );

    @DrawableRes
    private final int levelIcon;

    @StringRes
    private final int levelName;

    private String name;

    public NoteLevel(@DrawableRes int levelIcon, @StringRes int levelName) {
        this.levelIcon = levelIcon;
        this.levelName = levelName;
    }

    @DrawableRes
    public int getLevelIcon() {
        return levelIcon;
    }

    @StringRes
    public int getLevelName() {
        return levelName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
