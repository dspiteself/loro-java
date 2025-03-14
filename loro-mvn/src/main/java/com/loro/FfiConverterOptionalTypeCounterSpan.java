package com.loro;


import java.nio.ByteBuffer;

// public class TestForOptionals {}
public enum FfiConverterOptionalTypeCounterSpan implements FfiConverterRustBuffer<CounterSpan> {
  INSTANCE;

  @Override
  public CounterSpan read(ByteBuffer buf) {
    if (buf.get() == (byte)0) {
      return null;
    }
    return FfiConverterTypeCounterSpan.INSTANCE.read(buf);
  }

  @Override
  public long allocationSize(CounterSpan value) {
    if (value == null) {
      return 1L;
    } else {
      return 1L + FfiConverterTypeCounterSpan.INSTANCE.allocationSize(value);
    }
  }

  @Override
  public void write(CounterSpan value, ByteBuffer buf) {
    if (value == null) {
      buf.put((byte)0);
    } else {
      buf.put((byte)1);
      FfiConverterTypeCounterSpan.INSTANCE.write(value, buf);
    }
  }
}



