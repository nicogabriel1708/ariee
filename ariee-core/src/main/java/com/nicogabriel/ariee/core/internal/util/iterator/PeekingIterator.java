package com.nicogabriel.ariee.core.internal.util.iterator;

import java.util.Iterator;

public interface PeekingIterator<T> extends Iterator<T> {

    T peek();
}
