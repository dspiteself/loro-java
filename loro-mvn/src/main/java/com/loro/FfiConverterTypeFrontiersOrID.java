package com.loro;


import java.nio.ByteBuffer;

public enum FfiConverterTypeFrontiersOrID implements FfiConverterRustBuffer<FrontiersOrId> {
  INSTANCE;

  @Override
  public FrontiersOrId read(ByteBuffer buf) {
    return new FrontiersOrId(
      FfiConverterOptionalTypeFrontiers.INSTANCE.read(buf),
      FfiConverterOptionalTypeID.INSTANCE.read(buf)
    );
  }

  @Override
  public long allocationSize(FrontiersOrId value) {
      return (
            FfiConverterOptionalTypeFrontiers.INSTANCE.allocationSize(value.frontiers()) +
            FfiConverterOptionalTypeID.INSTANCE.allocationSize(value.id())
      );
  }

  @Override
  public void write(FrontiersOrId value, ByteBuffer buf) {
      FfiConverterOptionalTypeFrontiers.INSTANCE.write(value.frontiers(), buf);
      FfiConverterOptionalTypeID.INSTANCE.write(value.id(), buf);
  }
}



