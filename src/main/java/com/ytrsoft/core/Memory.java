package com.ytrsoft.core;

import java.util.List;

public interface Memory<T> {
    T read(long hex);
    boolean write(long hex, T value);
    List<Found<T>> scan();
    List<Found<T>> scan(long start, long stop);
}
