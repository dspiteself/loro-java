package com.loro;


import java.nio.ByteBuffer;

// public class TestForOptionals {}
public enum FfiConverterOptionalTypeStyleConfig implements FfiConverterRustBuffer<StyleConfig> {
  INSTANCE;

  @Override
  public StyleConfig read(ByteBuffer buf) {
    if (buf.get() == (byte)0) {
      return null;
    }
    return FfiConverterTypeStyleConfig.INSTANCE.read(buf);
  }

  @Override
  public long allocationSize(StyleConfig value) {
    if (value == null) {
      return 1L;
    } else {
      return 1L + FfiConverterTypeStyleConfig.INSTANCE.allocationSize(value);
    }
  }

  @Override
  public void write(StyleConfig value, ByteBuffer buf) {
    if (value == null) {
      buf.put((byte)0);
    } else {
      buf.put((byte)1);
      FfiConverterTypeStyleConfig.INSTANCE.write(value, buf);
    }
  }
}



