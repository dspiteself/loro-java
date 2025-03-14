package com.loro;


import java.nio.ByteBuffer;
import java.util.List;

// public class TestForOptionals {}
public enum FfiConverterOptionalSequenceTypeTreeID implements FfiConverterRustBuffer<List<TreeId>> {
  INSTANCE;

  @Override
  public List<TreeId> read(ByteBuffer buf) {
    if (buf.get() == (byte)0) {
      return null;
    }
    return FfiConverterSequenceTypeTreeID.INSTANCE.read(buf);
  }

  @Override
  public long allocationSize(List<TreeId> value) {
    if (value == null) {
      return 1L;
    } else {
      return 1L + FfiConverterSequenceTypeTreeID.INSTANCE.allocationSize(value);
    }
  }

  @Override
  public void write(List<TreeId> value, ByteBuffer buf) {
    if (value == null) {
      buf.put((byte)0);
    } else {
      buf.put((byte)1);
      FfiConverterSequenceTypeTreeID.INSTANCE.write(value, buf);
    }
  }
}



