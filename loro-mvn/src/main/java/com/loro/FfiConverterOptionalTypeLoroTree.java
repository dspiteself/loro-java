package com.loro;


import java.nio.ByteBuffer;

// public class TestForOptionals {}
public enum FfiConverterOptionalTypeLoroTree implements FfiConverterRustBuffer<LoroTree> {
  INSTANCE;

  @Override
  public LoroTree read(ByteBuffer buf) {
    if (buf.get() == (byte)0) {
      return null;
    }
    return FfiConverterTypeLoroTree.INSTANCE.read(buf);
  }

  @Override
  public long allocationSize(LoroTree value) {
    if (value == null) {
      return 1L;
    } else {
      return 1L + FfiConverterTypeLoroTree.INSTANCE.allocationSize(value);
    }
  }

  @Override
  public void write(LoroTree value, ByteBuffer buf) {
    if (value == null) {
      buf.put((byte)0);
    } else {
      buf.put((byte)1);
      FfiConverterTypeLoroTree.INSTANCE.write(value, buf);
    }
  }
}



