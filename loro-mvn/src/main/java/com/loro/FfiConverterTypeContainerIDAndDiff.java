package com.loro;


import java.nio.ByteBuffer;

public enum FfiConverterTypeContainerIDAndDiff implements FfiConverterRustBuffer<ContainerIdAndDiff> {
  INSTANCE;

  @Override
  public ContainerIdAndDiff read(ByteBuffer buf) {
    return new ContainerIdAndDiff(
      FfiConverterTypeContainerID.INSTANCE.read(buf),
      FfiConverterTypeDiff.INSTANCE.read(buf)
    );
  }

  @Override
  public long allocationSize(ContainerIdAndDiff value) {
      return (
            FfiConverterTypeContainerID.INSTANCE.allocationSize(value.cid()) +
            FfiConverterTypeDiff.INSTANCE.allocationSize(value.diff())
      );
  }

  @Override
  public void write(ContainerIdAndDiff value, ByteBuffer buf) {
      FfiConverterTypeContainerID.INSTANCE.write(value.cid(), buf);
      FfiConverterTypeDiff.INSTANCE.write(value.diff(), buf);
  }
}



