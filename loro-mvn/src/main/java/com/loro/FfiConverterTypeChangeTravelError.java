package com.loro;


import java.nio.ByteBuffer;

public enum FfiConverterTypeChangeTravelError implements FfiConverterRustBuffer<ChangeTravelException> {
    INSTANCE;

    @Override
    public ChangeTravelException read(ByteBuffer buf) {
        return switch(buf.getInt()) {
            case 1 -> new ChangeTravelException.TargetIdNotFound(FfiConverterString.INSTANCE.read(buf));
            case 2 -> new ChangeTravelException.TargetVersionNotIncluded(FfiConverterString.INSTANCE.read(buf));
            default -> throw new RuntimeException("invalid error enum value, something is very wrong!!");
        };
    }

    @Override
    public long allocationSize(ChangeTravelException value) {
        return 4L;
    }

    @Override
    public void write(ChangeTravelException value, ByteBuffer buf) {
        switch(value) {
            case ChangeTravelException.TargetIdNotFound x -> {
                buf.putInt(1);
            }
            case ChangeTravelException.TargetVersionNotIncluded x -> {
                buf.putInt(2);
            }
            default -> throw new RuntimeException("invalid error enum value, something is very wrong!!");
        };
    }
}




