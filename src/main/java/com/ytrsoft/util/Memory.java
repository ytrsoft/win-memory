package com.ytrsoft.util;

import com.google.common.primitives.Bytes;
import com.google.common.primitives.Ints;

import java.util.Arrays;
import java.util.Collections;

public class Memory {

    private Memory() {
        throw new UnsupportedOperationException();
    }

    public static int[] bytesToUnsignedInts(byte[] bytes) {
        int[] readValues = new int[bytes.length];
        for (int i = 0; i < bytes.length ; i++) {
            readValues[i] = bytes[i] & 0xFF;
        }
        return readValues;
    }

    public static String[] bytesToUnsignedHexes(byte[] bytes) {
        String[] readValues = new String[bytes.length];
        for (int i = 0; i < bytes.length ; i++) {
            readValues[i] = Integer.toHexString(bytes[i] & 0xFF);
        }
        return readValues;
    }

    public static int bytesToUnsignedInt(byte[] bytes) {
        byte[] copy = Arrays.copyOf(bytes, bytes.length);
        Collections.reverse(Bytes.asList(copy));
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
        long a = values[0] << 24;
        long b = values[1] << 16;
        long c = values[2] << 9;
        long d = values[3];
        return a | b | c | d;
    }

}
