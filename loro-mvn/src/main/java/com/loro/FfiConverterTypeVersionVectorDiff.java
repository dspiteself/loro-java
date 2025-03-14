package com.loro;


import java.nio.ByteBuffer;

public enum FfiConverterTypeVersionVectorDiff implements FfiConverterRustBuffer<VersionVectorDiff> {
  INSTANCE;

  @Override
  public VersionVectorDiff read(ByteBuffer buf) {
    return new VersionVectorDiff(
      FfiConverterMapLongTypeCounterSpan.INSTANCE.read(buf),
      FfiConverterMapLongTypeCounterSpan.INSTANCE.read(buf)
    );
  }

  @Override
  public long allocationSize(VersionVectorDiff value) {
      return (
            FfiConverterMapLongTypeCounterSpan.INSTANCE.allocationSize(value.retreat()) +
            FfiConverterMapLongTypeCounterSpan.INSTANCE.allocationSize(value.forward())
      );
  }

  @Override
  public void write(VersionVectorDiff value, ByteBuffer buf) {
      FfiConverterMapLongTypeCounterSpan.INSTANCE.write(value.retreat(), buf);
      FfiConverterMapLongTypeCounterSpan.INSTANCE.write(value.forward(), buf);
  }
}


