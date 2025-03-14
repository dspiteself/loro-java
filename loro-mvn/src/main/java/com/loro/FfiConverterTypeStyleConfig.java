package com.loro;


import java.nio.ByteBuffer;

public enum FfiConverterTypeStyleConfig implements FfiConverterRustBuffer<StyleConfig> {
  INSTANCE;

  @Override
  public StyleConfig read(ByteBuffer buf) {
    return new StyleConfig(
      FfiConverterTypeExpandType.INSTANCE.read(buf)
    );
  }

  @Override
  public long allocationSize(StyleConfig value) {
      return (
            FfiConverterTypeExpandType.INSTANCE.allocationSize(value.expand())
      );
  }

  @Override
  public void write(StyleConfig value, ByteBuffer buf) {
      FfiConverterTypeExpandType.INSTANCE.write(value.expand(), buf);
  }
}



