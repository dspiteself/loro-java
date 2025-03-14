package com.loro;


import java.nio.ByteBuffer;

// public class TestForOptionals {}
public enum FfiConverterOptionalTypeContainerType implements FfiConverterRustBuffer<ContainerType> {
  INSTANCE;

  @Override
  public ContainerType read(ByteBuffer buf) {
    if (buf.get() == (byte)0) {
      return null;
    }
    return FfiConverterTypeContainerType.INSTANCE.read(buf);
  }

  @Override
  public long allocationSize(ContainerType value) {
    if (value == null) {
      return 1L;
    } else {
      return 1L + FfiConverterTypeContainerType.INSTANCE.allocationSize(value);
    }
  }

  @Override
  public void write(ContainerType value, ByteBuffer buf) {
    if (value == null) {
      buf.put((byte)0);
    } else {
      buf.put((byte)1);
      FfiConverterTypeContainerType.INSTANCE.write(value, buf);
    }
  }
}



