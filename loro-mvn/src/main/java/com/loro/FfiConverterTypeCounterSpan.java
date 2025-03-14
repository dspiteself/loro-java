package com.loro;


import java.nio.ByteBuffer;

public enum FfiConverterTypeCounterSpan implements FfiConverterRustBuffer<CounterSpan> {
  INSTANCE;

  @Override
  public CounterSpan read(ByteBuffer buf) {
    return new CounterSpan(
      FfiConverterInteger.INSTANCE.read(buf),
      FfiConverterInteger.INSTANCE.read(buf)
    );
  }

  @Override
  public long allocationSize(CounterSpan value) {
      return (
            FfiConverterInteger.INSTANCE.allocationSize(value.start()) +
            FfiConverterInteger.INSTANCE.allocationSize(value.end())
      );
  }

  @Override
  public void write(CounterSpan value, ByteBuffer buf) {
      FfiConverterInteger.INSTANCE.write(value.start(), buf);
      FfiConverterInteger.INSTANCE.write(value.end(), buf);
  }
}



