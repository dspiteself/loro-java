package com.loro;


import java.nio.ByteBuffer;

public enum FfiConverterTypeLoroValue implements FfiConverterRustBuffer<LoroValue> {
    INSTANCE;

    @Override
    public LoroValue read(ByteBuffer buf) {
      return switch (buf.getInt()) {
        case 1 -> new LoroValue.Null();
        case 2 -> new LoroValue.Bool(FfiConverterBoolean.INSTANCE.read(buf)
          );
        case 3 -> new LoroValue.Double(FfiConverterDouble.INSTANCE.read(buf)
          );
        case 4 -> new LoroValue.I64(FfiConverterLong.INSTANCE.read(buf)
          );
        case 5 -> new LoroValue.Binary(FfiConverterByteArray.INSTANCE.read(buf)
          );
        case 6 -> new LoroValue.String(FfiConverterString.INSTANCE.read(buf)
          );
        case 7 -> new LoroValue.List(FfiConverterSequenceTypeLoroValue.INSTANCE.read(buf)
          );
        case 8 -> new LoroValue.Map(FfiConverterMapStringTypeLoroValue.INSTANCE.read(buf)
          );
        case 9 -> new LoroValue.Container(FfiConverterTypeContainerID.INSTANCE.read(buf)
          );
        default ->
          throw new RuntimeException("invalid enum value, something is very wrong!");
      };
    }

    @Override
    public long allocationSize(LoroValue x) {
        return switch (x) {
          case LoroValue.Null() ->
            (4L);
          case LoroValue.Bool(var value) ->
            (4L
            + FfiConverterBoolean.INSTANCE.allocationSize(value));
          case LoroValue.Double(var value) ->
            (4L
            + FfiConverterDouble.INSTANCE.allocationSize(value));
          case LoroValue.I64(var value) ->
            (4L
            + FfiConverterLong.INSTANCE.allocationSize(value));
          case LoroValue.Binary(var value) ->
            (4L
            + FfiConverterByteArray.INSTANCE.allocationSize(value));
          case LoroValue.String(var value) ->
            (4L
            + FfiConverterString.INSTANCE.allocationSize(value));
          case LoroValue.List(var value) ->
            (4L
            + FfiConverterSequenceTypeLoroValue.INSTANCE.allocationSize(value));
          case LoroValue.Map(var value) ->
            (4L
            + FfiConverterMapStringTypeLoroValue.INSTANCE.allocationSize(value));
          case LoroValue.Container(var value) ->
            (4L
            + FfiConverterTypeContainerID.INSTANCE.allocationSize(value));
        };
    }

    @Override
    public void write(LoroValue x, ByteBuffer buf) {
      switch (x) {
        case LoroValue.Null() -> {
          buf.putInt(1);
        }
        case LoroValue.Bool(var value) -> {
          buf.putInt(2);
          FfiConverterBoolean.INSTANCE.write(value, buf);
        }
        case LoroValue.Double(var value) -> {
          buf.putInt(3);
          FfiConverterDouble.INSTANCE.write(value, buf);
        }
        case LoroValue.I64(var value) -> {
          buf.putInt(4);
          FfiConverterLong.INSTANCE.write(value, buf);
        }
        case LoroValue.Binary(var value) -> {
          buf.putInt(5);
          FfiConverterByteArray.INSTANCE.write(value, buf);
        }
        case LoroValue.String(var value) -> {
          buf.putInt(6);
          FfiConverterString.INSTANCE.write(value, buf);
        }
        case LoroValue.List(var value) -> {
          buf.putInt(7);
          FfiConverterSequenceTypeLoroValue.INSTANCE.write(value, buf);
        }
        case LoroValue.Map(var value) -> {
          buf.putInt(8);
          FfiConverterMapStringTypeLoroValue.INSTANCE.write(value, buf);
        }
        case LoroValue.Container(var value) -> {
          buf.putInt(9);
          FfiConverterTypeContainerID.INSTANCE.write(value, buf);
        }
      };
    }
}




