package com.ytrsoft.simplified;

import com.sun.jna.platform.win32.WinNT;

public class VirtualQuery {

    private int state;
    private int protect;

    private boolean isWrite() {
        return state != WinNT.MEM_COMMIT || protect != WinNT.PAGE_READWRITE;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getProtect() {
        return protect;
    }

    public void setProtect(int protect) {
        this.protect = protect;
    }
}
