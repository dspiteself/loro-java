package com.loro;


import java.nio.ByteBuffer;

public enum FfiConverterTypeMapDelta implements FfiConverterRustBuffer<MapDelta> {
  INSTANCE;

  @Override
  public MapDelta read(ByteBuffer buf) {
    return new MapDelta(
      FfiConverterMapStringOptionalTypeValueOrContainer.INSTANCE.read(buf)
    );
  }

  @Override
  public long allocationSize(MapDelta value) {
      return (
            FfiConverterMapStringOptionalTypeValueOrContainer.INSTANCE.allocationSize(value.updated())
      );
  }

  @Override
  public void write(MapDelta value, ByteBuffer buf) {
      FfiConverterMapStringOptionalTypeValueOrContainer.INSTANCE.write(value.updated(), buf);
  }
}



