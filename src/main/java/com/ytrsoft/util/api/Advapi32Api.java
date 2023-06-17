package com.ytrsoft.util.api;

import com.sun.jna.platform.win32.Advapi32;
import com.sun.jna.platform.win32.WinNT;
import com.ytrsoft.simplified.ProcessToken;
import com.ytrsoft.simplified.TokenHandler;

/**
 * Advapi32 API工具类
 */
public class Advapi32Api {

    private static final Advapi32 CTX = Advapi32.INSTANCE;

    private Advapi32Api() {
        throw new UnsupportedOperationException();
    }

    /**
     * 打开进程的访问令牌
     * @param handle 进程句柄
     * @return 表示进程访问令牌的ProcessToken对象
     */
    public ProcessToken openProcessToken(WinNT.HANDLE handle) {
        ProcessToken token = new ProcessToken();
        TokenHandler handler = new TokenHandler();
        boolean status = CTX.OpenProcessToken(
            handle,
            WinNT.TOKEN_ADJUST_PRIVILEGES,
            handler.getTokenHandle()
        );
        token.setStatus(status);
        token.setPrivileges(
            handler.getPrivileges()
        );
        if (status) {
            token.setHandle(
                handler.getTokenHandle().getValue()
            );
        }
        return token;
    }

    /**
     * 查找特权的本地唯一标识符（LUID）
     * @param lpName 特权名称
     * @param token  进程令牌信息
     * @return 操作是否成功
     */
    public boolean lookupPrivilegeValue(String lpName, ProcessToken token) {
        if (!token.isStatus()) {
            return false;
        }
        return CTX.LookupPrivilegeValue(
            null,
            lpName,
            token.luid()
        );
    }

    /**
     * 调整进程令牌的特权
     * @param token 进程令牌信息
     * @return 操作是否成功
     */
    public boolean adjustTokenPrivileges(ProcessToken token) {
        if (!token.isStatus()) {
            return false;
        }
        return CTX.AdjustTokenPrivileges(
            token.getHandle(),
            false,
            token.getPrivileges(),
            token.getPrivileges().size(),
            null,
            null
        );
    }
}
