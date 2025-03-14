package com.loro;


import java.nio.ByteBuffer;

public enum FfiConverterTypeImportStatus implements FfiConverterRustBuffer<ImportStatus> {
  INSTANCE;

  @Override
  public ImportStatus read(ByteBuffer buf) {
    return new ImportStatus(
      FfiConverterMapLongTypeCounterSpan.INSTANCE.read(buf),
      FfiConverterOptionalMapLongTypeCounterSpan.INSTANCE.read(buf)
    );
  }

  @Override
  public long allocationSize(ImportStatus value) {
      return (
            FfiConverterMapLongTypeCounterSpan.INSTANCE.allocationSize(value.success()) +
            FfiConverterOptionalMapLongTypeCounterSpan.INSTANCE.allocationSize(value.pending())
      );
  }

  @Override
  public void write(ImportStatus value, ByteBuffer buf) {
      FfiConverterMapLongTypeCounterSpan.INSTANCE.write(value.success(), buf);
      FfiConverterOptionalMapLongTypeCounterSpan.INSTANCE.write(value.pending(), buf);
  }
}



