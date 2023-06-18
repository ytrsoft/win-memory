    package com.ytrsoft.util.api;

    import com.sun.jna.platform.win32.User32;
    import com.sun.jna.platform.win32.WinDef;
    import com.sun.jna.ptr.IntByReference;
    import com.ytrsoft.util.RefFree;

    /**
     * User32 API工具类
     */
    public final class User32Api {

        private static final User32 CTX = User32.INSTANCE;

        private User32Api() {
            throw new UnsupportedOperationException();
        }

        /**
         * 根据窗口名称查找窗口句柄
         * @param name 窗口名称
         * @return 窗口句柄
         */
        public static WinDef.HWND findHandle(String name) {
            return CTX.FindWindow(null, name);
        }

        /**
         * 获取窗口句柄所属的进程ID
         * @param handle 窗口句柄
         * @return 窗口所属的进程ID
         */
        public static int getPId(WinDef.HWND handle) {
            IntByReference ref = new IntByReference();
            CTX.GetWindowThreadProcessId(handle, ref);
            int valued = ref.getValue();
            RefFree.free(ref);
            return valued;
        }

    }
