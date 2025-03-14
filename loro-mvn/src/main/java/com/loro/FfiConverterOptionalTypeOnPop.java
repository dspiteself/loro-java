package com.loro;


import java.nio.ByteBuffer;

// public class TestForOptionals {}
public enum FfiConverterOptionalTypeOnPop implements FfiConverterRustBuffer<OnPop> {
  INSTANCE;

  @Override
  public OnPop read(ByteBuffer buf) {
    if (buf.get() == (byte)0) {
      return null;
    }
    return FfiConverterTypeOnPop.INSTANCE.read(buf);
  }

  @Override
  public long allocationSize(OnPop value) {
    if (value == null) {
      return 1L;
    } else {
      return 1L + FfiConverterTypeOnPop.INSTANCE.allocationSize(value);
    }
  }

  @Override
  public void write(OnPop value, ByteBuffer buf) {
    if (value == null) {
      buf.put((byte)0);
    } else {
      buf.put((byte)1);
      FfiConverterTypeOnPop.INSTANCE.write(value, buf);
    }
  }
}



