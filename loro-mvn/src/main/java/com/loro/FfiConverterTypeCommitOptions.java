package com.loro;


import java.nio.ByteBuffer;

public enum FfiConverterTypeCommitOptions implements FfiConverterRustBuffer<CommitOptions> {
  INSTANCE;

  @Override
  public CommitOptions read(ByteBuffer buf) {
    return new CommitOptions(
      FfiConverterOptionalString.INSTANCE.read(buf),
      FfiConverterBoolean.INSTANCE.read(buf),
      FfiConverterOptionalLong.INSTANCE.read(buf),
      FfiConverterOptionalString.INSTANCE.read(buf)
    );
  }

  @Override
  public long allocationSize(CommitOptions value) {
      return (
            FfiConverterOptionalString.INSTANCE.allocationSize(value.origin()) +
            FfiConverterBoolean.INSTANCE.allocationSize(value.immediateRenew()) +
            FfiConverterOptionalLong.INSTANCE.allocationSize(value.timestamp()) +
            FfiConverterOptionalString.INSTANCE.allocationSize(value.commitMsg())
      );
  }

  @Override
  public void write(CommitOptions value, ByteBuffer buf) {
      FfiConverterOptionalString.INSTANCE.write(value.origin(), buf);
      FfiConverterBoolean.INSTANCE.write(value.immediateRenew(), buf);
      FfiConverterOptionalLong.INSTANCE.write(value.timestamp(), buf);
      FfiConverterOptionalString.INSTANCE.write(value.commitMsg(), buf);
  }
}



