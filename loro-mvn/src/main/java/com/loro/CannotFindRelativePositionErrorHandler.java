package com.loro;


public class CannotFindRelativePositionErrorHandler implements UniffiRustCallStatusErrorHandler<CannotFindRelativePosition> {
  @Override
  public CannotFindRelativePosition lift(RustBuffer.ByValue errorBuf){
     return FfiConverterTypeCannotFindRelativePosition.INSTANCE.lift(errorBuf);
  }
}

