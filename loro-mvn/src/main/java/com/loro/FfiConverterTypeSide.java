package com.loro;


import java.nio.ByteBuffer;

public enum FfiConverterTypeSide implements FfiConverterRustBuffer<Side> {
    INSTANCE;

    @Override
    public Side read(ByteBuffer buf) {
        try {
            return Side.values()[buf.getInt() - 1];
        } catch (IndexOutOfBoundsException e) {
            throw new RuntimeException("invalid enum value, something is very wrong!!", e);
        }
    }

    @Override
    public long allocationSize(Side value) {
        return 4L;
    }

    @Override
    public void write(Side value, ByteBuffer buf) {
        buf.putInt(value.ordinal() + 1);
    }
}




