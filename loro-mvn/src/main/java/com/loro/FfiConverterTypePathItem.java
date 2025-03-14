package com.loro;


import java.nio.ByteBuffer;

public enum FfiConverterTypePathItem implements FfiConverterRustBuffer<PathItem> {
  INSTANCE;

  @Override
  public PathItem read(ByteBuffer buf) {
    return new PathItem(
      FfiConverterTypeContainerID.INSTANCE.read(buf),
      FfiConverterTypeIndex.INSTANCE.read(buf)
    );
  }

  @Override
  public long allocationSize(PathItem value) {
      return (
            FfiConverterTypeContainerID.INSTANCE.allocationSize(value.container()) +
            FfiConverterTypeIndex.INSTANCE.allocationSize(value.index())
      );
  }

  @Override
  public void write(PathItem value, ByteBuffer buf) {
      FfiConverterTypeContainerID.INSTANCE.write(value.container(), buf);
      FfiConverterTypeIndex.INSTANCE.write(value.index(), buf);
  }
}



