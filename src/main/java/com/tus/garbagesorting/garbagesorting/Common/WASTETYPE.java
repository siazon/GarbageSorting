package com.tus.garbagesorting.garbagesorting.Common;

public enum WASTETYPE {
    defult,
    Recycle,
    Organic,
    Trash;

    public static WASTETYPE fromInteger(int x) {
        switch (x) {
            case 0:
                return defult;
            case 1:
                return Recycle;
            case 2:
                return Organic;
            case 3:
                return Trash;
        }
        return null;
    }
}
