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
public class UniffiCallbackInterfaceLocalUpdateCallback {
    public static final UniffiCallbackInterfaceLocalUpdateCallback INSTANCE = new UniffiCallbackInterfaceLocalUpdateCallback();
    UniffiVTableCallbackInterfaceLocalUpdateCallback.UniffiByValue vtable;
    
    UniffiCallbackInterfaceLocalUpdateCallback() {
        vtable = new UniffiVTableCallbackInterfaceLocalUpdateCallback.UniffiByValue(
            onLocalUpdate.INSTANCE,
            UniffiFree.INSTANCE
        );
    }
    
    // Registers the foreign callback with the Rust side.
    // This method is generated for each callback interface.
    void register(UniffiLib lib) {
        lib.uniffi_loro_fn_init_callback_vtable_localupdatecallback(vtable);
    }
    
    public static class onLocalUpdate implements UniffiCallbackInterfaceLocalUpdateCallbackMethod0 {
        public static final onLocalUpdate INSTANCE = new onLocalUpdate();
        private onLocalUpdate() {}

        @Override
        public void callback(long uniffiHandle,RustBuffer.ByValue update,Pointer uniffiOutReturn,UniffiRustCallStatus uniffiCallStatus) {
            var uniffiObj = FfiConverterTypeLocalUpdateCallback.INSTANCE.handleMap.get(uniffiHandle);
            Supplier<Void> makeCall = () -> {
                uniffiObj.onLocalUpdate(
                    FfiConverterByteArray.INSTANCE.lift(update)
                );
                return null;
            };
            Consumer<Void> writeReturn = (nothing) -> {};
            UniffiHelpers.uniffiTraitInterfaceCall(uniffiCallStatus, makeCall, writeReturn);
        }
    }

    public static class UniffiFree implements UniffiCallbackInterfaceFree {
        public static final UniffiFree INSTANCE = new UniffiFree();

        private UniffiFree() {}

        @Override
        public void callback(long handle) {
            FfiConverterTypeLocalUpdateCallback.INSTANCE.handleMap.remove(handle);
        }
    }
}

