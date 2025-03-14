package com.loro;


import java.nio.ByteBuffer;

// public class TestForOptionals {}
public enum FfiConverterOptionalTypeLoroDoc implements FfiConverterRustBuffer<LoroDoc> {
  INSTANCE;

  @Override
  public LoroDoc read(ByteBuffer buf) {
    if (buf.get() == (byte)0) {
      return null;
    }
    return FfiConverterTypeLoroDoc.INSTANCE.read(buf);
  }

  @Override
  public long allocationSize(LoroDoc value) {
    if (value == null) {
      return 1L;
    } else {
      return 1L + FfiConverterTypeLoroDoc.INSTANCE.allocationSize(value);
    }
  }

  @Override
  public void write(LoroDoc value, ByteBuffer buf) {
    if (value == null) {
      buf.put((byte)0);
    } else {
      buf.put((byte)1);
      FfiConverterTypeLoroDoc.INSTANCE.write(value, buf);
    }
  }
}



