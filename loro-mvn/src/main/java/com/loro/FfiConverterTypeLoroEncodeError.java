package com.loro;


import java.nio.ByteBuffer;

public enum FfiConverterTypeLoroEncodeError implements FfiConverterRustBuffer<LoroEncodeException> {
    INSTANCE;

    @Override
    public LoroEncodeException read(ByteBuffer buf) {
        return switch(buf.getInt()) {
            case 1 -> new LoroEncodeException.FrontiersNotFound(FfiConverterString.INSTANCE.read(buf));
            case 2 -> new LoroEncodeException.ShallowSnapshotIncompatibleWithOldFormat(FfiConverterString.INSTANCE.read(buf));
            case 3 -> new LoroEncodeException.UnknownContainer(FfiConverterString.INSTANCE.read(buf));
            default -> throw new RuntimeException("invalid error enum value, something is very wrong!!");
        };
    }

    @Override
    public long allocationSize(LoroEncodeException value) {
        return 4L;
    }

    @Override
    public void write(LoroEncodeException value, ByteBuffer buf) {
        switch(value) {
            case LoroEncodeException.FrontiersNotFound x -> {
                buf.putInt(1);
            }
            case LoroEncodeException.ShallowSnapshotIncompatibleWithOldFormat x -> {
                buf.putInt(2);
            }
            case LoroEncodeException.UnknownContainer x -> {
                buf.putInt(3);
            }
            default -> throw new RuntimeException("invalid error enum value, something is very wrong!!");
        };
    }
}




