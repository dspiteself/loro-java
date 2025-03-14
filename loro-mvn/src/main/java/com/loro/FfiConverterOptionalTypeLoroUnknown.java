package com.loro;


import java.nio.ByteBuffer;

// public class TestForOptionals {}
public enum FfiConverterOptionalTypeLoroUnknown implements FfiConverterRustBuffer<LoroUnknown> {
  INSTANCE;

  @Override
  public LoroUnknown read(ByteBuffer buf) {
    if (buf.get() == (byte)0) {
      return null;
    }
    return FfiConverterTypeLoroUnknown.INSTANCE.read(buf);
  }

  @Override
  public long allocationSize(LoroUnknown value) {
    if (value == null) {
      return 1L;
    } else {
      return 1L + FfiConverterTypeLoroUnknown.INSTANCE.allocationSize(value);
    }
  }

  @Override
  public void write(LoroUnknown value, ByteBuffer buf) {
    if (value == null) {
      buf.put((byte)0);
    } else {
      buf.put((byte)1);
      FfiConverterTypeLoroUnknown.INSTANCE.write(value, buf);
    }
  }
}



