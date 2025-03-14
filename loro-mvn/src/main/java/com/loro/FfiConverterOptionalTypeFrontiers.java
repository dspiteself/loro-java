package com.loro;


import java.nio.ByteBuffer;

// public class TestForOptionals {}
public enum FfiConverterOptionalTypeFrontiers implements FfiConverterRustBuffer<Frontiers> {
  INSTANCE;

  @Override
  public Frontiers read(ByteBuffer buf) {
    if (buf.get() == (byte)0) {
      return null;
    }
    return FfiConverterTypeFrontiers.INSTANCE.read(buf);
  }

  @Override
  public long allocationSize(Frontiers value) {
    if (value == null) {
      return 1L;
    } else {
      return 1L + FfiConverterTypeFrontiers.INSTANCE.allocationSize(value);
    }
  }

  @Override
  public void write(Frontiers value, ByteBuffer buf) {
    if (value == null) {
      buf.put((byte)0);
    } else {
      buf.put((byte)1);
      FfiConverterTypeFrontiers.INSTANCE.write(value, buf);
    }
  }
}



