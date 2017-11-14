package com.nex3z.togglebuttongroup.models;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

/**
 * Created by hesk on 21/9/2017.
 */

public abstract class Label extends MarkerButton implements ToggleButton {
    private static final String LOG_TAG = Label.class.getSimpleName();

    private static final int DEFAULT_ANIMATION_DURATION = 150;

    private long mAnimationDuration = DEFAULT_ANIMATION_DURATION;
    private Animation mCheckAnimation;
    private Animation mUncheckAnimation;
    private ValueAnimator mTextColorAnimator;

    public Label(Context context) {
        this(context, null);
    }

    public Label(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
        init();
    }

    public Label(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
        init();
    }

    private int checkedTextColor;
    private int defaultTextColor;
    private boolean use_animation = false;

    protected void init(Context context, AttributeSet attrs) {
        defaultTextColor = getDefaultTextColor();
        checkedTextColor = getCheckedTextColor();
    }


    protected abstract void init();

    protected void initBackground() {
        GradientDrawable checked = new GradientDrawable();
        checked.setColor(mMarkerColor);
        checked.setCornerRadius(dpToPx(25));
        checked.setStroke(1, mMarkerColor);
        mIvBg.setImageDrawable(checked);

        GradientDrawable unchecked = new GradientDrawable();
        unchecked.setColor(ContextCompat.getColor(getContext(), android.R.color.transparent));
        unchecked.setCornerRadius(dpToPx(25));
        unchecked.setStroke((int) dpToPx(1), mMarkerColor);
        mTvText.setBackgroundDrawable(unchecked);

        int padding = (int) dpToPx(16);
        mTvText.setPadding(padding, 0, padding, 0);
    }


    protected void initAnimation() {
        use_animation = true;
        mTextColorStateList.getDefaultColor();
        mTextColorAnimator = ValueAnimator.ofObject(new ArgbEvaluator(), defaultTextColor, checkedTextColor);
        mTextColorAnimator.setDuration(mAnimationDuration);
        mTextColorAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                mTvText.setTextColor((Integer) valueAnimator.getAnimatedValue());
            }
        });
        mCheckAnimation = new AlphaAnimation(0, 1);
        mCheckAnimation.setDuration(mAnimationDuration);
        mCheckAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mTvText.setTextColor(checkedTextColor);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });

        mUncheckAnimation = new AlphaAnimation(1, 0);
        mUncheckAnimation.setDuration(mAnimationDuration);
        mUncheckAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mIvBg.setVisibility(INVISIBLE);
                mTvText.setTextColor(defaultTextColor);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
    }

    @Override
    public boolean performClick() {
        playSoundEffect(android.view.SoundEffectConstants.CLICK);
        return super.performClick();
    }

    @Override
    public void setChecked(boolean checked) {
        super.setChecked(checked);
        if (use_animation) {
            if (checked) {
                mIvBg.setVisibility(VISIBLE);
                mIvBg.startAnimation(mCheckAnimation);
                mTextColorAnimator.start();
            } else {
                mIvBg.setVisibility(VISIBLE);
                mIvBg.startAnimation(mUncheckAnimation);
                mTextColorAnimator.reverse();
            }
        } else {
            if (checked) {
                mTvText.setTextColor(checkedTextColor);
            } else {
                mTvText.setTextColor(defaultTextColor);
            }
        }
    }
}
