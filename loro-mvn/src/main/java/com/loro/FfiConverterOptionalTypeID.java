package com.loro;


import java.nio.ByteBuffer;

// public class TestForOptionals {}
public enum FfiConverterOptionalTypeID implements FfiConverterRustBuffer<Id> {
  INSTANCE;

  @Override
  public Id read(ByteBuffer buf) {
    if (buf.get() == (byte)0) {
      return null;
    }
    return FfiConverterTypeID.INSTANCE.read(buf);
  }

  @Override
  public long allocationSize(Id value) {
    if (value == null) {
      return 1L;
    } else {
      return 1L + FfiConverterTypeID.INSTANCE.allocationSize(value);
    }
  }

  @Override
  public void write(Id value, ByteBuffer buf) {
    if (value == null) {
      buf.put((byte)0);
    } else {
      buf.put((byte)1);
      FfiConverterTypeID.INSTANCE.write(value, buf);
    }
  }
}



