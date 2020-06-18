package com.example.korailauto;

import android.widget.TableRow;

public interface ITrain {
    public abstract boolean reserve();
    public abstract void makeTrainData(String d);
    public abstract int getTrainType();
    public abstract String[] getTimeInfo();
    public abstract String[] getFromTo();
    public abstract TableRow makeRow();
}
