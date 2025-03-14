package com.loro;


import com.sun.jna.Structure;
import com.sun.jna.Pointer;

@Structure.FieldOrder({ "asContainerId", "uniffiFree" })
public class UniffiVTableCallbackInterfaceContainerIdLike extends Structure {
    public UniffiCallbackInterfaceContainerIdLikeMethod0 asContainerId = null;
    public UniffiCallbackInterfaceFree uniffiFree = null;

    // no-arg constructor required so JNA can instantiate and reflect
    public UniffiVTableCallbackInterfaceContainerIdLike() {
        super();
    }
    
    public UniffiVTableCallbackInterfaceContainerIdLike(
        UniffiCallbackInterfaceContainerIdLikeMethod0 asContainerId,
        UniffiCallbackInterfaceFree uniffiFree
    ) {
        this.asContainerId = asContainerId;
        this.uniffiFree = uniffiFree;
    }

    public static class UniffiByValue extends UniffiVTableCallbackInterfaceContainerIdLike implements Structure.ByValue {
        public UniffiByValue(
            UniffiCallbackInterfaceContainerIdLikeMethod0 asContainerId,
            UniffiCallbackInterfaceFree uniffiFree
        ) {
            super(asContainerId,        
            uniffiFree        
            );
        }
    }

    void uniffiSetValue(UniffiVTableCallbackInterfaceContainerIdLike other) {
        asContainerId = other.asContainerId;
        uniffiFree = other.uniffiFree;
    }

}
