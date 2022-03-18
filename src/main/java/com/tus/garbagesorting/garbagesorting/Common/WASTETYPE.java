package com.tus.garbagesorting.garbagesorting.Common;

public enum WASTETYPE {
    Recycle,
    Organic,
    Trash;

    public static WASTETYPE fromInteger(int x) {
        switch (x) {
            case 0:
                return Recycle;
            case 1:
                return Organic;
            case 2:
                return Trash;
        }
        return null;
    }
}
