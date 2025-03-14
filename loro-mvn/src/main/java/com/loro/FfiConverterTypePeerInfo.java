package com.loro;


import java.nio.ByteBuffer;

public enum FfiConverterTypePeerInfo implements FfiConverterRustBuffer<PeerInfo> {
  INSTANCE;

  @Override
  public PeerInfo read(ByteBuffer buf) {
    return new PeerInfo(
      FfiConverterTypeLoroValue.INSTANCE.read(buf),
      FfiConverterInteger.INSTANCE.read(buf),
      FfiConverterLong.INSTANCE.read(buf)
    );
  }

  @Override
  public long allocationSize(PeerInfo value) {
      return (
            FfiConverterTypeLoroValue.INSTANCE.allocationSize(value.state()) +
            FfiConverterInteger.INSTANCE.allocationSize(value.counter()) +
            FfiConverterLong.INSTANCE.allocationSize(value.timestamp())
      );
  }

  @Override
  public void write(PeerInfo value, ByteBuffer buf) {
      FfiConverterTypeLoroValue.INSTANCE.write(value.state(), buf);
      FfiConverterInteger.INSTANCE.write(value.counter(), buf);
      FfiConverterLong.INSTANCE.write(value.timestamp(), buf);
  }
}



