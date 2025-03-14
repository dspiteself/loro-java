package com.loro;


import java.nio.ByteBuffer;

public enum FfiConverterTypeContainerDiff implements FfiConverterRustBuffer<ContainerDiff> {
  INSTANCE;

  @Override
  public ContainerDiff read(ByteBuffer buf) {
    return new ContainerDiff(
      FfiConverterTypeContainerID.INSTANCE.read(buf),
      FfiConverterSequenceTypePathItem.INSTANCE.read(buf),
      FfiConverterBoolean.INSTANCE.read(buf),
      FfiConverterTypeDiff.INSTANCE.read(buf)
    );
  }

  @Override
  public long allocationSize(ContainerDiff value) {
      return (
            FfiConverterTypeContainerID.INSTANCE.allocationSize(value.target()) +
            FfiConverterSequenceTypePathItem.INSTANCE.allocationSize(value.path()) +
            FfiConverterBoolean.INSTANCE.allocationSize(value.isUnknown()) +
            FfiConverterTypeDiff.INSTANCE.allocationSize(value.diff())
      );
  }

  @Override
  public void write(ContainerDiff value, ByteBuffer buf) {
      FfiConverterTypeContainerID.INSTANCE.write(value.target(), buf);
      FfiConverterSequenceTypePathItem.INSTANCE.write(value.path(), buf);
      FfiConverterBoolean.INSTANCE.write(value.isUnknown(), buf);
      FfiConverterTypeDiff.INSTANCE.write(value.diff(), buf);
  }
}



