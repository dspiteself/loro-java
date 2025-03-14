package com.loro;


import java.nio.ByteBuffer;

public enum FfiConverterTypeJsonPathError implements FfiConverterRustBuffer<JsonPathException> {
    INSTANCE;

    @Override
    public JsonPathException read(ByteBuffer buf) {
        return switch(buf.getInt()) {
            case 1 -> new JsonPathException.InvalidJsonPath(FfiConverterString.INSTANCE.read(buf));
            case 2 -> new JsonPathException.EvaluationException(FfiConverterString.INSTANCE.read(buf));
            default -> throw new RuntimeException("invalid error enum value, something is very wrong!!");
        };
    }

    @Override
    public long allocationSize(JsonPathException value) {
        return 4L;
    }

    @Override
    public void write(JsonPathException value, ByteBuffer buf) {
        switch(value) {
            case JsonPathException.InvalidJsonPath x -> {
                buf.putInt(1);
            }
            case JsonPathException.EvaluationException x -> {
                buf.putInt(2);
            }
            default -> throw new RuntimeException("invalid error enum value, something is very wrong!!");
        };
    }
}




