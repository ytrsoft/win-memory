package com.ytrsoft.win32;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.win32.W32APIOptions;

public final class Win32Native {

    private Win32Native() {
        throw new UnsupportedOperationException();
    }

    public static <T extends Library> Library synchronizedLibrary(String name, Class<T> interfaceClass) {
        return Native.synchronizedLibrary(Native.load(name, interfaceClass, W32APIOptions.DEFAULT_OPTIONS));
    }

}
