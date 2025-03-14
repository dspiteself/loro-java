package com.loro;


import java.nio.ByteBuffer;

public enum FfiConverterTypeImportBlobMetadata implements FfiConverterRustBuffer<ImportBlobMetadata> {
  INSTANCE;

  @Override
  public ImportBlobMetadata read(ByteBuffer buf) {
    return new ImportBlobMetadata(
      FfiConverterTypeVersionVector.INSTANCE.read(buf),
      FfiConverterTypeVersionVector.INSTANCE.read(buf),
      FfiConverterLong.INSTANCE.read(buf),
      FfiConverterTypeFrontiers.INSTANCE.read(buf),
      FfiConverterLong.INSTANCE.read(buf),
      FfiConverterInteger.INSTANCE.read(buf),
      FfiConverterString.INSTANCE.read(buf)
    );
  }

  @Override
  public long allocationSize(ImportBlobMetadata value) {
      return (
            FfiConverterTypeVersionVector.INSTANCE.allocationSize(value.partialStartVv()) +
            FfiConverterTypeVersionVector.INSTANCE.allocationSize(value.partialEndVv()) +
            FfiConverterLong.INSTANCE.allocationSize(value.startTimestamp()) +
            FfiConverterTypeFrontiers.INSTANCE.allocationSize(value.startFrontiers()) +
            FfiConverterLong.INSTANCE.allocationSize(value.endTimestamp()) +
            FfiConverterInteger.INSTANCE.allocationSize(value.changeNum()) +
            FfiConverterString.INSTANCE.allocationSize(value.mode())
      );
  }

  @Override
  public void write(ImportBlobMetadata value, ByteBuffer buf) {
      FfiConverterTypeVersionVector.INSTANCE.write(value.partialStartVv(), buf);
      FfiConverterTypeVersionVector.INSTANCE.write(value.partialEndVv(), buf);
      FfiConverterLong.INSTANCE.write(value.startTimestamp(), buf);
      FfiConverterTypeFrontiers.INSTANCE.write(value.startFrontiers(), buf);
      FfiConverterLong.INSTANCE.write(value.endTimestamp(), buf);
      FfiConverterInteger.INSTANCE.write(value.changeNum(), buf);
      FfiConverterString.INSTANCE.write(value.mode(), buf);
  }
}



