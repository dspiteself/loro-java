package com.loro;


import java.nio.ByteBuffer;

public enum FfiConverterTypeCursorWithPos implements FfiConverterRustBuffer<CursorWithPos> {
  INSTANCE;

  @Override
  public CursorWithPos read(ByteBuffer buf) {
    return new CursorWithPos(
      FfiConverterTypeCursor.INSTANCE.read(buf),
      FfiConverterTypeAbsolutePosition.INSTANCE.read(buf)
    );
  }

  @Override
  public long allocationSize(CursorWithPos value) {
      return (
            FfiConverterTypeCursor.INSTANCE.allocationSize(value.cursor()) +
            FfiConverterTypeAbsolutePosition.INSTANCE.allocationSize(value.pos())
      );
  }

  @Override
  public void write(CursorWithPos value, ByteBuffer buf) {
      FfiConverterTypeCursor.INSTANCE.write(value.cursor(), buf);
      FfiConverterTypeAbsolutePosition.INSTANCE.write(value.pos(), buf);
  }
}



