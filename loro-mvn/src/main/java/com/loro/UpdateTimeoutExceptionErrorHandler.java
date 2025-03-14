package com.loro;


public class UpdateTimeoutExceptionErrorHandler implements UniffiRustCallStatusErrorHandler<UpdateTimeoutException> {
  @Override
  public UpdateTimeoutException lift(RustBuffer.ByValue errorBuf){
     return FfiConverterTypeUpdateTimeoutError.INSTANCE.lift(errorBuf);
  }
}

