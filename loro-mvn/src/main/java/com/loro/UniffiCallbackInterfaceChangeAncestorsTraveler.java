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
public class UniffiCallbackInterfaceChangeAncestorsTraveler {
    public static final UniffiCallbackInterfaceChangeAncestorsTraveler INSTANCE = new UniffiCallbackInterfaceChangeAncestorsTraveler();
    UniffiVTableCallbackInterfaceChangeAncestorsTraveler.UniffiByValue vtable;
    
    UniffiCallbackInterfaceChangeAncestorsTraveler() {
        vtable = new UniffiVTableCallbackInterfaceChangeAncestorsTraveler.UniffiByValue(
            travel.INSTANCE,
            UniffiFree.INSTANCE
        );
    }
    
    // Registers the foreign callback with the Rust side.
    // This method is generated for each callback interface.
    void register(UniffiLib lib) {
        lib.uniffi_loro_fn_init_callback_vtable_changeancestorstraveler(vtable);
    }
    
    public static class travel implements UniffiCallbackInterfaceChangeAncestorsTravelerMethod0 {
        public static final travel INSTANCE = new travel();
        private travel() {}

        @Override
        public void callback(long uniffiHandle,RustBuffer.ByValue change,ByteByReference uniffiOutReturn,UniffiRustCallStatus uniffiCallStatus) {
            var uniffiObj = FfiConverterTypeChangeAncestorsTraveler.INSTANCE.handleMap.get(uniffiHandle);
            Supplier<Boolean> makeCall = () -> {
                return uniffiObj.travel(
                    FfiConverterTypeChangeMeta.INSTANCE.lift(change)
                );
                
            };
            Consumer<Boolean> writeReturn = (Boolean value) -> { uniffiOutReturn.setValue(FfiConverterBoolean.INSTANCE.lower(value)); };
            UniffiHelpers.uniffiTraitInterfaceCall(uniffiCallStatus, makeCall, writeReturn);
        }
    }

    public static class UniffiFree implements UniffiCallbackInterfaceFree {
        public static final UniffiFree INSTANCE = new UniffiFree();

        private UniffiFree() {}

        @Override
        public void callback(long handle) {
            FfiConverterTypeChangeAncestorsTraveler.INSTANCE.handleMap.remove(handle);
        }
    }
}

