package com.nex3z.togglebuttongroup;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Checkable;

import com.nex3z.togglebuttongroup.models.ToggleButton;

import java.util.LinkedHashSet;
import java.util.Set;

public class MultiSelectToggleGroup extends ToggleButtonGroup {
    private static final String LOG_TAG = MultiSelectToggleGroup.class.getSimpleName();
    private int max_count = -1;
    private OnCheckedStateChangeListener mOnCheckedStateChangeListener;

    public MultiSelectToggleGroup(Context context) {
        super(context);
    }

    public MultiSelectToggleGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (mInitialCheckedId != View.NO_ID) {
            setCheckedStateForView(mInitialCheckedId, true);
        }
    }

    @Override
    protected <T extends View & Checkable> void onChildCheckedChange(T child, boolean isChecked) {
        notifyCheckedStateChange(child.getId(), isChecked);
    }

    public void check(int id) {
        setCheckedStateForView(id, true);
    }

    public void check(int id, boolean checked) {
        setCheckedStateForView(id, checked);
    }

    public void clearCheck() {
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            if (child instanceof ToggleButton) {
                ((ToggleButton) child).setChecked(false);
            }
        }
    }
 /*
    public void setCheckedItems(Set<Integer> ){
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            if (child instanceof ToggleButton && ((ToggleButton) child).isChecked()) {
                ids.add(child.getId());
            }
        }
    }*/

    public Set<Integer> getCheckedIds() {
        Set<Integer> ids = new LinkedHashSet<>();
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            if (child instanceof ToggleButton && ((ToggleButton) child).isChecked()) {
                ids.add(child.getId());
            }
        }
        return ids;
    }

    public int getCheckedCount() {
        int count = 0;
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            if (child instanceof ToggleButton && ((ToggleButton) child).isChecked()) {
                count++;
            }
        }
        return count;
    }

    public void toggle(int id) {
        toggleCheckedStateForView(id);
    }

    public void setOnCheckedChangeListener(OnCheckedStateChangeListener listener) {
        mOnCheckedStateChangeListener = listener;
    }

    private void notifyCheckedStateChange(int id, boolean isChecked) {
        if (mOnCheckedStateChangeListener != null) {
            mOnCheckedStateChangeListener.onCheckedStateChanged(this, id, isChecked);
        }
    }

    @Override
    protected <T extends View & Checkable> boolean canNowCheck(T view, int id_special) {
        if (max_count > -1) {
            return getCheckedCount() < max_count;
        } else {
            return super.canNowCheck(view, id_special);
        }
    }

    public interface OnCheckedStateChangeListener {
        void onCheckedStateChanged(MultiSelectToggleGroup group, int checkedId, boolean isChecked);
    }

    public void setMaxItems(int count) {
        max_count = count;
    }
}
