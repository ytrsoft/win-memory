    package com.ytrsoft.util;

    import com.sun.jna.platform.win32.User32;
    import com.sun.jna.platform.win32.WinDef;
    import com.sun.jna.ptr.IntByReference;

    public class User32Api {

        private static final User32 INSTANCE = User32.INSTANCE;

        private User32Api() {
            throw new UnsupportedOperationException();
        }

        /**
         * 查找指定窗口名称的窗口句柄。
         * @param windowName 窗口名称。
         * @return 窗口句柄。
         */
        public static WinDef.HWND findWindow(String windowName) {
            return INSTANCE.FindWindow(null, windowName);
        }

        /**
         * 获取窗口句柄对应的线程和进程标识符。
         * @param hWnd 窗口句柄。
         * @return 窗口所属的进程标识符。
         */
        public static int getWindowThreadProcessId(WinDef.HWND hWnd) {
            IntByReference lpdwProcessId = new IntByReference(0);
            INSTANCE.GetWindowThreadProcessId(hWnd, lpdwProcessId);
            int value = lpdwProcessId.getValue();
            RefFree.free(lpdwProcessId);
            return value;
        }
    }
