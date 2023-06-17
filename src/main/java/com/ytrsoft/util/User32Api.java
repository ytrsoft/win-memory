    package com.ytrsoft.util;

    import com.sun.jna.Native;
    import com.sun.jna.platform.win32.User32;
    import com.sun.jna.platform.win32.WinDef;
    import com.sun.jna.ptr.IntByReference;
    import com.sun.jna.win32.StdCallLibrary;
    import com.sun.jna.win32.W32APIOptions;

    public class User32Api {

        private static final User32 INSTANCE = User32.INSTANCE;

        private User32Api() {
            throw new UnsupportedOperationException();
        }

        /**
         * 查找指定窗口名称的窗口句柄
         * @param windowName 窗口名称
         * @return 窗口句柄
         */
        public static WinDef.HWND findWindow(String windowName) {
            return INSTANCE.FindWindow(null, windowName);
        }

        /**
         * 获取窗口句柄对应的线程和进程标识符
         * @param hWnd 窗口句柄
         * @return 窗口所属的进程标识符
         */
        public static int getWindowThreadProcessId(WinDef.HWND hWnd) {
            IntByReference lpdwProcessId = new IntByReference(0);
            INSTANCE.GetWindowThreadProcessId(hWnd, lpdwProcessId);
            int value = lpdwProcessId.getValue();
            RefFree.free(lpdwProcessId);
            return value;
        }

//        interface User32 extends StdCallLibrary {
//            User32 INSTANCE = (User32) Native.load("user32", User32.class, W32APIOptions.DEFAULT_OPTIONS);
//
//            HWND GetForegroundWindow();
//
//            HICON GetIconSmall(HWND hWnd);
//
//            HICON GetIconSmall2(HWND hWnd);
//
//            HICON GetIconBig(HWND hWnd);
//
//            boolean GetIconInfo(HICON hIcon, ICONINFO piconinfo);
//        }

        public void test() {
            User32.INSTANCE.GetForegroundWindow();
//            User32.INSTANCE.GetIconInfo();

        }

    }
