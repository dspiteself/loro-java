package com.loro;


import java.nio.ByteBuffer;

// public class TestForOptionals {}
public enum FfiConverterOptionalMapLongTypeCounterSpan implements FfiConverterRustBuffer<java.util.Map<Long, CounterSpan>> {
  INSTANCE;

  @Override
  public java.util.Map<Long, CounterSpan> read(ByteBuffer buf) {
    if (buf.get() == (byte)0) {
      return null;
    }
    return FfiConverterMapLongTypeCounterSpan.INSTANCE.read(buf);
  }

  @Override
  public long allocationSize(java.util.Map<Long, CounterSpan> value) {
    if (value == null) {
      return 1L;
    } else {
      return 1L + FfiConverterMapLongTypeCounterSpan.INSTANCE.allocationSize(value);
    }
  }

  @Override
  public void write(java.util.Map<Long, CounterSpan> value, ByteBuffer buf) {
    if (value == null) {
      buf.put((byte)0);
    } else {
      buf.put((byte)1);
      FfiConverterMapLongTypeCounterSpan.INSTANCE.write(value, buf);
    }
  }
}



