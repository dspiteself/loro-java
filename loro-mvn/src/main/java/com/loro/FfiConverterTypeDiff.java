package com.loro;


import java.nio.ByteBuffer;

public enum FfiConverterTypeDiff implements FfiConverterRustBuffer<Diff> {
    INSTANCE;

    @Override
    public Diff read(ByteBuffer buf) {
      return switch (buf.getInt()) {
        case 1 -> new Diff.List(FfiConverterSequenceTypeListDiffItem.INSTANCE.read(buf)
          );
        case 2 -> new Diff.Text(FfiConverterSequenceTypeTextDelta.INSTANCE.read(buf)
          );
        case 3 -> new Diff.Map(FfiConverterTypeMapDelta.INSTANCE.read(buf)
          );
        case 4 -> new Diff.Tree(FfiConverterTypeTreeDiff.INSTANCE.read(buf)
          );
        case 5 -> new Diff.Counter(FfiConverterDouble.INSTANCE.read(buf)
          );
        case 6 -> new Diff.Unknown();
        default ->
          throw new RuntimeException("invalid enum value, something is very wrong!");
      };
    }

    @Override
    public long allocationSize(Diff value) {
        return switch (value) {
          case Diff.List(var diff) ->
            (4L
            + FfiConverterSequenceTypeListDiffItem.INSTANCE.allocationSize(diff));
          case Diff.Text(var diff) ->
            (4L
            + FfiConverterSequenceTypeTextDelta.INSTANCE.allocationSize(diff));
          case Diff.Map(var diff) ->
            (4L
            + FfiConverterTypeMapDelta.INSTANCE.allocationSize(diff));
          case Diff.Tree(var diff) ->
            (4L
            + FfiConverterTypeTreeDiff.INSTANCE.allocationSize(diff));
          case Diff.Counter(var diff) ->
            (4L
            + FfiConverterDouble.INSTANCE.allocationSize(diff));
          case Diff.Unknown() ->
            (4L);
        };
    }

    @Override
    public void write(Diff value, ByteBuffer buf) {
      switch (value) {
        case Diff.List(var diff) -> {
          buf.putInt(1);
          FfiConverterSequenceTypeListDiffItem.INSTANCE.write(diff, buf);
        }
        case Diff.Text(var diff) -> {
          buf.putInt(2);
          FfiConverterSequenceTypeTextDelta.INSTANCE.write(diff, buf);
        }
        case Diff.Map(var diff) -> {
          buf.putInt(3);
          FfiConverterTypeMapDelta.INSTANCE.write(diff, buf);
        }
        case Diff.Tree(var diff) -> {
          buf.putInt(4);
          FfiConverterTypeTreeDiff.INSTANCE.write(diff, buf);
        }
        case Diff.Counter(var diff) -> {
          buf.putInt(5);
          FfiConverterDouble.INSTANCE.write(diff, buf);
        }
        case Diff.Unknown() -> {
          buf.putInt(6);
        }
      };
    }
}




