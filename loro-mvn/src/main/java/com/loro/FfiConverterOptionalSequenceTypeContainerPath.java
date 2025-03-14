package com.loro;


import java.nio.ByteBuffer;
import java.util.List;

// public class TestForOptionals {}
public enum FfiConverterOptionalSequenceTypeContainerPath implements FfiConverterRustBuffer<List<ContainerPath>> {
  INSTANCE;

  @Override
  public List<ContainerPath> read(ByteBuffer buf) {
    if (buf.get() == (byte)0) {
      return null;
    }
    return FfiConverterSequenceTypeContainerPath.INSTANCE.read(buf);
  }

  @Override
  public long allocationSize(List<ContainerPath> value) {
    if (value == null) {
      return 1L;
    } else {
      return 1L + FfiConverterSequenceTypeContainerPath.INSTANCE.allocationSize(value);
    }
  }

  @Override
  public void write(List<ContainerPath> value, ByteBuffer buf) {
    if (value == null) {
      buf.put((byte)0);
    } else {
      buf.put((byte)1);
      FfiConverterSequenceTypeContainerPath.INSTANCE.write(value, buf);
    }
  }
}



