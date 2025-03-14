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
public class UniffiCallbackInterfaceUnsubscriber {
    public static final UniffiCallbackInterfaceUnsubscriber INSTANCE = new UniffiCallbackInterfaceUnsubscriber();
    UniffiVTableCallbackInterfaceUnsubscriber.UniffiByValue vtable;
    
    UniffiCallbackInterfaceUnsubscriber() {
        vtable = new UniffiVTableCallbackInterfaceUnsubscriber.UniffiByValue(
            onUnsubscribe.INSTANCE,
            UniffiFree.INSTANCE
        );
    }
    
    // Registers the foreign callback with the Rust side.
    // This method is generated for each callback interface.
    void register(UniffiLib lib) {
        lib.uniffi_loro_fn_init_callback_vtable_unsubscriber(vtable);
    }
    
    public static class onUnsubscribe implements UniffiCallbackInterfaceUnsubscriberMethod0 {
        public static final onUnsubscribe INSTANCE = new onUnsubscribe();
        private onUnsubscribe() {}

        @Override
        public void callback(long uniffiHandle,Pointer uniffiOutReturn,UniffiRustCallStatus uniffiCallStatus) {
            var uniffiObj = FfiConverterTypeUnsubscriber.INSTANCE.handleMap.get(uniffiHandle);
            Supplier<Void> makeCall = () -> {
                uniffiObj.onUnsubscribe(
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
            FfiConverterTypeUnsubscriber.INSTANCE.handleMap.remove(handle);
        }
    }
}

