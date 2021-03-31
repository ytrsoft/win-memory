package com.ytrsoft.win32;

import com.sun.jna.platform.win32.Win32Exception;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.ptr.IntByReference;

public final class JUser32Api {

    private JUser32Api() {
        throw new UnsupportedOperationException();
    }

    public static HWND findWindow(String lpClassName, String lpWindowName) {
        HWND hWnd = JUser32.SYNC_INSTANCE.FindWindow(lpClassName, lpWindowName);
        if (hWnd == null) {
            throw new Win32Exception(JKernel32.SYNC_INSTANCE.GetLastError());
        }
        return hWnd;
    }

    public static int getWindowThreadProcessId(HWND hWnd) {
        IntByReference lpdwProcessId = new IntByReference(0);
        JUser32.SYNC_INSTANCE.GetWindowThreadProcessId(hWnd, lpdwProcessId);
        return lpdwProcessId.getValue();
    }

}
