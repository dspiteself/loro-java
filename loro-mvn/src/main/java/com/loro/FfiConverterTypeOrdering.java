package com.loro;


import java.nio.ByteBuffer;

public enum FfiConverterTypeOrdering implements FfiConverterRustBuffer<Ordering> {
    INSTANCE;

    @Override
    public Ordering read(ByteBuffer buf) {
        try {
            return Ordering.values()[buf.getInt() - 1];
        } catch (IndexOutOfBoundsException e) {
            throw new RuntimeException("invalid enum value, something is very wrong!!", e);
        }
    }

    @Override
    public long allocationSize(Ordering value) {
        return 4L;
    }

    @Override
    public void write(Ordering value, ByteBuffer buf) {
        buf.putInt(value.ordinal() + 1);
    }
}




