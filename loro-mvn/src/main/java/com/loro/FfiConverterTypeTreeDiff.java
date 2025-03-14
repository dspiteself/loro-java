package com.loro;


import java.nio.ByteBuffer;

public enum FfiConverterTypeTreeDiff implements FfiConverterRustBuffer<TreeDiff> {
  INSTANCE;

  @Override
  public TreeDiff read(ByteBuffer buf) {
    return new TreeDiff(
      FfiConverterSequenceTypeTreeDiffItem.INSTANCE.read(buf)
    );
  }

  @Override
  public long allocationSize(TreeDiff value) {
      return (
            FfiConverterSequenceTypeTreeDiffItem.INSTANCE.allocationSize(value.diff())
      );
  }

  @Override
  public void write(TreeDiff value, ByteBuffer buf) {
      FfiConverterSequenceTypeTreeDiffItem.INSTANCE.write(value.diff(), buf);
  }
}



