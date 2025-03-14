package com.loro;


import java.nio.ByteBuffer;
import com.sun.jna.Pointer;

public enum FfiConverterTypeVersionVector implements FfiConverter<VersionVector, Pointer> {
    INSTANCE;

    @Override
    public Pointer lower(VersionVector value) {
        return value.uniffiClonePointer();
    }

    @Override
    public VersionVector lift(Pointer value) {
        return new VersionVector(value);
    }

    @Override
    public VersionVector read(ByteBuffer buf) {
        // The Rust code always writes pointers as 8 bytes, and will
        // fail to compile if they don't fit.
        return lift(new Pointer(buf.getLong()));
    }

    @Override
    public long allocationSize(VersionVector value) {
      return 8L;
    }

    @Override
    public void write(VersionVector value, ByteBuffer buf) {
        // The Rust code always expects pointers written as 8 bytes,
        // and will fail to compile if they don't fit.
        buf.putLong(Pointer.nativeValue(lower(value)));
    }
}



