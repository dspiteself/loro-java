package com.loro;


import java.nio.ByteBuffer;

public enum FfiConverterTypeChangeMeta implements FfiConverterRustBuffer<ChangeMeta> {
  INSTANCE;

  @Override
  public ChangeMeta read(ByteBuffer buf) {
    return new ChangeMeta(
      FfiConverterInteger.INSTANCE.read(buf),
      FfiConverterTypeID.INSTANCE.read(buf),
      FfiConverterLong.INSTANCE.read(buf),
      FfiConverterOptionalString.INSTANCE.read(buf),
      FfiConverterTypeFrontiers.INSTANCE.read(buf),
      FfiConverterInteger.INSTANCE.read(buf)
    );
  }

  @Override
  public long allocationSize(ChangeMeta value) {
      return (
            FfiConverterInteger.INSTANCE.allocationSize(value.lamport()) +
            FfiConverterTypeID.INSTANCE.allocationSize(value.id()) +
            FfiConverterLong.INSTANCE.allocationSize(value.timestamp()) +
            FfiConverterOptionalString.INSTANCE.allocationSize(value.message()) +
            FfiConverterTypeFrontiers.INSTANCE.allocationSize(value.deps()) +
            FfiConverterInteger.INSTANCE.allocationSize(value.len())
      );
  }

  @Override
  public void write(ChangeMeta value, ByteBuffer buf) {
      FfiConverterInteger.INSTANCE.write(value.lamport(), buf);
      FfiConverterTypeID.INSTANCE.write(value.id(), buf);
      FfiConverterLong.INSTANCE.write(value.timestamp(), buf);
      FfiConverterOptionalString.INSTANCE.write(value.message(), buf);
      FfiConverterTypeFrontiers.INSTANCE.write(value.deps(), buf);
      FfiConverterInteger.INSTANCE.write(value.len(), buf);
  }
}



