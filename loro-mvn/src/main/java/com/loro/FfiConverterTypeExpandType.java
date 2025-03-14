package com.loro;


import java.nio.ByteBuffer;

public enum FfiConverterTypeExpandType implements FfiConverterRustBuffer<ExpandType> {
    INSTANCE;

    @Override
    public ExpandType read(ByteBuffer buf) {
        try {
            return ExpandType.values()[buf.getInt() - 1];
        } catch (IndexOutOfBoundsException e) {
            throw new RuntimeException("invalid enum value, something is very wrong!!", e);
        }
    }

    @Override
    public long allocationSize(ExpandType value) {
        return 4L;
    }

    @Override
    public void write(ExpandType value, ByteBuffer buf) {
        buf.putInt(value.ordinal() + 1);
    }
}




