package com.loro;


import java.nio.ByteBuffer;

public enum FfiConverterTypeTextDelta implements FfiConverterRustBuffer<TextDelta> {
    INSTANCE;

    @Override
    public TextDelta read(ByteBuffer buf) {
      return switch (buf.getInt()) {
        case 1 -> new TextDelta.Retain(FfiConverterInteger.INSTANCE.read(buf),
          FfiConverterOptionalMapStringTypeLoroValue.INSTANCE.read(buf)
          );
        case 2 -> new TextDelta.Insert(FfiConverterString.INSTANCE.read(buf),
          FfiConverterOptionalMapStringTypeLoroValue.INSTANCE.read(buf)
          );
        case 3 -> new TextDelta.Delete(FfiConverterInteger.INSTANCE.read(buf)
          );
        default ->
          throw new RuntimeException("invalid enum value, something is very wrong!");
      };
    }

    @Override
    public long allocationSize(TextDelta value) {
        return switch (value) {
          case TextDelta.Retain(var retain, var attributes) ->
            (4L
            + FfiConverterInteger.INSTANCE.allocationSize(retain)
            + FfiConverterOptionalMapStringTypeLoroValue.INSTANCE.allocationSize(attributes));
          case TextDelta.Insert(var insert, var attributes) ->
            (4L
            + FfiConverterString.INSTANCE.allocationSize(insert)
            + FfiConverterOptionalMapStringTypeLoroValue.INSTANCE.allocationSize(attributes));
          case TextDelta.Delete(var delete) ->
            (4L
            + FfiConverterInteger.INSTANCE.allocationSize(delete));
        };
    }

    @Override
    public void write(TextDelta value, ByteBuffer buf) {
      switch (value) {
        case TextDelta.Retain(var retain, var attributes) -> {
          buf.putInt(1);
          FfiConverterInteger.INSTANCE.write(retain, buf);
          FfiConverterOptionalMapStringTypeLoroValue.INSTANCE.write(attributes, buf);
        }
        case TextDelta.Insert(var insert, var attributes) -> {
          buf.putInt(2);
          FfiConverterString.INSTANCE.write(insert, buf);
          FfiConverterOptionalMapStringTypeLoroValue.INSTANCE.write(attributes, buf);
        }
        case TextDelta.Delete(var delete) -> {
          buf.putInt(3);
          FfiConverterInteger.INSTANCE.write(delete, buf);
        }
      };
    }
}




