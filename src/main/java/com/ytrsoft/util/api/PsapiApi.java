    package com.ytrsoft.util.api;

    import com.sun.jna.platform.win32.Psapi;
    import com.sun.jna.platform.win32.WinNT;
    import com.ytrsoft.simplified.ByteEx;

    /**
     * Psapi API工具类
     */
    public final class PsapiApi {

        private static final Psapi CTX = Psapi.INSTANCE;

        private PsapiApi() {
            throw new UnsupportedOperationException();
        }

        /**
         * 获取模块名称
         * @param handle 模块的句柄
         * @return 模块的名称
         */
        public static String getModuleName(WinNT.HANDLE handle) {
            ByteEx filename = new ByteEx();
            CTX.GetModuleFileNameExA(
                handle,
                null,
                filename.get(),
                filename.size()
            );
            return filename.toString();
        }
    }
