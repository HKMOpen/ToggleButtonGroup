package com.nex3z.togglebuttongroup.models;

import com.nex3z.togglebuttongroup.MultiSelectToggleGroup;

/**
 * Created by hesk on 1/12/2017.
 */

public interface OnBeforeOnCheckChange {
    boolean beforeCheck(MultiSelectToggleGroup group, int checkedId);
}
