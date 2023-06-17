package com.ytrsoft.util.share;

import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.PointerType;

/**
 * 释放指针
 */
public class RefFree {

    private RefFree() {
        throw new UnsupportedOperationException();
    }

    /**
     * 释放指定PointerType对象所指向的内存。
     * @param pt 需要释放内存的PointerType对象
     */
    public static void free(PointerType pt) {
        if (pt != null) {
            free(pt.getPointer());
        }
    }

    /**
     * 释放指定Pointer对象所指向的内存。
     * @param p 需要释放内存的Pointer对象
     */
    public static void free(Pointer p) {
        if (p != null) {
            Native.free(Pointer.nativeValue(p));
            Pointer.nativeValue(p, 0);
        }
    }

}