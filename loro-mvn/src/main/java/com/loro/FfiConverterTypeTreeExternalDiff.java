package com.loro;


import java.nio.ByteBuffer;

public enum FfiConverterTypeTreeExternalDiff implements FfiConverterRustBuffer<TreeExternalDiff> {
    INSTANCE;

    @Override
    public TreeExternalDiff read(ByteBuffer buf) {
      return switch (buf.getInt()) {
        case 1 -> new TreeExternalDiff.Create(FfiConverterTypeTreeParentId.INSTANCE.read(buf),
          FfiConverterInteger.INSTANCE.read(buf),
          FfiConverterString.INSTANCE.read(buf)
          );
        case 2 -> new TreeExternalDiff.Move(FfiConverterTypeTreeParentId.INSTANCE.read(buf),
          FfiConverterInteger.INSTANCE.read(buf),
          FfiConverterString.INSTANCE.read(buf),
          FfiConverterTypeTreeParentId.INSTANCE.read(buf),
          FfiConverterInteger.INSTANCE.read(buf)
          );
        case 3 -> new TreeExternalDiff.Delete(FfiConverterTypeTreeParentId.INSTANCE.read(buf),
          FfiConverterInteger.INSTANCE.read(buf)
          );
        default ->
          throw new RuntimeException("invalid enum value, something is very wrong!");
      };
    }

    @Override
    public long allocationSize(TreeExternalDiff value) {
        return switch (value) {
          case TreeExternalDiff.Create(var parent, var index, var fractionalIndex) ->
            (4L
            + FfiConverterTypeTreeParentId.INSTANCE.allocationSize(parent)
            + FfiConverterInteger.INSTANCE.allocationSize(index)
            + FfiConverterString.INSTANCE.allocationSize(fractionalIndex));
          case TreeExternalDiff.Move(var parent, var index, var fractionalIndex, var oldParent, var oldIndex) ->
            (4L
            + FfiConverterTypeTreeParentId.INSTANCE.allocationSize(parent)
            + FfiConverterInteger.INSTANCE.allocationSize(index)
            + FfiConverterString.INSTANCE.allocationSize(fractionalIndex)
            + FfiConverterTypeTreeParentId.INSTANCE.allocationSize(oldParent)
            + FfiConverterInteger.INSTANCE.allocationSize(oldIndex));
          case TreeExternalDiff.Delete(var oldParent, var oldIndex) ->
            (4L
            + FfiConverterTypeTreeParentId.INSTANCE.allocationSize(oldParent)
            + FfiConverterInteger.INSTANCE.allocationSize(oldIndex));
        };
    }

    @Override
    public void write(TreeExternalDiff value, ByteBuffer buf) {
      switch (value) {
        case TreeExternalDiff.Create(var parent, var index, var fractionalIndex) -> {
          buf.putInt(1);
          FfiConverterTypeTreeParentId.INSTANCE.write(parent, buf);
          FfiConverterInteger.INSTANCE.write(index, buf);
          FfiConverterString.INSTANCE.write(fractionalIndex, buf);
        }
        case TreeExternalDiff.Move(var parent, var index, var fractionalIndex, var oldParent, var oldIndex) -> {
          buf.putInt(2);
          FfiConverterTypeTreeParentId.INSTANCE.write(parent, buf);
          FfiConverterInteger.INSTANCE.write(index, buf);
          FfiConverterString.INSTANCE.write(fractionalIndex, buf);
          FfiConverterTypeTreeParentId.INSTANCE.write(oldParent, buf);
          FfiConverterInteger.INSTANCE.write(oldIndex, buf);
        }
        case TreeExternalDiff.Delete(var oldParent, var oldIndex) -> {
          buf.putInt(3);
          FfiConverterTypeTreeParentId.INSTANCE.write(oldParent, buf);
          FfiConverterInteger.INSTANCE.write(oldIndex, buf);
        }
      };
    }
}




