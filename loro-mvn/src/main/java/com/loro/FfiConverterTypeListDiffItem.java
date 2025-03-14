package com.loro;


import java.nio.ByteBuffer;

public enum FfiConverterTypeListDiffItem implements FfiConverterRustBuffer<ListDiffItem> {
    INSTANCE;

    @Override
    public ListDiffItem read(ByteBuffer buf) {
      return switch (buf.getInt()) {
        case 1 -> new ListDiffItem.Insert(FfiConverterSequenceTypeValueOrContainer.INSTANCE.read(buf),
          FfiConverterBoolean.INSTANCE.read(buf)
          );
        case 2 -> new ListDiffItem.Delete(FfiConverterInteger.INSTANCE.read(buf)
          );
        case 3 -> new ListDiffItem.Retain(FfiConverterInteger.INSTANCE.read(buf)
          );
        default ->
          throw new RuntimeException("invalid enum value, something is very wrong!");
      };
    }

    @Override
    public long allocationSize(ListDiffItem value) {
        return switch (value) {
          case ListDiffItem.Insert(var insert, var isMove) ->
            (4L
            + FfiConverterSequenceTypeValueOrContainer.INSTANCE.allocationSize(insert)
            + FfiConverterBoolean.INSTANCE.allocationSize(isMove));
          case ListDiffItem.Delete(var delete) ->
            (4L
            + FfiConverterInteger.INSTANCE.allocationSize(delete));
          case ListDiffItem.Retain(var retain) ->
            (4L
            + FfiConverterInteger.INSTANCE.allocationSize(retain));
        };
    }

    @Override
    public void write(ListDiffItem value, ByteBuffer buf) {
      switch (value) {
        case ListDiffItem.Insert(var insert, var isMove) -> {
          buf.putInt(1);
          FfiConverterSequenceTypeValueOrContainer.INSTANCE.write(insert, buf);
          FfiConverterBoolean.INSTANCE.write(isMove, buf);
        }
        case ListDiffItem.Delete(var delete) -> {
          buf.putInt(2);
          FfiConverterInteger.INSTANCE.write(delete, buf);
        }
        case ListDiffItem.Retain(var retain) -> {
          buf.putInt(3);
          FfiConverterInteger.INSTANCE.write(retain, buf);
        }
      };
    }
}




