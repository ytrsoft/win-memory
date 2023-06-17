package com.ytrsoft.util.api;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.*;
import com.sun.jna.ptr.IntByReference;
import com.ytrsoft.simplified.NTHandle;
import com.ytrsoft.simplified.SystemInfo;
import com.ytrsoft.simplified.VirtualQuery;
import com.ytrsoft.util.share.RefFree;

public final class Kernel32Api {

    private static final Kernel32 CTX = Kernel32.INSTANCE;

    private Kernel32Api() {
        throw new UnsupportedOperationException();
    }

    /**
     * 获取调用线程的最后一个错误代码
     * @return 最后一个错误代码值
     */
    public static int getLastError() {
        return CTX.GetLastError();
    }

    /**
     * 关闭一个打开的对象句柄
     * @param handle 要关闭的句柄
     * @return 执行状态
     */
    public static boolean closeHandle(NTHandle handle) {
        return CTX.CloseHandle(handle);
    }

    /**
     * 获取指定进程的终止状态
     * @param handle 进程的句柄
     * @return 进程的终止状态
     */
    public static int getExitCodeProcess(NTHandle handle) {
        IntByReference ref = new IntByReference();
        CTX.GetExitCodeProcess(handle, ref);
        int valued = ref.getValue();
        RefFree.free(ref);
        return valued;
    }

    /**
     * 终止指定的进程及其所有线程
     * @param handle 要终止的进程的句柄
     * @param exitCode 进程和由该函数终止的线程的退出代码
     * @return 执行状态
     */
    public static boolean terminateProcess(NTHandle handle, int exitCode) {
        return CTX.TerminateProcess(handle, exitCode);
    }

    /**
     * 获取进程快照中的第一个进程信息
     * @param handle 进程快照句柄
     * @param processEntry 进程信息
     * @return 执行状态
     */
    public static boolean process32First(NTHandle handle, Tlhelp32.PROCESSENTRY32 processEntry) {
        return CTX.Process32First(handle, processEntry);
    }

    /**
     * 获取进程快照中的下一个进程信息
     * @param handle 进程快照句柄
     * @param processEntry 进程信息
     * @return 执行状态
     */
    public static boolean process32Next(NTHandle handle, Tlhelp32.PROCESSENTRY32 processEntry) {
        return CTX.Process32Next(handle, processEntry);
    }

    /**
     * 获取与进程关联的第一个模块的信息
     * @param handle 进程句柄
     * @param moduleEntry 模块信息
     * @return 执行状态
     */
    public static boolean module32First(NTHandle handle, Tlhelp32.MODULEENTRY32W moduleEntry) {
        return CTX.Module32FirstW(handle, moduleEntry);
    }

    /**
     * 获取与进程或线程关联的下一个模块的信息
     * @param handle 进程句柄
     * @param moduleEntry 模块信息
     * @return 执行状态
     */
    public static boolean module32Next(NTHandle handle, Tlhelp32.MODULEENTRY32W moduleEntry) {
        return CTX.Module32NextW(handle, moduleEntry);
    }

    /**
     * 判断指定的进程是否是64位进程
     * @param handle 进程的句柄
     * @return 执行状态
     */
    public static boolean is64BitProcess(NTHandle handle) {
        IntByReference wow64Process = new IntByReference(0);
        CTX.IsWow64Process(handle, wow64Process);
        int valued = wow64Process.getValue();
        RefFree.free(wow64Process);
        return valued == 0;
    }

    /**
     * 获取系统信息
     * @param info 存储系统信息的对象
     */
    public static void GetSystemInfo(SystemInfo info) {
        CTX.GetSystemInfo(info);
    }

    /**
     * 打开指定进程的句柄
     * @param pid 进程的 PID
     * @return 进程的句柄
     */
    public static NTHandle openProcess(int pid) {
        return (NTHandle)CTX.OpenProcess(
            WinNT.PROCESS_ALL_ACCESS,
            false,
            pid
        );
    }

    /**
     * 创建进程快照。
     * @param flags 快照标志，用于指定快照的类型
     * @param pid  要获取快照的进程的ID，0表示获取所有进程的快照
     * @return 返回进程快照的句柄
     */
    public static NTHandle createToolhelp32Snapshot(long flags, int pid) {
        WinDef.DWORD dwFlags = new WinDef.DWORD(flags);
        return (NTHandle)CTX.CreateToolhelp32Snapshot(
            dwFlags,
            new WinNT.DWORD(pid)
        );
    }

    /**
     * 获取指定句柄和内存地址的虚拟内存信息
     * @param handle 进程的句柄
     * @param lpAddress  内存地址
     * @return 虚拟内存信息
     */
    public static VirtualQuery virtualQuery(NTHandle handle, long lpAddress) {
        VirtualQuery virtual = new VirtualQuery();
        WinNT.MEMORY_BASIC_INFORMATION lpBuffer = new WinNT.MEMORY_BASIC_INFORMATION();
        CTX.VirtualQueryEx(
            handle,
            new Pointer(lpAddress),
            lpBuffer,
            new BaseTSD.SIZE_T(lpBuffer.size())
        );
        virtual.setProtect(lpBuffer.protect.intValue());
        virtual.setState(lpBuffer.state.intValue());
        return virtual;
    }

    /**
     * 从指定进程的内存中读取数据
     * @param handle         进程的句柄
     * @param lpBaseAddress  内存基址
     * @param lpBuffer       接收读取的数据的缓冲区
     * @param nSize          要读取的数据大小
     */
    public void readProcessMemory(WinNT.HANDLE handle, long lpBaseAddress, Pointer lpBuffer, int nSize) {
        IntByReference lpNumberOfBytesRead = new IntByReference();
        Pointer _lpBaseAddress = new Pointer(lpBaseAddress);
        CTX.ReadProcessMemory(
            handle,
            _lpBaseAddress,
            lpBuffer,
            nSize,
            lpNumberOfBytesRead
        );
        RefFree.free(lpNumberOfBytesRead);
        RefFree.free(_lpBaseAddress);
    }

    /**
     * 向指定进程的内存中写入数据
     * @param handle         进程的句柄
     * @param lpBaseAddress  内存基址
     * @param lpBuffer       要写入的数据缓冲区
     * @param nSize          要写入的数据大小
     * @return 返回执行结果
     */
    public boolean writeProcessMemory(WinNT.HANDLE handle, long lpBaseAddress, Pointer lpBuffer, int nSize) {
        IntByReference lpNumberOfBytesWritten = new IntByReference();
        Pointer _lpBaseAddress = new Pointer(lpBaseAddress);
        boolean status = CTX.WriteProcessMemory(
            handle,
            _lpBaseAddress,
            lpBuffer,
            nSize,
            lpNumberOfBytesWritten
        );
        RefFree.free(lpNumberOfBytesWritten);
        RefFree.free(_lpBaseAddress);
        return status;
    }
}
