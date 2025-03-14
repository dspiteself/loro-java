package com.loro;


import java.nio.ByteBuffer;

// public class TestForOptionals {}
public enum FfiConverterOptionalTypeLoroMovableList implements FfiConverterRustBuffer<LoroMovableList> {
  INSTANCE;

  @Override
  public LoroMovableList read(ByteBuffer buf) {
    if (buf.get() == (byte)0) {
      return null;
    }
    return FfiConverterTypeLoroMovableList.INSTANCE.read(buf);
  }

  @Override
  public long allocationSize(LoroMovableList value) {
    if (value == null) {
      return 1L;
    } else {
      return 1L + FfiConverterTypeLoroMovableList.INSTANCE.allocationSize(value);
    }
  }

  @Override
  public void write(LoroMovableList value, ByteBuffer buf) {
    if (value == null) {
      buf.put((byte)0);
    } else {
      buf.put((byte)1);
      FfiConverterTypeLoroMovableList.INSTANCE.write(value, buf);
    }
  }
}



