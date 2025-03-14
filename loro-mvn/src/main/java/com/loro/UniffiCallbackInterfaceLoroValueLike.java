package com.loro;


import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Callable;
import java.util.function.Function;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.List;
import com.sun.jna.*;
import com.sun.jna.ptr.*;

// Put the implementation in an object so we don't pollute the top-level namespace
public class UniffiCallbackInterfaceLoroValueLike {
    public static final UniffiCallbackInterfaceLoroValueLike INSTANCE = new UniffiCallbackInterfaceLoroValueLike();
    UniffiVTableCallbackInterfaceLoroValueLike.UniffiByValue vtable;
    
    UniffiCallbackInterfaceLoroValueLike() {
        vtable = new UniffiVTableCallbackInterfaceLoroValueLike.UniffiByValue(
            asLoroValue.INSTANCE,
            UniffiFree.INSTANCE
        );
    }
    
    // Registers the foreign callback with the Rust side.
    // This method is generated for each callback interface.
    void register(UniffiLib lib) {
        lib.uniffi_loro_fn_init_callback_vtable_lorovaluelike(vtable);
    }
    
    public static class asLoroValue implements UniffiCallbackInterfaceLoroValueLikeMethod0 {
        public static final asLoroValue INSTANCE = new asLoroValue();
        private asLoroValue() {}

        @Override
        public void callback(long uniffiHandle,RustBuffer uniffiOutReturn,UniffiRustCallStatus uniffiCallStatus) {
            var uniffiObj = FfiConverterTypeLoroValueLike.INSTANCE.handleMap.get(uniffiHandle);
            Supplier<LoroValue> makeCall = () -> {
                return uniffiObj.asLoroValue(
                );
                
            };
            Consumer<LoroValue> writeReturn = (LoroValue value) -> { uniffiOutReturn.setValue(FfiConverterTypeLoroValue.INSTANCE.lower(value)); };
            UniffiHelpers.uniffiTraitInterfaceCall(uniffiCallStatus, makeCall, writeReturn);
        }
    }

    public static class UniffiFree implements UniffiCallbackInterfaceFree {
        public static final UniffiFree INSTANCE = new UniffiFree();

        private UniffiFree() {}

        @Override
        public void callback(long handle) {
            FfiConverterTypeLoroValueLike.INSTANCE.handleMap.remove(handle);
        }
    }
}

