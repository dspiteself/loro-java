package com.loro;


import java.nio.ByteBuffer;

// public class TestForOptionals {}
public enum FfiConverterOptionalTypeVersionVector implements FfiConverterRustBuffer<VersionVector> {
  INSTANCE;

  @Override
  public VersionVector read(ByteBuffer buf) {
    if (buf.get() == (byte)0) {
      return null;
    }
    return FfiConverterTypeVersionVector.INSTANCE.read(buf);
  }

  @Override
  public long allocationSize(VersionVector value) {
    if (value == null) {
      return 1L;
    } else {
      return 1L + FfiConverterTypeVersionVector.INSTANCE.allocationSize(value);
    }
  }

  @Override
  public void write(VersionVector value, ByteBuffer buf) {
    if (value == null) {
      buf.put((byte)0);
    } else {
      buf.put((byte)1);
      FfiConverterTypeVersionVector.INSTANCE.write(value, buf);
    }
  }
}



