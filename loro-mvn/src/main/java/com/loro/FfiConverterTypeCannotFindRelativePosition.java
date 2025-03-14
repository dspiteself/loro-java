package com.loro;


import java.nio.ByteBuffer;

public enum FfiConverterTypeCannotFindRelativePosition implements FfiConverterRustBuffer<CannotFindRelativePosition> {
    INSTANCE;

    @Override
    public CannotFindRelativePosition read(ByteBuffer buf) {
        return switch(buf.getInt()) {
            case 1 -> new CannotFindRelativePosition.ContainerDeleted(FfiConverterString.INSTANCE.read(buf));
            case 2 -> new CannotFindRelativePosition.HistoryCleared(FfiConverterString.INSTANCE.read(buf));
            case 3 -> new CannotFindRelativePosition.IdNotFound(FfiConverterString.INSTANCE.read(buf));
            default -> throw new RuntimeException("invalid error enum value, something is very wrong!!");
        };
    }

    @Override
    public long allocationSize(CannotFindRelativePosition value) {
        return 4L;
    }

    @Override
    public void write(CannotFindRelativePosition value, ByteBuffer buf) {
        switch(value) {
            case CannotFindRelativePosition.ContainerDeleted x -> {
                buf.putInt(1);
            }
            case CannotFindRelativePosition.HistoryCleared x -> {
                buf.putInt(2);
            }
            case CannotFindRelativePosition.IdNotFound x -> {
                buf.putInt(3);
            }
            default -> throw new RuntimeException("invalid error enum value, something is very wrong!!");
        };
    }
}




