package com.ytrsoft.util.ex;

import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.WString;
import com.sun.jna.platform.win32.Guid;
import com.sun.jna.platform.win32.Shell32;
import com.sun.jna.platform.win32.WinNT;
import com.sun.jna.ptr.PointerByReference;
import com.sun.jna.win32.W32APIOptions;

public interface Shell32Ex extends Shell32 {

    Shell32Ex INSTANCE = Native.load("shell32", Shell32Ex.class, W32APIOptions.DEFAULT_OPTIONS);

    WinNT.HRESULT SHCreateItemFromParsingName(WString path, Pointer pointer, Guid.REFIID guid, PointerByReference reference);

}
