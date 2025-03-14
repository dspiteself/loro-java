package com.loro;


import java.nio.ByteBuffer;

public enum FfiConverterTypeAwarenessPeerUpdate implements FfiConverterRustBuffer<AwarenessPeerUpdate> {
  INSTANCE;

  @Override
  public AwarenessPeerUpdate read(ByteBuffer buf) {
    return new AwarenessPeerUpdate(
      FfiConverterSequenceLong.INSTANCE.read(buf),
      FfiConverterSequenceLong.INSTANCE.read(buf)
    );
  }

  @Override
  public long allocationSize(AwarenessPeerUpdate value) {
      return (
            FfiConverterSequenceLong.INSTANCE.allocationSize(value.updated()) +
            FfiConverterSequenceLong.INSTANCE.allocationSize(value.added())
      );
  }

  @Override
  public void write(AwarenessPeerUpdate value, ByteBuffer buf) {
      FfiConverterSequenceLong.INSTANCE.write(value.updated(), buf);
      FfiConverterSequenceLong.INSTANCE.write(value.added(), buf);
  }
}



