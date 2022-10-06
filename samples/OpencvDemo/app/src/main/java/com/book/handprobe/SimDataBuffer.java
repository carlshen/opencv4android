package com.book.handprobe;

import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.concurrent.LinkedBlockingQueue;

/* loaded from: classes2.dex */
public class SimDataBuffer extends LinkedBlockingQueue<Object> {
    int bufferSize;

    public SimDataBuffer(int i) {
        this.bufferSize = 10;
        this.bufferSize = i;
    }

    public SimDataBuffer(Collection<?> collection, int i) {
        super(collection);
        this.bufferSize = 10;
        this.bufferSize = i;
    }

    public int getBufferSize() {
        return this.bufferSize;
    }

    public void setBufferSize(int i) {
        this.bufferSize = i;
    }

    @Override // java.util.concurrent.LinkedBlockingQueue, java.util.Queue, java.util.concurrent.BlockingQueue
    public boolean offer(Object obj) {
        while (!isEmpty() && size() / 2 >= this.bufferSize) {
            synchronized (this) {
                try {
                    poll();
                } catch (NoSuchElementException e) {
                    e.printStackTrace();
                }
            }
        }
        return super.offer(obj);
    }

    public boolean offerBoth(byte b, byte[] bArr) {
        return offer(Byte.valueOf(b)) & offer(bArr);
    }

    @Override // java.util.concurrent.LinkedBlockingQueue, java.util.AbstractQueue, java.util.AbstractCollection, java.util.Collection
    public void clear() {
        super.clear();
    }
}
