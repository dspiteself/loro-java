package com.loro;


import java.nio.ByteBuffer;

public enum FfiConverterTypeDiffEvent implements FfiConverterRustBuffer<DiffEvent> {
  INSTANCE;

  @Override
  public DiffEvent read(ByteBuffer buf) {
    return new DiffEvent(
      FfiConverterTypeEventTriggerKind.INSTANCE.read(buf),
      FfiConverterString.INSTANCE.read(buf),
      FfiConverterOptionalTypeContainerID.INSTANCE.read(buf),
      FfiConverterSequenceTypeContainerDiff.INSTANCE.read(buf)
    );
  }

  @Override
  public long allocationSize(DiffEvent value) {
      return (
            FfiConverterTypeEventTriggerKind.INSTANCE.allocationSize(value.triggeredBy()) +
            FfiConverterString.INSTANCE.allocationSize(value.origin()) +
            FfiConverterOptionalTypeContainerID.INSTANCE.allocationSize(value.currentTarget()) +
            FfiConverterSequenceTypeContainerDiff.INSTANCE.allocationSize(value.events())
      );
  }

  @Override
  public void write(DiffEvent value, ByteBuffer buf) {
      FfiConverterTypeEventTriggerKind.INSTANCE.write(value.triggeredBy(), buf);
      FfiConverterString.INSTANCE.write(value.origin(), buf);
      FfiConverterOptionalTypeContainerID.INSTANCE.write(value.currentTarget(), buf);
      FfiConverterSequenceTypeContainerDiff.INSTANCE.write(value.events(), buf);
  }
}



