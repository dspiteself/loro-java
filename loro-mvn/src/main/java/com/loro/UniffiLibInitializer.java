package com.loro;


// Java doesn't allow for static init blocks in an interface outside of a static property with a default.
// To get around that and make sure that when the UniffiLib interface loads it has an initialized library
// we call this class. The init code won't be called until a function on this interface is called unfortunately.
final class UniffiLibInitializer {
    static UniffiLib load() {
        UniffiLib instance = NamespaceLibrary.loadIndirect("loro", UniffiLib.class);
        NamespaceLibrary.uniffiCheckContractApiVersion(instance);
        NamespaceLibrary.uniffiCheckApiChecksums(instance);
        UniffiCallbackInterfaceChangeAncestorsTraveler.INSTANCE.register(instance);
        UniffiCallbackInterfaceContainerIdLike.INSTANCE.register(instance);
        UniffiCallbackInterfaceLocalUpdateCallback.INSTANCE.register(instance);
        UniffiCallbackInterfaceLoroValueLike.INSTANCE.register(instance);
        UniffiCallbackInterfaceOnPop.INSTANCE.register(instance);
        UniffiCallbackInterfaceOnPush.INSTANCE.register(instance);
        UniffiCallbackInterfaceSubscriber.INSTANCE.register(instance);
        UniffiCallbackInterfaceUnsubscriber.INSTANCE.register(instance);
        return instance;
    }
}

// Async support

// Public interface members begin here.


