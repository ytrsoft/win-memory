package com.ytrsoft.entity;

import com.sun.jna.platform.win32.WinNT;

public class ProcessToken {

    private WinNT.TOKEN_PRIVILEGES privileges;

    private WinNT.HANDLE handle;

    private boolean status;

    public WinNT.TOKEN_PRIVILEGES getPrivileges() {
        return privileges;
    }

    public void setPrivileges(WinNT.TOKEN_PRIVILEGES privileges) {
        this.privileges = privileges;
    }

    public WinNT.HANDLE getHandle() {
        return handle;
    }

    public void setHandle(WinNT.HANDLE handle) {
        this.handle = handle;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
