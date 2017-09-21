package com.nex3z.togglebuttongroup.models;

import android.widget.Checkable;

import com.nex3z.togglebuttongroup.models.OnCheckedChangeListener;

public interface ToggleButton extends Checkable {

    void setOnCheckedChangeListener(OnCheckedChangeListener listener);

}
