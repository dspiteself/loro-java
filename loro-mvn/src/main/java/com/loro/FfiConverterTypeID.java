package com.loro;


import java.nio.ByteBuffer;

public enum FfiConverterTypeID implements FfiConverterRustBuffer<Id> {
  INSTANCE;

  @Override
  public Id read(ByteBuffer buf) {
    return new Id(
      FfiConverterLong.INSTANCE.read(buf),
      FfiConverterInteger.INSTANCE.read(buf)
    );
  }

  @Override
  public long allocationSize(Id value) {
      return (
            FfiConverterLong.INSTANCE.allocationSize(value.peer()) +
            FfiConverterInteger.INSTANCE.allocationSize(value.counter())
      );
  }

  @Override
  public void write(Id value, ByteBuffer buf) {
      FfiConverterLong.INSTANCE.write(value.peer(), buf);
      FfiConverterInteger.INSTANCE.write(value.counter(), buf);
  }
}



