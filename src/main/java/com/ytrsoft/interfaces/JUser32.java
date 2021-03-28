package com.ytrsoft.interfaces;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.win32.StdCallLibrary;
import com.ytrsoft.util.Strings;

public class JUser32 {

    public interface User32 extends StdCallLibrary {

        User32 INSTANCE = Native.load("user32", User32.class);

        User32 SYNC_INSTANCE = (User32)Native.synchronizedLibrary(INSTANCE);

        HWND FindWindowA(String lpClassName, String lpWindowName);

        int GetWindowThreadProcessId(HWND hWnd, int[] lpdwProcessId);

    }

    public static HWND findWindow(String lpClassName, String lpWindowName) {
        return User32.SYNC_INSTANCE.FindWindowA(
            Strings.notEmptySelf(lpClassName),
            Strings.notEmptySelf(lpWindowName)
        );
    }

    public static int getWindowThreadProcessId(HWND hWnd) {
        int[] lpdwProcessId = new int[1];
        User32.SYNC_INSTANCE.GetWindowThreadProcessId(hWnd, lpdwProcessId);
        return lpdwProcessId[0];
    }
}
