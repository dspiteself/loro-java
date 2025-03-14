package com.loro;


import java.nio.ByteBuffer;

// public class TestForOptionals {}
public enum FfiConverterOptionalTypeDiff implements FfiConverterRustBuffer<Diff> {
  INSTANCE;

  @Override
  public Diff read(ByteBuffer buf) {
    if (buf.get() == (byte)0) {
      return null;
    }
    return FfiConverterTypeDiff.INSTANCE.read(buf);
  }

  @Override
  public long allocationSize(Diff value) {
    if (value == null) {
      return 1L;
    } else {
      return 1L + FfiConverterTypeDiff.INSTANCE.allocationSize(value);
    }
  }

  @Override
  public void write(Diff value, ByteBuffer buf) {
    if (value == null) {
      buf.put((byte)0);
    } else {
      buf.put((byte)1);
      FfiConverterTypeDiff.INSTANCE.write(value, buf);
    }
  }
}



