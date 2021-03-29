package com.ytrsoft.win32;

import com.sun.jna.platform.win32.WinDef;

public final class JUser32Api {

    private JUser32Api() {
        throw new UnsupportedOperationException();
    }

    public static WinDef.HWND findWindow(String lpClassName, String lpWindowName) {
        return JUser32.SYNC_INSTANCE.FindWindowA(lpClassName, lpWindowName);
    }

    public static int getWindowThreadProcessId(WinDef.HWND hWnd) {
        int[] lpdwProcessId = new int[1];
        JUser32.SYNC_INSTANCE.GetWindowThreadProcessId(hWnd, lpdwProcessId);
        return lpdwProcessId[0];
    }

}
