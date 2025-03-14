package com.loro;


import java.nio.ByteBuffer;

public enum FfiConverterTypeIdLp implements FfiConverterRustBuffer<IdLp> {
  INSTANCE;

  @Override
  public IdLp read(ByteBuffer buf) {
    return new IdLp(
      FfiConverterInteger.INSTANCE.read(buf),
      FfiConverterLong.INSTANCE.read(buf)
    );
  }

  @Override
  public long allocationSize(IdLp value) {
      return (
            FfiConverterInteger.INSTANCE.allocationSize(value.lamport()) +
            FfiConverterLong.INSTANCE.allocationSize(value.peer())
      );
  }

  @Override
  public void write(IdLp value, ByteBuffer buf) {
      FfiConverterInteger.INSTANCE.write(value.lamport(), buf);
      FfiConverterLong.INSTANCE.write(value.peer(), buf);
  }
}



