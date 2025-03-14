package com.loro;


public class ChangeTravelExceptionErrorHandler implements UniffiRustCallStatusErrorHandler<ChangeTravelException> {
  @Override
  public ChangeTravelException lift(RustBuffer.ByValue errorBuf){
     return FfiConverterTypeChangeTravelError.INSTANCE.lift(errorBuf);
  }
}

