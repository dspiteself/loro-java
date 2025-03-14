package com.loro;


import java.util.List;
import java.nio.ByteBuffer;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public enum FfiConverterSequenceTypeTextDelta implements FfiConverterRustBuffer<List<TextDelta>> {
  INSTANCE;

  @Override
  public List<TextDelta> read(ByteBuffer buf) {
    int len = buf.getInt();
    return IntStream.range(0, len).mapToObj(_i -> FfiConverterTypeTextDelta.INSTANCE.read(buf)).toList();
  }

  @Override
  public long allocationSize(List<TextDelta> value) {
    long sizeForLength = 4L;
    long sizeForItems = value.stream().mapToLong(inner -> FfiConverterTypeTextDelta.INSTANCE.allocationSize(inner)).sum();
    return sizeForLength + sizeForItems;
  }

  @Override
  public void write(List<TextDelta> value, ByteBuffer buf) {
    buf.putInt(value.size());
    value.forEach(inner -> FfiConverterTypeTextDelta.INSTANCE.write(inner, buf));
  }
}



