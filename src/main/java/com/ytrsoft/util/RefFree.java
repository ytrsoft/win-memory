package com.ytrsoft.util;

import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.PointerType;
import org.apache.commons.lang3.ObjectUtils;

/**
 * 释放指针
 * 注意：重复调用会导致GC崩溃
 * 释放完及时置为null
 */
public class RefFree {

    private RefFree() {
        throw new UnsupportedOperationException();
    }

    /**
     * 释放指定PointerType对象所指向的内存
     * @param pt 需要释放内存的PointerType对象
     */
    public static void free(PointerType pt) {
        if (ObjectUtils.isNotEmpty(pt)) {
            free(pt.getPointer());
        }
    }

    /**
     * 释放指定Pointer对象所指向的内存
     * @param p 需要释放内存的Pointer对象
     */
    public static void free(Pointer p) {
        if (ObjectUtils.isNotEmpty(p)) {
            Native.free(Pointer.nativeValue(p));
            Pointer.nativeValue(p, 0);
        }
    }

}