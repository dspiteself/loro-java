package com.loro;


import java.nio.ByteBuffer;

public enum FfiConverterTypeContainerPath implements FfiConverterRustBuffer<ContainerPath> {
  INSTANCE;

  @Override
  public ContainerPath read(ByteBuffer buf) {
    return new ContainerPath(
      FfiConverterTypeContainerID.INSTANCE.read(buf),
      FfiConverterTypeIndex.INSTANCE.read(buf)
    );
  }

  @Override
  public long allocationSize(ContainerPath value) {
      return (
            FfiConverterTypeContainerID.INSTANCE.allocationSize(value.id()) +
            FfiConverterTypeIndex.INSTANCE.allocationSize(value.path())
      );
  }

  @Override
  public void write(ContainerPath value, ByteBuffer buf) {
      FfiConverterTypeContainerID.INSTANCE.write(value.id(), buf);
      FfiConverterTypeIndex.INSTANCE.write(value.path(), buf);
  }
}



