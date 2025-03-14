package com.loro;


import java.nio.ByteBuffer;

public enum FfiConverterTypeUndoOrRedo implements FfiConverterRustBuffer<UndoOrRedo> {
    INSTANCE;

    @Override
    public UndoOrRedo read(ByteBuffer buf) {
        try {
            return UndoOrRedo.values()[buf.getInt() - 1];
        } catch (IndexOutOfBoundsException e) {
            throw new RuntimeException("invalid enum value, something is very wrong!!", e);
        }
    }

    @Override
    public long allocationSize(UndoOrRedo value) {
        return 4L;
    }

    @Override
    public void write(UndoOrRedo value, ByteBuffer buf) {
        buf.putInt(value.ordinal() + 1);
    }
}




