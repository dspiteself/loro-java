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
public class UniffiCallbackInterfaceOnPush {
    public static final UniffiCallbackInterfaceOnPush INSTANCE = new UniffiCallbackInterfaceOnPush();
    UniffiVTableCallbackInterfaceOnPush.UniffiByValue vtable;
    
    UniffiCallbackInterfaceOnPush() {
        vtable = new UniffiVTableCallbackInterfaceOnPush.UniffiByValue(
            onPush.INSTANCE,
            UniffiFree.INSTANCE
        );
    }
    
    // Registers the foreign callback with the Rust side.
    // This method is generated for each callback interface.
    void register(UniffiLib lib) {
        lib.uniffi_loro_fn_init_callback_vtable_onpush(vtable);
    }
    
    public static class onPush implements UniffiCallbackInterfaceOnPushMethod0 {
        public static final onPush INSTANCE = new onPush();
        private onPush() {}

        @Override
        public void callback(long uniffiHandle,RustBuffer.ByValue undoOrRedo,RustBuffer.ByValue span,RustBuffer.ByValue diffEvent,RustBuffer uniffiOutReturn,UniffiRustCallStatus uniffiCallStatus) {
            var uniffiObj = FfiConverterTypeOnPush.INSTANCE.handleMap.get(uniffiHandle);
            Supplier<UndoItemMeta> makeCall = () -> {
                return uniffiObj.onPush(
                    FfiConverterTypeUndoOrRedo.INSTANCE.lift(undoOrRedo),
                    FfiConverterTypeCounterSpan.INSTANCE.lift(span),
                    FfiConverterOptionalTypeDiffEvent.INSTANCE.lift(diffEvent)
                );
                
            };
            Consumer<UndoItemMeta> writeReturn = (UndoItemMeta value) -> { uniffiOutReturn.setValue(FfiConverterTypeUndoItemMeta.INSTANCE.lower(value)); };
            UniffiHelpers.uniffiTraitInterfaceCall(uniffiCallStatus, makeCall, writeReturn);
        }
    }

    public static class UniffiFree implements UniffiCallbackInterfaceFree {
        public static final UniffiFree INSTANCE = new UniffiFree();

        private UniffiFree() {}

        @Override
        public void callback(long handle) {
            FfiConverterTypeOnPush.INSTANCE.handleMap.remove(handle);
        }
    }
}

