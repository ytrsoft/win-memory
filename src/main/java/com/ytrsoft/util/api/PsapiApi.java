    package com.ytrsoft.util.api;

    import com.sun.jna.platform.win32.Psapi;
    import com.ytrsoft.simplified.ByteEx;
    import com.ytrsoft.simplified.NTHandle;

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
        public static String getModuleName(NTHandle handle) {
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
