package com.ytrsoft.win32;

import com.sun.jna.platform.win32.User32;

public interface JUser32 extends User32 {

    JUser32 SYNC_INSTANCE = (JUser32) Win32Native.synchronizedLibrary("user32", JUser32.class);

}
