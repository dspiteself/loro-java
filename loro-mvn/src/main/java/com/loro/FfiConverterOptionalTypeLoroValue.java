package com.loro;


import java.nio.ByteBuffer;

// public class TestForOptionals {}
public enum FfiConverterOptionalTypeLoroValue implements FfiConverterRustBuffer<LoroValue> {
  INSTANCE;

  @Override
  public LoroValue read(ByteBuffer buf) {
    if (buf.get() == (byte)0) {
      return null;
    }
    return FfiConverterTypeLoroValue.INSTANCE.read(buf);
  }

  @Override
  public long allocationSize(LoroValue value) {
    if (value == null) {
      return 1L;
    } else {
      return 1L + FfiConverterTypeLoroValue.INSTANCE.allocationSize(value);
    }
  }

  @Override
  public void write(LoroValue value, ByteBuffer buf) {
    if (value == null) {
      buf.put((byte)0);
    } else {
      buf.put((byte)1);
      FfiConverterTypeLoroValue.INSTANCE.write(value, buf);
    }
  }
}



