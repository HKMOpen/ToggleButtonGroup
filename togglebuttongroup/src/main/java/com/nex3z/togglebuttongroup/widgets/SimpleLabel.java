package com.nex3z.togglebuttongroup.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.TypedValue;

import com.nex3z.togglebuttongroup.R;
import com.nex3z.togglebuttongroup.models.Label;

/**
 * Created by hesk on 21/9/2017.
 */

public class SimpleLabel extends Label {
    private Drawable d_state_list;

    public SimpleLabel(Context context) {
        super(context);
    }

    public SimpleLabel(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void init(Context context, AttributeSet attrs) {
        super.init(context, attrs);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.MarkerButton, 0, 0);
        try {
            //  CharSequence text = a.getText(R.styleable.MarkerButton_android_text);
            d_state_list = a.getDrawable(R.styleable.MarkerButton_togglebackground);
            if (d_state_list == null) {
                initBackground();
                setBackground(null);
            } else {
                setCheckedImageDrawable(d_state_list);
                mIvBg.setVisibility(VISIBLE);
            }
        } finally {
            a.recycle();
        }
    }

    @Override
    public void setChecked(boolean checked) {
        super.setChecked(checked);
        if (checked) {
            mIvBg.setImageState(CHECKED_STATE_SET, true);
        } else {
            mIvBg.setImageState(UNCHECKED_STATE_SET, true);
        }
    }

    @Override
    protected void init() {
        //initBackground();
        //initAnimationTypeSimpleLabel();
        mIvBg.setVisibility(VISIBLE);
    }
}
