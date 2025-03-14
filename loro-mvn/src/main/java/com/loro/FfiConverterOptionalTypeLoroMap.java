package com.loro;


import java.nio.ByteBuffer;

// public class TestForOptionals {}
public enum FfiConverterOptionalTypeLoroMap implements FfiConverterRustBuffer<LoroMap> {
  INSTANCE;

  @Override
  public LoroMap read(ByteBuffer buf) {
    if (buf.get() == (byte)0) {
      return null;
    }
    return FfiConverterTypeLoroMap.INSTANCE.read(buf);
  }

  @Override
  public long allocationSize(LoroMap value) {
    if (value == null) {
      return 1L;
    } else {
      return 1L + FfiConverterTypeLoroMap.INSTANCE.allocationSize(value);
    }
  }

  @Override
  public void write(LoroMap value, ByteBuffer buf) {
    if (value == null) {
      buf.put((byte)0);
    } else {
      buf.put((byte)1);
      FfiConverterTypeLoroMap.INSTANCE.write(value, buf);
    }
  }
}



