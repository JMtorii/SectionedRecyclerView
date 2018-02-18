package com.juntorii.testapplication;

public class IndexPath {
    public static final int INVALID = -1;

    public int section;
    public int row;

    public IndexPath(int section, int row) {
        this.section = section;
        this.row = row;
    }
}
