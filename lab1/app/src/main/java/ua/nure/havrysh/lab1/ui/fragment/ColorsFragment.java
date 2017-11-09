package ua.nure.havrysh.lab1.ui.fragment;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.SeekBar;

import java.util.Random;

import butterknife.BindView;
import ua.nure.havrysh.lab1.R;
import ua.nure.havrysh.lab1.ui.fragment.base.BaseFragment;

public class ColorsFragment extends BaseFragment {

    @BindView(R.id.color_view)
    View colorView;

    @BindView(R.id.r_seek)
    SeekBar rSeekBar;

    @BindView(R.id.g_seek)
    SeekBar gSeekBar;

    @BindView(R.id.b_seek)
    SeekBar bSeekBar;

    private SeekBar dfdf;

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_colors;
    }

    private SeekBar.OnSeekBarChangeListener onSeekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
            invalidateColor();
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rSeekBar.setOnSeekBarChangeListener(onSeekBarChangeListener);
        gSeekBar.setOnSeekBarChangeListener(onSeekBarChangeListener);
        bSeekBar.setOnSeekBarChangeListener(onSeekBarChangeListener);
        Random random = new Random();
        rSeekBar.setProgress(random.nextInt(256));
        gSeekBar.setProgress(random.nextInt(256));
        bSeekBar.setProgress(random.nextInt(256));
        invalidateColor();
    }

    private void invalidateColor() {
        Drawable drawable = new ColorDrawable(Color.argb(255,
                rSeekBar.getProgress(),
                gSeekBar.getProgress(),
                bSeekBar.getProgress()));
        colorView.setBackground(drawable);
    }
}
