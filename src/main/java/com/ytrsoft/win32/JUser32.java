package com.ytrsoft.win32;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.win32.StdCallLibrary;

public interface JUser32 extends StdCallLibrary {

    JUser32 INSTANCE = Native.load("user32", JUser32.class);

    JUser32 SYNC_INSTANCE = (JUser32) Native.synchronizedLibrary(INSTANCE);

    WinDef.HWND FindWindowA(String lpClassName, String lpWindowName);

    int GetWindowThreadProcessId(WinDef.HWND hWnd, int[] lpdwProcessId);

}
