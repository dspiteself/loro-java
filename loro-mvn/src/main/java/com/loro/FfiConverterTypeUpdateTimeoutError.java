package com.loro;


import java.nio.ByteBuffer;

public enum FfiConverterTypeUpdateTimeoutError implements FfiConverterRustBuffer<UpdateTimeoutException> {
    INSTANCE;

    @Override
    public UpdateTimeoutException read(ByteBuffer buf) {
        return switch(buf.getInt()) {
            case 1 -> new UpdateTimeoutException.Timeout(FfiConverterString.INSTANCE.read(buf));
            default -> throw new RuntimeException("invalid error enum value, something is very wrong!!");
        };
    }

    @Override
    public long allocationSize(UpdateTimeoutException value) {
        return 4L;
    }

    @Override
    public void write(UpdateTimeoutException value, ByteBuffer buf) {
        switch(value) {
            case UpdateTimeoutException.Timeout x -> {
                buf.putInt(1);
            }
            default -> throw new RuntimeException("invalid error enum value, something is very wrong!!");
        };
    }
}





