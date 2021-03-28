package com.ytrsoft.util;


public final class Strings {

    private Strings() {
        throw new UnsupportedOperationException();
    }

    public static String notEmptySelf(String str) {
        return isEmpty(str) ? null : str;
    }

    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }
}
