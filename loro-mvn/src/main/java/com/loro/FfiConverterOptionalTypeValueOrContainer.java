package com.loro;


import java.nio.ByteBuffer;

// public class TestForOptionals {}
public enum FfiConverterOptionalTypeValueOrContainer implements FfiConverterRustBuffer<ValueOrContainer> {
  INSTANCE;

  @Override
  public ValueOrContainer read(ByteBuffer buf) {
    if (buf.get() == (byte)0) {
      return null;
    }
    return FfiConverterTypeValueOrContainer.INSTANCE.read(buf);
  }

  @Override
  public long allocationSize(ValueOrContainer value) {
    if (value == null) {
      return 1L;
    } else {
      return 1L + FfiConverterTypeValueOrContainer.INSTANCE.allocationSize(value);
    }
  }

  @Override
  public void write(ValueOrContainer value, ByteBuffer buf) {
    if (value == null) {
      buf.put((byte)0);
    } else {
      buf.put((byte)1);
      FfiConverterTypeValueOrContainer.INSTANCE.write(value, buf);
    }
  }
}



