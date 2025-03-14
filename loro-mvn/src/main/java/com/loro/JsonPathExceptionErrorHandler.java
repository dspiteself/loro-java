package com.loro;


public class JsonPathExceptionErrorHandler implements UniffiRustCallStatusErrorHandler<JsonPathException> {
  @Override
  public JsonPathException lift(RustBuffer.ByValue errorBuf){
     return FfiConverterTypeJsonPathError.INSTANCE.lift(errorBuf);
  }
}

