package com.ytrsoft.util;

import com.google.common.primitives.Ints;

import java.util.*;

public final class Bytes {

    private Bytes() {
        throw new UnsupportedOperationException();
    }

    public static int[] toUnsignedInts(byte[] bytes) {
        int[] readValues = new int[bytes.length];
        for (int i = 0; i < bytes.length ; i++) {
            readValues[i] = bytes[i] & 0xFF;
        }
        return readValues;
    }

    public static String[] toUnsignedHexes(byte[] bytes) {
        String[] readValues = new String[bytes.length];
        for (int i = 0; i < bytes.length ; i++) {
            readValues[i] = Integer.toHexString(bytes[i] & 0xFF);
        }
        return readValues;
    }

    public static int toUnsignedInt(byte[] bytes) {
        byte[] copy = Arrays.copyOf(bytes, bytes.length);
        Collections.reverse(com.google.common.primitives.Bytes.asList(copy));
        return Ints.fromByteArray(copy);
    }

    public static int unsignedShortToInt(byte[] bytes) {
        int low = bytes[0] & 0xFF;
        int high = bytes[1] & 0XFF;
        return high << 8 | low;
    }

    public static long unsignedIntToLong(byte[] bytes) {
        long[] values = new long[4];
        for (int i = 0; i < values.length ; i++) {
            values[i] = bytes[i] & 0XFF;
        }
        return (values[0] << 24) | (values[1] << 16) | (values[2] << 8) | values[3];
    }

}
