package com.ytrsoft.core;

import com.sun.jna.platform.win32.WinNT;
import com.ytrsoft.util.api.Kernel32Api;
import com.ytrsoft.util.api.PsapiApi;
import com.ytrsoft.util.img.IconExtract;

import java.awt.image.BufferedImage;

public class HandleManager {

    private final WinNT.HANDLE handle;

    public HandleManager(int pid) {
        this.handle = Kernel32Api.openProcess(pid);
    }

    public BufferedImage getIcon(int size) {
        return getIcon(size, size);
    }

    public BufferedImage getIcon(int width, int height) {
        String fileName = PsapiApi.getModuleName(handle);
        return IconExtract.getIconForFile(
            width,
            height,
            fileName
        );
    }

}
