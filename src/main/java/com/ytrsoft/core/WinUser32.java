package com.ytrsoft.core;

import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.ptr.IntByReference;

public final class WinUser32 {

    private WinUser32() {
        throw new UnsupportedOperationException();
    }

    public static WinDef.HWND findWindow(String lpWindowName) {
        return User32.INSTANCE.FindWindow(null, lpWindowName);
    }

    public static int getWindowThreadProcessId(WinDef.HWND hWnd) {
        IntByReference lpdwProcessId = new IntByReference(0);
        User32.INSTANCE.GetWindowThreadProcessId(hWnd, lpdwProcessId);
        return lpdwProcessId.getValue();
    }
}
