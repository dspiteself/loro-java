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
public class UniffiCallbackInterfaceSubscriber {
    public static final UniffiCallbackInterfaceSubscriber INSTANCE = new UniffiCallbackInterfaceSubscriber();
    UniffiVTableCallbackInterfaceSubscriber.UniffiByValue vtable;
    
    UniffiCallbackInterfaceSubscriber() {
        vtable = new UniffiVTableCallbackInterfaceSubscriber.UniffiByValue(
            onDiff.INSTANCE,
            UniffiFree.INSTANCE
        );
    }
    
    // Registers the foreign callback with the Rust side.
    // This method is generated for each callback interface.
    void register(UniffiLib lib) {
        lib.uniffi_loro_fn_init_callback_vtable_subscriber(vtable);
    }
    
    public static class onDiff implements UniffiCallbackInterfaceSubscriberMethod0 {
        public static final onDiff INSTANCE = new onDiff();
        private onDiff() {}

        @Override
        public void callback(long uniffiHandle,RustBuffer.ByValue diff,Pointer uniffiOutReturn,UniffiRustCallStatus uniffiCallStatus) {
            var uniffiObj = FfiConverterTypeSubscriber.INSTANCE.handleMap.get(uniffiHandle);
            Supplier<Void> makeCall = () -> {
                uniffiObj.onDiff(
                    FfiConverterTypeDiffEvent.INSTANCE.lift(diff)
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
            FfiConverterTypeSubscriber.INSTANCE.handleMap.remove(handle);
        }
    }
}

