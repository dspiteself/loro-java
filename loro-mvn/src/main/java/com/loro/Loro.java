package com.loro;


import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
public class Loro {
  
    /**
     * Decodes the metadata for an imported blob from the provided bytes.
     */public static ImportBlobMetadata decodeImportBlobMeta(byte[] bytes, Boolean checkChecksum) throws LoroException {
            try {
                return FfiConverterTypeImportBlobMetadata.INSTANCE.lift(
    UniffiHelpers.uniffiRustCallWithError(new LoroExceptionErrorHandler(), _status -> {
        return UniffiLib.INSTANCE.uniffi_loro_fn_func_decode_import_blob_meta(
            FfiConverterByteArray.INSTANCE.lower(bytes), FfiConverterBoolean.INSTANCE.lower(checkChecksum), _status);
    })
    );
            } catch (RuntimeException _e) {
                
                if (LoroException.class.isInstance(_e.getCause())) {
                    throw (LoroException)_e.getCause();
                }
                
                if (InternalException.class.isInstance(_e.getCause())) {
                    throw (InternalException)_e.getCause();
                }
                throw _e;
            }
    }
    

  public static String getVersion()  {
            try {
                return FfiConverterString.INSTANCE.lift(
    UniffiHelpers.uniffiRustCall( _status -> {
        return UniffiLib.INSTANCE.uniffi_loro_fn_func_get_version(
            _status);
    })
    );
            } catch (RuntimeException _e) {
                
                
                if (InternalException.class.isInstance(_e.getCause())) {
                    throw (InternalException)_e.getCause();
                }
                throw _e;
            }
    }
    

}

