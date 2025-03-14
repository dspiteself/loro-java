package com.loro;


import java.nio.ByteBuffer;

public enum FfiConverterTypeTreeID implements FfiConverterRustBuffer<TreeId> {
  INSTANCE;

  @Override
  public TreeId read(ByteBuffer buf) {
    return new TreeId(
      FfiConverterLong.INSTANCE.read(buf),
      FfiConverterInteger.INSTANCE.read(buf)
    );
  }

  @Override
  public long allocationSize(TreeId value) {
      return (
            FfiConverterLong.INSTANCE.allocationSize(value.peer()) +
            FfiConverterInteger.INSTANCE.allocationSize(value.counter())
      );
  }

  @Override
  public void write(TreeId value, ByteBuffer buf) {
      FfiConverterLong.INSTANCE.write(value.peer(), buf);
      FfiConverterInteger.INSTANCE.write(value.counter(), buf);
  }
}



