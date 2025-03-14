package com.loro;


import java.nio.ByteBuffer;

public enum FfiConverterTypeIndex implements FfiConverterRustBuffer<Index> {
    INSTANCE;

    @Override
    public Index read(ByteBuffer buf) {
      return switch (buf.getInt()) {
        case 1 -> new Index.Key(FfiConverterString.INSTANCE.read(buf)
          );
        case 2 -> new Index.Seq(FfiConverterInteger.INSTANCE.read(buf)
          );
        case 3 -> new Index.Node(FfiConverterTypeTreeID.INSTANCE.read(buf)
          );
        default ->
          throw new RuntimeException("invalid enum value, something is very wrong!");
      };
    }

    @Override
    public long allocationSize(Index value) {
        return switch (value) {
          case Index.Key(var key) ->
            (4L
            + FfiConverterString.INSTANCE.allocationSize(key));
          case Index.Seq(var index) ->
            (4L
            + FfiConverterInteger.INSTANCE.allocationSize(index));
          case Index.Node(var target) ->
            (4L
            + FfiConverterTypeTreeID.INSTANCE.allocationSize(target));
        };
    }

    @Override
    public void write(Index value, ByteBuffer buf) {
      switch (value) {
        case Index.Key(var key) -> {
          buf.putInt(1);
          FfiConverterString.INSTANCE.write(key, buf);
        }
        case Index.Seq(var index) -> {
          buf.putInt(2);
          FfiConverterInteger.INSTANCE.write(index, buf);
        }
        case Index.Node(var target) -> {
          buf.putInt(3);
          FfiConverterTypeTreeID.INSTANCE.write(target, buf);
        }
      };
    }
}




