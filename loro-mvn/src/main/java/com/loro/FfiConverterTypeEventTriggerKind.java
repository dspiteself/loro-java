package com.loro;


import java.nio.ByteBuffer;

public enum FfiConverterTypeEventTriggerKind implements FfiConverterRustBuffer<EventTriggerKind> {
    INSTANCE;

    @Override
    public EventTriggerKind read(ByteBuffer buf) {
        try {
            return EventTriggerKind.values()[buf.getInt() - 1];
        } catch (IndexOutOfBoundsException e) {
            throw new RuntimeException("invalid enum value, something is very wrong!!", e);
        }
    }

    @Override
    public long allocationSize(EventTriggerKind value) {
        return 4L;
    }

    @Override
    public void write(EventTriggerKind value, ByteBuffer buf) {
        buf.putInt(value.ordinal() + 1);
    }
}




