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
public class UniffiCallbackInterfaceOnPop {
    public static final UniffiCallbackInterfaceOnPop INSTANCE = new UniffiCallbackInterfaceOnPop();
    UniffiVTableCallbackInterfaceOnPop.UniffiByValue vtable;
    
    UniffiCallbackInterfaceOnPop() {
        vtable = new UniffiVTableCallbackInterfaceOnPop.UniffiByValue(
            onPop.INSTANCE,
            UniffiFree.INSTANCE
        );
    }
    
    // Registers the foreign callback with the Rust side.
    // This method is generated for each callback interface.
    void register(UniffiLib lib) {
        lib.uniffi_loro_fn_init_callback_vtable_onpop(vtable);
    }
    
    public static class onPop implements UniffiCallbackInterfaceOnPopMethod0 {
        public static final onPop INSTANCE = new onPop();
        private onPop() {}

        @Override
        public void callback(long uniffiHandle,RustBuffer.ByValue undoOrRedo,RustBuffer.ByValue span,RustBuffer.ByValue undoMeta,Pointer uniffiOutReturn,UniffiRustCallStatus uniffiCallStatus) {
            var uniffiObj = FfiConverterTypeOnPop.INSTANCE.handleMap.get(uniffiHandle);
            Supplier<Void> makeCall = () -> {
                uniffiObj.onPop(
                    FfiConverterTypeUndoOrRedo.INSTANCE.lift(undoOrRedo),
                    FfiConverterTypeCounterSpan.INSTANCE.lift(span),
                    FfiConverterTypeUndoItemMeta.INSTANCE.lift(undoMeta)
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
            FfiConverterTypeOnPop.INSTANCE.handleMap.remove(handle);
        }
    }
}

