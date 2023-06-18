package com.ytrsoft.util.img;

import com.sun.jna.Structure;
import com.sun.jna.platform.win32.WinUser;

public class SIZEByValue extends WinUser.SIZE implements Structure.ByValue {
    public SIZEByValue(int w, int h) {
        super(w, h);
    }
}