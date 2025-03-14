package com.loro;


import java.nio.ByteBuffer;

// public class TestForOptionals {}
public enum FfiConverterOptionalTypeOnPush implements FfiConverterRustBuffer<OnPush> {
  INSTANCE;

  @Override
  public OnPush read(ByteBuffer buf) {
    if (buf.get() == (byte)0) {
      return null;
    }
    return FfiConverterTypeOnPush.INSTANCE.read(buf);
  }

  @Override
  public long allocationSize(OnPush value) {
    if (value == null) {
      return 1L;
    } else {
      return 1L + FfiConverterTypeOnPush.INSTANCE.allocationSize(value);
    }
  }

  @Override
  public void write(OnPush value, ByteBuffer buf) {
    if (value == null) {
      buf.put((byte)0);
    } else {
      buf.put((byte)1);
      FfiConverterTypeOnPush.INSTANCE.write(value, buf);
    }
  }
}



