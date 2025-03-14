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
public class UniffiCallbackInterfaceContainerIdLike {
    public static final UniffiCallbackInterfaceContainerIdLike INSTANCE = new UniffiCallbackInterfaceContainerIdLike();
    UniffiVTableCallbackInterfaceContainerIdLike.UniffiByValue vtable;
    
    UniffiCallbackInterfaceContainerIdLike() {
        vtable = new UniffiVTableCallbackInterfaceContainerIdLike.UniffiByValue(
            asContainerId.INSTANCE,
            UniffiFree.INSTANCE
        );
    }
    
    // Registers the foreign callback with the Rust side.
    // This method is generated for each callback interface.
    void register(UniffiLib lib) {
        lib.uniffi_loro_fn_init_callback_vtable_containeridlike(vtable);
    }
    
    public static class asContainerId implements UniffiCallbackInterfaceContainerIdLikeMethod0 {
        public static final asContainerId INSTANCE = new asContainerId();
        private asContainerId() {}

        @Override
        public void callback(long uniffiHandle,RustBuffer.ByValue ty,RustBuffer uniffiOutReturn,UniffiRustCallStatus uniffiCallStatus) {
            var uniffiObj = FfiConverterTypeContainerIdLike.INSTANCE.handleMap.get(uniffiHandle);
            Supplier<ContainerId> makeCall = () -> {
                return uniffiObj.asContainerId(
                    FfiConverterTypeContainerType.INSTANCE.lift(ty)
                );
                
            };
            Consumer<ContainerId> writeReturn = (ContainerId value) -> { uniffiOutReturn.setValue(FfiConverterTypeContainerID.INSTANCE.lower(value)); };
            UniffiHelpers.uniffiTraitInterfaceCall(uniffiCallStatus, makeCall, writeReturn);
        }
    }

    public static class UniffiFree implements UniffiCallbackInterfaceFree {
        public static final UniffiFree INSTANCE = new UniffiFree();

        private UniffiFree() {}

        @Override
        public void callback(long handle) {
            FfiConverterTypeContainerIdLike.INSTANCE.handleMap.remove(handle);
        }
    }
}

