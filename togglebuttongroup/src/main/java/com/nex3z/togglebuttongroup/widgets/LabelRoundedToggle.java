package com.nex3z.togglebuttongroup.widgets;

import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.Animation;

import com.nex3z.togglebuttongroup.models.Label;

public class LabelRoundedToggle extends Label {
    private static final String LOG_TAG = LabelRoundedToggle.class.getSimpleName();

    private static final int DEFAULT_ANIMATION_DURATION = 150;

    private long mAnimationDuration = DEFAULT_ANIMATION_DURATION;
    private Animation mCheckAnimation;
    private Animation mUncheckAnimation;
    private ValueAnimator mTextColorAnimator;

    public LabelRoundedToggle(Context context) {
        this(context, null);
    }

    public LabelRoundedToggle(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public LabelRoundedToggle(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    @Override
    protected void init() {
        initBackground();
        initAnimation();
    }


}
