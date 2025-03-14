package com.loro;


import java.nio.ByteBuffer;

public enum FfiConverterTypeTreeDiffItem implements FfiConverterRustBuffer<TreeDiffItem> {
  INSTANCE;

  @Override
  public TreeDiffItem read(ByteBuffer buf) {
    return new TreeDiffItem(
      FfiConverterTypeTreeID.INSTANCE.read(buf),
      FfiConverterTypeTreeExternalDiff.INSTANCE.read(buf)
    );
  }

  @Override
  public long allocationSize(TreeDiffItem value) {
      return (
            FfiConverterTypeTreeID.INSTANCE.allocationSize(value.target()) +
            FfiConverterTypeTreeExternalDiff.INSTANCE.allocationSize(value.action())
      );
  }

  @Override
  public void write(TreeDiffItem value, ByteBuffer buf) {
      FfiConverterTypeTreeID.INSTANCE.write(value.target(), buf);
      FfiConverterTypeTreeExternalDiff.INSTANCE.write(value.action(), buf);
  }
}



