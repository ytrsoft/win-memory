package com.ytrsoft.util.img;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.COM.Unknown;
import com.sun.jna.platform.win32.WinNT;
import com.sun.jna.ptr.PointerByReference;

public class IShellItemImageFactory extends Unknown {

    public IShellItemImageFactory(Pointer p) {
        super(p);
    }

    public WinNT.HRESULT getImage(SIZEByValue size, int flags, PointerByReference bitmap) {
        Object[] params = new Object[] {
            this.getPointer(),
            size,
            flags,
            bitmap
        };
        return (WinNT.HRESULT) _invokeNativeObject(3, params, WinNT.HRESULT.class);
    }
}
