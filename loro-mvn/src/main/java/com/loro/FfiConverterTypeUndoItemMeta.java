package com.loro;


import java.nio.ByteBuffer;

public enum FfiConverterTypeUndoItemMeta implements FfiConverterRustBuffer<UndoItemMeta> {
  INSTANCE;

  @Override
  public UndoItemMeta read(ByteBuffer buf) {
    return new UndoItemMeta(
      FfiConverterTypeLoroValue.INSTANCE.read(buf),
      FfiConverterSequenceTypeCursorWithPos.INSTANCE.read(buf)
    );
  }

  @Override
  public long allocationSize(UndoItemMeta value) {
      return (
            FfiConverterTypeLoroValue.INSTANCE.allocationSize(value.value()) +
            FfiConverterSequenceTypeCursorWithPos.INSTANCE.allocationSize(value.cursors())
      );
  }

  @Override
  public void write(UndoItemMeta value, ByteBuffer buf) {
      FfiConverterTypeLoroValue.INSTANCE.write(value.value(), buf);
      FfiConverterSequenceTypeCursorWithPos.INSTANCE.write(value.cursors(), buf);
  }
}



