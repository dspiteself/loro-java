package com.loro;


import java.nio.ByteBuffer;

// public class TestForOptionals {}
public enum FfiConverterOptionalTypeLoroList implements FfiConverterRustBuffer<LoroList> {
  INSTANCE;

  @Override
  public LoroList read(ByteBuffer buf) {
    if (buf.get() == (byte)0) {
      return null;
    }
    return FfiConverterTypeLoroList.INSTANCE.read(buf);
  }

  @Override
  public long allocationSize(LoroList value) {
    if (value == null) {
      return 1L;
    } else {
      return 1L + FfiConverterTypeLoroList.INSTANCE.allocationSize(value);
    }
  }

  @Override
  public void write(LoroList value, ByteBuffer buf) {
    if (value == null) {
      buf.put((byte)0);
    } else {
      buf.put((byte)1);
      FfiConverterTypeLoroList.INSTANCE.write(value, buf);
    }
  }
}



