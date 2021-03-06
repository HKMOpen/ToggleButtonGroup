package com.nex3z.togglebuttongroup.models;

import android.view.View;
import android.widget.Checkable;

public interface OnCheckedChangeListener {

    <T extends View & Checkable> void onCheckedChanged(T view, boolean isChecked);

    <T extends View & Checkable> boolean canCheck(T view, int special_id);
}
