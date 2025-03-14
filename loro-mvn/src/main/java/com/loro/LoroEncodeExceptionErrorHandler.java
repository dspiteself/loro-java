package com.loro;


public class LoroEncodeExceptionErrorHandler implements UniffiRustCallStatusErrorHandler<LoroEncodeException> {
  @Override
  public LoroEncodeException lift(RustBuffer.ByValue errorBuf){
     return FfiConverterTypeLoroEncodeError.INSTANCE.lift(errorBuf);
  }
}

