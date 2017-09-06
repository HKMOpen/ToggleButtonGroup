package com.nex3z.togglebuttongroup.preset;

import android.content.Context;
import android.util.AttributeSet;

import com.nex3z.togglebuttongroup.button.OnCheckedChangeListener;
import com.nex3z.togglebuttongroup.button.ToggleButton;


/**
 * Created by hesk on 6/9/2017.
 */

public class ImageToggle extends android.support.v7.widget.AppCompatImageButton implements ToggleButton {
    private boolean mChecked;
    private boolean mBroadcasting;
    private OnCheckedChangeListener mOnCheckedWidgetListener;

    public ImageToggle(Context context) {
        this(context, null);
        setClickable(true);
    }

    public ImageToggle(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        setClickable(true);
    }

    public ImageToggle(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setClickable(true);
    }


    @Override
    protected void drawableStateChanged() {
        super.drawableStateChanged();
    }

    @Override
    public boolean performClick() {
        toggle();
        playSoundEffect(android.view.SoundEffectConstants.CLICK);
        return super.performClick();
    }

    @Override
    public void setOnCheckedChangeListener(OnCheckedChangeListener listener) {
        mOnCheckedWidgetListener = listener;
    }

    @Override
    public void setChecked(boolean checked) {
        if (mChecked != checked) {
            mChecked = checked;
            setSelected(checked);
            refreshDrawableState();
            if (mBroadcasting) {
                return;
            }
            mBroadcasting = true;
            if (mOnCheckedWidgetListener != null) {
                mOnCheckedWidgetListener.onCheckedChanged(this, mChecked);
            }
            mBroadcasting = false;
        }
    }

    @Override
    public boolean isChecked() {
        return mChecked;
    }

    @Override
    public void toggle() {
        setChecked(!mChecked);
    }
}
