package com.loro;


import java.nio.ByteBuffer;

public enum FfiConverterTypePosQueryResult implements FfiConverterRustBuffer<PosQueryResult> {
  INSTANCE;

  @Override
  public PosQueryResult read(ByteBuffer buf) {
    return new PosQueryResult(
      FfiConverterOptionalTypeCursor.INSTANCE.read(buf),
      FfiConverterTypeAbsolutePosition.INSTANCE.read(buf)
    );
  }

  @Override
  public long allocationSize(PosQueryResult value) {
      return (
            FfiConverterOptionalTypeCursor.INSTANCE.allocationSize(value.update()) +
            FfiConverterTypeAbsolutePosition.INSTANCE.allocationSize(value.current())
      );
  }

  @Override
  public void write(PosQueryResult value, ByteBuffer buf) {
      FfiConverterOptionalTypeCursor.INSTANCE.write(value.update(), buf);
      FfiConverterTypeAbsolutePosition.INSTANCE.write(value.current(), buf);
  }
}



