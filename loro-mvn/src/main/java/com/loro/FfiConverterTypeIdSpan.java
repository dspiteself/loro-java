package com.loro;


import java.nio.ByteBuffer;

public enum FfiConverterTypeIdSpan implements FfiConverterRustBuffer<IdSpan> {
  INSTANCE;

  @Override
  public IdSpan read(ByteBuffer buf) {
    return new IdSpan(
      FfiConverterLong.INSTANCE.read(buf),
      FfiConverterTypeCounterSpan.INSTANCE.read(buf)
    );
  }

  @Override
  public long allocationSize(IdSpan value) {
      return (
            FfiConverterLong.INSTANCE.allocationSize(value.peer()) +
            FfiConverterTypeCounterSpan.INSTANCE.allocationSize(value.counter())
      );
  }

  @Override
  public void write(IdSpan value, ByteBuffer buf) {
      FfiConverterLong.INSTANCE.write(value.peer(), buf);
      FfiConverterTypeCounterSpan.INSTANCE.write(value.counter(), buf);
  }
}



