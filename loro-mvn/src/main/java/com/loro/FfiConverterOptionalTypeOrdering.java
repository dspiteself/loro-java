package com.loro;


import java.nio.ByteBuffer;

// public class TestForOptionals {}
public enum FfiConverterOptionalTypeOrdering implements FfiConverterRustBuffer<Ordering> {
  INSTANCE;

  @Override
  public Ordering read(ByteBuffer buf) {
    if (buf.get() == (byte)0) {
      return null;
    }
    return FfiConverterTypeOrdering.INSTANCE.read(buf);
  }

  @Override
  public long allocationSize(Ordering value) {
    if (value == null) {
      return 1L;
    } else {
      return 1L + FfiConverterTypeOrdering.INSTANCE.allocationSize(value);
    }
  }

  @Override
  public void write(Ordering value, ByteBuffer buf) {
    if (value == null) {
      buf.put((byte)0);
    } else {
      buf.put((byte)1);
      FfiConverterTypeOrdering.INSTANCE.write(value, buf);
    }
  }
}



