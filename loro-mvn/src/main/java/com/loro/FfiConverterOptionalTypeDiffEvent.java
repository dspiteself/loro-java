package com.loro;


import java.nio.ByteBuffer;

// public class TestForOptionals {}
public enum FfiConverterOptionalTypeDiffEvent implements FfiConverterRustBuffer<DiffEvent> {
  INSTANCE;

  @Override
  public DiffEvent read(ByteBuffer buf) {
    if (buf.get() == (byte)0) {
      return null;
    }
    return FfiConverterTypeDiffEvent.INSTANCE.read(buf);
  }

  @Override
  public long allocationSize(DiffEvent value) {
    if (value == null) {
      return 1L;
    } else {
      return 1L + FfiConverterTypeDiffEvent.INSTANCE.allocationSize(value);
    }
  }

  @Override
  public void write(DiffEvent value, ByteBuffer buf) {
    if (value == null) {
      buf.put((byte)0);
    } else {
      buf.put((byte)1);
      FfiConverterTypeDiffEvent.INSTANCE.write(value, buf);
    }
  }
}



