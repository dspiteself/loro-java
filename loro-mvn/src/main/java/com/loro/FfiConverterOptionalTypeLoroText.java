package com.loro;


import java.nio.ByteBuffer;

// public class TestForOptionals {}
public enum FfiConverterOptionalTypeLoroText implements FfiConverterRustBuffer<LoroText> {
  INSTANCE;

  @Override
  public LoroText read(ByteBuffer buf) {
    if (buf.get() == (byte)0) {
      return null;
    }
    return FfiConverterTypeLoroText.INSTANCE.read(buf);
  }

  @Override
  public long allocationSize(LoroText value) {
    if (value == null) {
      return 1L;
    } else {
      return 1L + FfiConverterTypeLoroText.INSTANCE.allocationSize(value);
    }
  }

  @Override
  public void write(LoroText value, ByteBuffer buf) {
    if (value == null) {
      buf.put((byte)0);
    } else {
      buf.put((byte)1);
      FfiConverterTypeLoroText.INSTANCE.write(value, buf);
    }
  }
}



