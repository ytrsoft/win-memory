package com.ytrsoft.util;

import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.PointerType;

public class RefFree {

    private RefFree() {
        throw new UnsupportedOperationException();
    }

    /**
     * 释放由指定的PointerType对象分配的内存
     * @param pt 表示分配内存的PointerType对象
     */
    public static void free(PointerType pt) {
        free(pt.getPointer());
    }

    /**
     * 释放由指定的Pointer对象分配的内存。
     * @param p 表示分配内存的Pointer对象。
     */
    public static void free(Pointer p) {
        // 释放与指针关联的本机内存
        Native.free(Pointer.nativeValue(p));
        // 如果不将指针的本机值设置为0，可能会导致不可预料的错误
        // 当Java GC尝试回收这个指针时，可能会导致程序崩溃
        Pointer.nativeValue(p, 0);
    }
}