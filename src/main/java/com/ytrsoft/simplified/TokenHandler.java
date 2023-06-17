package com.ytrsoft.simplified;

import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinNT;

public class TokenHandler {

    private final WinNT.HANDLEByReference tokenHandle;
    private final WinNT.TOKEN_PRIVILEGES privileges;

    public TokenHandler() {
        tokenHandle = new WinNT.HANDLEByReference();
        privileges = new WinNT.TOKEN_PRIVILEGES(1);
        privileges.Privileges[0] = new WinNT.LUID_AND_ATTRIBUTES();
        privileges.Privileges[0].Luid = new WinNT.LUID();
        privileges.Privileges[0].Attributes = new WinDef.DWORD(WinNT.SE_PRIVILEGE_ENABLED);
    }

    public WinNT.HANDLEByReference getTokenHandle() {
        return tokenHandle;
    }

    public WinNT.TOKEN_PRIVILEGES getPrivileges() {
        return privileges;
    }
}
