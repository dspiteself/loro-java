package com.loro;


import java.nio.ByteBuffer;
import java.util.Map;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.Collectors;

public enum FfiConverterMapStringTypeLoroValue implements FfiConverterRustBuffer<java.util.Map<java.lang.String, LoroValue>> {
    INSTANCE;

    @Override
    public java.util.Map<java.lang.String, LoroValue> read(ByteBuffer buf) {
        int len = buf.getInt();
        return IntStream.range(0, len).boxed().collect(Collectors.toMap(
            _x -> FfiConverterString.INSTANCE.read(buf),
            _x -> FfiConverterTypeLoroValue.INSTANCE.read(buf)
        ));
    }

    @Override
    public long allocationSize(Map<String, LoroValue> value) {
        long spaceForMapSize = 4;
        long spaceForChildren = value.entrySet().stream().mapToLong(entry ->
            FfiConverterString.INSTANCE.allocationSize(entry.getKey()) +
            FfiConverterTypeLoroValue.INSTANCE.allocationSize(entry.getValue())
        ).sum();
        return spaceForMapSize + spaceForChildren;
    }

    @Override
    public void write(Map<String, LoroValue> value, ByteBuffer buf) {
        buf.putInt(value.size());
        // The parens on `(k, v)` here ensure we're calling the right method,
        // which is important for compatibility with older android devices.
        // Ref https://blog.danlew.net/2017/03/16/kotlin-puzzler-whose-line-is-it-anyways/
        for (var entry : value.entrySet()) {
            FfiConverterString.INSTANCE.write(entry.getKey(), buf);
            FfiConverterTypeLoroValue.INSTANCE.write(entry.getValue(), buf);
        }
    }
}



