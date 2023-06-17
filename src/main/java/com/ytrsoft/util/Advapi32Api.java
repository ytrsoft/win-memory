package com.ytrsoft.util;

import com.sun.jna.platform.win32.Advapi32;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinNT;
import com.ytrsoft.entity.ProcessToken;

public class Advapi32Api {

    private static final Advapi32 INSTANCE = Advapi32.INSTANCE;

    private Advapi32Api() {
        throw new UnsupportedOperationException();
    }

    /**
     * 打开进程的访问令牌
     * @param processHandle 进程句柄
     * @return 进程令牌信息
     */
    public ProcessToken openProcessToken(WinNT.HANDLE processHandle) {
        ProcessToken processToken = new ProcessToken();
        WinNT.HANDLEByReference tokenHandle = new WinNT.HANDLEByReference();
        WinNT.TOKEN_PRIVILEGES privileges = new WinNT.TOKEN_PRIVILEGES(1);
        privileges.Privileges[0] = new WinNT.LUID_AND_ATTRIBUTES();
        privileges.Privileges[0].Luid = new WinNT.LUID();
        privileges.Privileges[0].Attributes = new WinDef.DWORD(WinNT.SE_PRIVILEGE_ENABLED);
        boolean status = INSTANCE.OpenProcessToken(
            processHandle,
            WinNT.TOKEN_ADJUST_PRIVILEGES,
            tokenHandle
        );
        processToken.setPrivileges(privileges);
        processToken.setStatus(status);
        if (status) {
            processToken.setHandle(tokenHandle.getValue());
        }
        return processToken;
    }

    /**
     * 查找特权的本地唯一标识符（LUID）
     * @param lpName 特权名称
     * @param token  进程令牌信息
     * @return 操作是否成功
     */
    public boolean lookupPrivilegeValue(String lpName, ProcessToken token) {
        return INSTANCE.LookupPrivilegeValue(
            null,
            lpName,
            token.getPrivileges().Privileges[0].Luid
        );
    }

    /**
     * 调整进程令牌的特权
     * @param token 进程令牌信息
     * @return 操作是否成功
     */
    public boolean adjustTokenPrivileges(ProcessToken token) {
        return INSTANCE.AdjustTokenPrivileges(
            token.getHandle(),
            false,
            token.getPrivileges(),
            token.getPrivileges().size(),
            null,
            null
        );
    }
}
