package com.loro;


import java.nio.ByteBuffer;

// public class TestForOptionals {}
public enum FfiConverterOptionalTypeLoroCounter implements FfiConverterRustBuffer<LoroCounter> {
  INSTANCE;

  @Override
  public LoroCounter read(ByteBuffer buf) {
    if (buf.get() == (byte)0) {
      return null;
    }
    return FfiConverterTypeLoroCounter.INSTANCE.read(buf);
  }

  @Override
  public long allocationSize(LoroCounter value) {
    if (value == null) {
      return 1L;
    } else {
      return 1L + FfiConverterTypeLoroCounter.INSTANCE.allocationSize(value);
    }
  }

  @Override
  public void write(LoroCounter value, ByteBuffer buf) {
    if (value == null) {
      buf.put((byte)0);
    } else {
      buf.put((byte)1);
      FfiConverterTypeLoroCounter.INSTANCE.write(value, buf);
    }
  }
}



