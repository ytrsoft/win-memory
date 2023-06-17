package com.ytrsoft.util;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.*;
import com.sun.jna.ptr.IntByReference;
import com.ytrsoft.entity.Virtual;

public final class Kernel32Api {

    private static final Kernel32 INSTANCE = Kernel32.INSTANCE;

    private Kernel32Api() {
        throw new UnsupportedOperationException();
    }

    /**
     * 获取调用线程的最后一个错误代码
     * @return 最后一个错误代码值
     */
    public static int getLastError() {
        return INSTANCE.GetLastError();
    }

    /**
     * 关闭一个打开的对象句柄
     * @param handle 要关闭的句柄
     * @return 如果函数调用成功，则返回 true；否则返回 false
     */
    public static boolean closeHandle(WinNT.HANDLE handle) {
        return INSTANCE.CloseHandle(handle);
    }

    /**
     * 获取指定进程的终止状态
     * @param handle 进程的句柄
     * @return 进程的终止状态
     */
    public static int getExitCodeProcess(WinNT.HANDLE handle) {
        IntByReference lpExitCodeRef = new IntByReference(0);
        INSTANCE.GetExitCodeProcess(handle, lpExitCodeRef);
        int lpExitCode = lpExitCodeRef.getValue();
        RefFree.free(lpExitCodeRef);
        return lpExitCode;
    }

    /**
     * 终止指定的进程及其所有线程
     * @param handle 要终止的进程的句柄
     * @param uExitCode 进程和由该函数终止的线程的退出代码
     * @return 如果函数调用成功，则返回 true；否则返回 false
     */
    public static boolean terminateProcess(WinNT.HANDLE handle, int uExitCode) {
        return INSTANCE.TerminateProcess(handle, uExitCode);
    }

    /**
     * 获取进程快照中的第一个进程信息。
     * @param handle 进程快照句柄
     * @param lppe   进程信息结构体
     * @return 如果成功获取到第一个进程信息，则返回true；否则返回false
     */
    public static boolean process32First(WinNT.HANDLE handle, Tlhelp32.PROCESSENTRY32 lppe) {
        return INSTANCE.Process32First(handle, lppe);
    }

    /**
     * 获取进程快照中的下一个进程信息。
     * @param handle 进程快照句柄
     * @param lppe   进程信息结构体
     * @return 如果成功获取到下一个进程信息，则返回true；否则返回false
     */
    public static boolean process32Next(WinNT.HANDLE handle, Tlhelp32.PROCESSENTRY32 lppe) {
        return INSTANCE.Process32Next(handle, lppe);
    }

    /**
     * 获取与进程关联的第一个模块的信息
     * @param handle 从先前调用 createToolhelp32Snapshot 函数返回的快照的句柄
     * @return 如果模块列表的第一个条目已被复制到缓冲区，则返回 true；否则返回 false
     */
    public static boolean module32First(WinNT.HANDLE handle) {
        return INSTANCE.Module32FirstW(handle, new Tlhelp32.MODULEENTRY32W());
    }

    /**
     * 获取与进程或线程关联的下一个模块的信息
     * @param handle 从先前调用 createToolhelp32Snapshot 函数返回的快照的句柄
     * @return 如果模块列表的下一个条目已被复制到缓冲区，则返回 true；否则返回 false
     */
    public static boolean module32Next(WinNT.HANDLE handle) {
        return INSTANCE.Module32NextW(handle, new Tlhelp32.MODULEENTRY32W());
    }

    /**
     * 判断指定的进程是否是 64 位进程
     * @param handle 进程的句柄
     * @return 如果进程是 64 位进程，则返回 false；否则返回 true
     */
    public static boolean isWow64Process(WinNT.HANDLE handle) {
        IntByReference wow64Process = new IntByReference(0);
        INSTANCE.IsWow64Process(handle, wow64Process);
        int status = wow64Process.getValue();
        RefFree.free(wow64Process);
        return status == 0;
    }

    /**
     * 获取系统信息
     * @param lpSystemInfo 存储系统信息的对象
     */
    public static void GetSystemInfo(WinBase.SYSTEM_INFO lpSystemInfo) {
        INSTANCE.GetSystemInfo(lpSystemInfo);
    }

    /**
     * 打开指定进程的句柄
     * @param pid 进程的 PID
     * @return 进程的句柄
     */
    public static WinNT.HANDLE openProcess(int pid) {
        return INSTANCE.OpenProcess(
            WinNT.PROCESS_ALL_ACCESS,
            false,
            pid
        );
    }

    /**
     * 创建进程快照。
     * @param dwFlags 快照标志，用于指定快照的类型
     * @param pid  要获取快照的进程的ID，如果为0，则表示获取所有进程的快照
     * @return 返回进程快照的句柄
     */
    public static WinNT.HANDLE createToolhelp32Snapshot(WinDef.DWORD dwFlags, int pid) {
        return INSTANCE.CreateToolhelp32Snapshot(
            dwFlags,
            new WinNT.DWORD(pid)
        );
    }

    /**
     * 获取指定句柄和内存地址的虚拟内存信息
     * @param handle     进程的句柄
     * @param lpAddress  内存地址
     * @return 虚拟内存信息
     */
    public static Virtual virtualQuery(WinNT.HANDLE handle, long lpAddress) {
        Virtual virtual = new Virtual();
        WinNT.MEMORY_BASIC_INFORMATION lpBuffer = new WinNT.MEMORY_BASIC_INFORMATION();
        INSTANCE.VirtualQueryEx(
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
     *
     * @param handle         进程的句柄
     * @param lpBaseAddress  内存基址
     * @param lpBuffer       接收读取的数据的缓冲区
     * @param nSize          要读取的数据大小
     */
    public void readProcessMemory(WinNT.HANDLE handle, long lpBaseAddress, Pointer lpBuffer, int nSize) {
        IntByReference lpNumberOfBytesRead = new IntByReference(0);
        Pointer _lpBaseAddress = new Pointer(lpBaseAddress);
        INSTANCE.ReadProcessMemory(
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
     *
     * @param handle         进程的句柄
     * @param lpBaseAddress  内存基址
     * @param lpBuffer       要写入的数据缓冲区
     * @param nSize          要写入的数据大小
     * @return 如果函数调用成功，则返回 true；否则返回 false
     */
    public boolean writeProcessMemory(WinNT.HANDLE handle, long lpBaseAddress, Pointer lpBuffer, int nSize) {
        IntByReference lpNumberOfBytesWritten = new IntByReference(0);
        Pointer _lpBaseAddress = new Pointer(lpBaseAddress);
        boolean status = INSTANCE.WriteProcessMemory(
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
