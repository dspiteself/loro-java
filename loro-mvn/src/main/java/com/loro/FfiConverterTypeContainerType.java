package com.loro;


import java.nio.ByteBuffer;

public enum FfiConverterTypeContainerType implements FfiConverterRustBuffer<ContainerType> {
    INSTANCE;

    @Override
    public ContainerType read(ByteBuffer buf) {
      return switch (buf.getInt()) {
        case 1 -> new ContainerType.Text();
        case 2 -> new ContainerType.Map();
        case 3 -> new ContainerType.List();
        case 4 -> new ContainerType.MovableList();
        case 5 -> new ContainerType.Tree();
        case 6 -> new ContainerType.Counter();
        case 7 -> new ContainerType.Unknown(FfiConverterByte.INSTANCE.read(buf)
          );
        default ->
          throw new RuntimeException("invalid enum value, something is very wrong!");
      };
    }

    @Override
    public long allocationSize(ContainerType value) {
        return switch (value) {
          case ContainerType.Text() ->
            (4L);
          case ContainerType.Map() ->
            (4L);
          case ContainerType.List() ->
            (4L);
          case ContainerType.MovableList() ->
            (4L);
          case ContainerType.Tree() ->
            (4L);
          case ContainerType.Counter() ->
            (4L);
          case ContainerType.Unknown(var kind) ->
            (4L
            + FfiConverterByte.INSTANCE.allocationSize(kind));
        };
    }

    @Override
    public void write(ContainerType value, ByteBuffer buf) {
      switch (value) {
        case ContainerType.Text() -> {
          buf.putInt(1);
        }
        case ContainerType.Map() -> {
          buf.putInt(2);
        }
        case ContainerType.List() -> {
          buf.putInt(3);
        }
        case ContainerType.MovableList() -> {
          buf.putInt(4);
        }
        case ContainerType.Tree() -> {
          buf.putInt(5);
        }
        case ContainerType.Counter() -> {
          buf.putInt(6);
        }
        case ContainerType.Unknown(var kind) -> {
          buf.putInt(7);
          FfiConverterByte.INSTANCE.write(kind, buf);
        }
      };
    }
}




