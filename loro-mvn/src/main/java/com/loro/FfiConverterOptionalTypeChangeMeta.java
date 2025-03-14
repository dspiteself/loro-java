package com.loro;


import java.nio.ByteBuffer;

// public class TestForOptionals {}
public enum FfiConverterOptionalTypeChangeMeta implements FfiConverterRustBuffer<ChangeMeta> {
  INSTANCE;

  @Override
  public ChangeMeta read(ByteBuffer buf) {
    if (buf.get() == (byte)0) {
      return null;
    }
    return FfiConverterTypeChangeMeta.INSTANCE.read(buf);
  }

  @Override
  public long allocationSize(ChangeMeta value) {
    if (value == null) {
      return 1L;
    } else {
      return 1L + FfiConverterTypeChangeMeta.INSTANCE.allocationSize(value);
    }
  }

  @Override
  public void write(ChangeMeta value, ByteBuffer buf) {
    if (value == null) {
      buf.put((byte)0);
    } else {
      buf.put((byte)1);
      FfiConverterTypeChangeMeta.INSTANCE.write(value, buf);
    }
  }
}



