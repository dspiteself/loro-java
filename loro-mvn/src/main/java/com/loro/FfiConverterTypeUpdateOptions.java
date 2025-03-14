package com.loro;


import java.nio.ByteBuffer;

public enum FfiConverterTypeUpdateOptions implements FfiConverterRustBuffer<UpdateOptions> {
  INSTANCE;

  @Override
  public UpdateOptions read(ByteBuffer buf) {
    return new UpdateOptions(
      FfiConverterOptionalDouble.INSTANCE.read(buf),
      FfiConverterBoolean.INSTANCE.read(buf)
    );
  }

  @Override
  public long allocationSize(UpdateOptions value) {
      return (
            FfiConverterOptionalDouble.INSTANCE.allocationSize(value.timeoutMs()) +
            FfiConverterBoolean.INSTANCE.allocationSize(value.useRefinedDiff())
      );
  }

  @Override
  public void write(UpdateOptions value, ByteBuffer buf) {
      FfiConverterOptionalDouble.INSTANCE.write(value.timeoutMs(), buf);
      FfiConverterBoolean.INSTANCE.write(value.useRefinedDiff(), buf);
  }
}



