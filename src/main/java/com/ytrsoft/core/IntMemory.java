package com.ytrsoft.core;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.ptr.IntByReference;

import java.util.ArrayList;
import java.util.List;

public class IntMemory extends AbstractMemory implements Memory<Integer> {

    public IntMemory(int pid) {
        super(pid);
    }

    @Override
    public Integer read(long hex) {
        IntByReference buff = new IntByReference();
        Pointer lpBuffer = buff.getPointer();
        IntByReference lpNumberOfBytesRead = new IntByReference(0);
        Pointer lpBaseAddress = new Pointer(hex);
        boolean succeed = Kernel32.INSTANCE.ReadProcessMemory(
            this.processHandle,
            lpBaseAddress,
            lpBuffer,
            Integer.BYTES,
            lpNumberOfBytesRead
        );
        if (!succeed) {
            return -1;
        }
        return buff.getValue();
    }

    @Override
    public boolean write(long hex, Integer value) {
        IntByReference buff = new IntByReference(value);
        Pointer lpBuffer = buff.getPointer();
        IntByReference lpNumberOfBytesWritten = new IntByReference(0);
        Pointer lpBaseAddress = new Pointer(hex);
        return Kernel32.INSTANCE.WriteProcessMemory(
            this.processHandle,
            lpBaseAddress,
            lpBuffer,
            Integer.BYTES,
            lpNumberOfBytesWritten
        );
    }

    @Override
    public List<Found<Integer>> scan() {
        return scan(START_HEX, STOP_HEX);
    }

    @Override
    public List<Found<Integer>> scan(long start, long stop) {
        List<Found<Integer>> foundList = new ArrayList<>();
        for (long address = start; address <= stop; address += Integer.BYTES) {
            Integer value = read(address);
            if (value != -1) {
                Found<Integer> found = new Found<>();
                found.setAddress(address);
                found.setValue(value);
                foundList.add(found);
            }
        }
        return foundList;
    }
}
