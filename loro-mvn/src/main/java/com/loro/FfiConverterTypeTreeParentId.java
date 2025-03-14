package com.loro;


import java.nio.ByteBuffer;

public enum FfiConverterTypeTreeParentId implements FfiConverterRustBuffer<TreeParentId> {
    INSTANCE;

    @Override
    public TreeParentId read(ByteBuffer buf) {
      return switch (buf.getInt()) {
        case 1 -> new TreeParentId.Node(FfiConverterTypeTreeID.INSTANCE.read(buf)
          );
        case 2 -> new TreeParentId.Root();
        case 3 -> new TreeParentId.Deleted();
        case 4 -> new TreeParentId.Unexist();
        default ->
          throw new RuntimeException("invalid enum value, something is very wrong!");
      };
    }

    @Override
    public long allocationSize(TreeParentId value) {
        return switch (value) {
          case TreeParentId.Node(var id) ->
            (4L
            + FfiConverterTypeTreeID.INSTANCE.allocationSize(id));
          case TreeParentId.Root() ->
            (4L);
          case TreeParentId.Deleted() ->
            (4L);
          case TreeParentId.Unexist() ->
            (4L);
        };
    }

    @Override
    public void write(TreeParentId value, ByteBuffer buf) {
      switch (value) {
        case TreeParentId.Node(var id) -> {
          buf.putInt(1);
          FfiConverterTypeTreeID.INSTANCE.write(id, buf);
        }
        case TreeParentId.Root() -> {
          buf.putInt(2);
        }
        case TreeParentId.Deleted() -> {
          buf.putInt(3);
        }
        case TreeParentId.Unexist() -> {
          buf.putInt(4);
        }
      };
    }
}




