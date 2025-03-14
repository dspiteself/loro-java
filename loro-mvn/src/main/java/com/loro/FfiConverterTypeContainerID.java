package com.loro;


import java.nio.ByteBuffer;

public enum FfiConverterTypeContainerID implements FfiConverterRustBuffer<ContainerId> {
    INSTANCE;

    @Override
    public ContainerId read(ByteBuffer buf) {
      return switch (buf.getInt()) {
        case 1 -> new ContainerId.Root(FfiConverterString.INSTANCE.read(buf),
          FfiConverterTypeContainerType.INSTANCE.read(buf)
          );
        case 2 -> new ContainerId.Normal(FfiConverterLong.INSTANCE.read(buf),
          FfiConverterInteger.INSTANCE.read(buf),
          FfiConverterTypeContainerType.INSTANCE.read(buf)
          );
        default ->
          throw new RuntimeException("invalid enum value, something is very wrong!");
      };
    }

    @Override
    public long allocationSize(ContainerId value) {
        return switch (value) {
          case ContainerId.Root(var name, var containerType) ->
            (4L
            + FfiConverterString.INSTANCE.allocationSize(name)
            + FfiConverterTypeContainerType.INSTANCE.allocationSize(containerType));
          case ContainerId.Normal(var peer, var counter, var containerType) ->
            (4L
            + FfiConverterLong.INSTANCE.allocationSize(peer)
            + FfiConverterInteger.INSTANCE.allocationSize(counter)
            + FfiConverterTypeContainerType.INSTANCE.allocationSize(containerType));
        };
    }

    @Override
    public void write(ContainerId value, ByteBuffer buf) {
      switch (value) {
        case ContainerId.Root(var name, var containerType) -> {
          buf.putInt(1);
          FfiConverterString.INSTANCE.write(name, buf);
          FfiConverterTypeContainerType.INSTANCE.write(containerType, buf);
        }
        case ContainerId.Normal(var peer, var counter, var containerType) -> {
          buf.putInt(2);
          FfiConverterLong.INSTANCE.write(peer, buf);
          FfiConverterInteger.INSTANCE.write(counter, buf);
          FfiConverterTypeContainerType.INSTANCE.write(containerType, buf);
        }
      };
    }
}




