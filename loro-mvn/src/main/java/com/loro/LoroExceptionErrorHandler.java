package com.loro;


public class LoroExceptionErrorHandler implements UniffiRustCallStatusErrorHandler<LoroException> {
  @Override
  public LoroException lift(RustBuffer.ByValue errorBuf){
     return FfiConverterTypeLoroError.INSTANCE.lift(errorBuf);
  }
}

