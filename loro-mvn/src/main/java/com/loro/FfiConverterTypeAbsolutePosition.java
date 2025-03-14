package com.loro;


import java.nio.ByteBuffer;

public enum FfiConverterTypeAbsolutePosition implements FfiConverterRustBuffer<AbsolutePosition> {
  INSTANCE;

  @Override
  public AbsolutePosition read(ByteBuffer buf) {
    return new AbsolutePosition(
      FfiConverterInteger.INSTANCE.read(buf),
      FfiConverterTypeSide.INSTANCE.read(buf)
    );
  }

  @Override
  public long allocationSize(AbsolutePosition value) {
      return (
            FfiConverterInteger.INSTANCE.allocationSize(value.pos()) +
            FfiConverterTypeSide.INSTANCE.allocationSize(value.side())
      );
  }

  @Override
  public void write(AbsolutePosition value, ByteBuffer buf) {
      FfiConverterInteger.INSTANCE.write(value.pos(), buf);
      FfiConverterTypeSide.INSTANCE.write(value.side(), buf);
  }
}



