    package com.ytrsoft.util;

    import com.sun.jna.platform.win32.Kernel32;
    import com.sun.jna.platform.win32.Psapi;
    import com.sun.jna.platform.win32.WinNT;

    public class PsapiApi {

        private static final Psapi INSTANCE = Psapi.INSTANCE;

        private PsapiApi() {
            throw new UnsupportedOperationException();
        }

        public static String getModuleFileName(WinNT.HANDLE handle) {
            byte[] exePath = new byte[Kernel32.MAX_PATH];
            INSTANCE.GetModuleFileNameExA(
                handle,
                null,
                exePath,
                exePath.length
            );
            return new String(exePath).trim();
        }
    }
