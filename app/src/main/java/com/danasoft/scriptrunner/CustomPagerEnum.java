package com.danasoft.scriptrunner;

public enum CustomPagerEnum {

    EDIT(R.string.edit, R.layout.edit_layout),
    RUN(R.string.run, R.layout.run_layout);

    private int mTitleResId;
    private int mLayoutResId;

    CustomPagerEnum(int titleResId, int layoutResId) {
        mTitleResId = titleResId;
        mLayoutResId = layoutResId;
    }

    public int getTitleResId() {
        return mTitleResId;
    }

    public int getLayoutResId() {
        return mLayoutResId;
    }

}
