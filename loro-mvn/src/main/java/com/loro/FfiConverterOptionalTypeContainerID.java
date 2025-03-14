package com.loro;


import java.nio.ByteBuffer;

// public class TestForOptionals {}
public enum FfiConverterOptionalTypeContainerID implements FfiConverterRustBuffer<ContainerId> {
  INSTANCE;

  @Override
  public ContainerId read(ByteBuffer buf) {
    if (buf.get() == (byte)0) {
      return null;
    }
    return FfiConverterTypeContainerID.INSTANCE.read(buf);
  }

  @Override
  public long allocationSize(ContainerId value) {
    if (value == null) {
      return 1L;
    } else {
      return 1L + FfiConverterTypeContainerID.INSTANCE.allocationSize(value);
    }
  }

  @Override
  public void write(ContainerId value, ByteBuffer buf) {
    if (value == null) {
      buf.put((byte)0);
    } else {
      buf.put((byte)1);
      FfiConverterTypeContainerID.INSTANCE.write(value, buf);
    }
  }
}



