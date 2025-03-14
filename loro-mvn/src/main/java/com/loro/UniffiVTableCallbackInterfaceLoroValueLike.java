package com.loro;


import com.sun.jna.Structure;
import com.sun.jna.Pointer;

@Structure.FieldOrder({ "asLoroValue", "uniffiFree" })
public class UniffiVTableCallbackInterfaceLoroValueLike extends Structure {
    public UniffiCallbackInterfaceLoroValueLikeMethod0 asLoroValue = null;
    public UniffiCallbackInterfaceFree uniffiFree = null;

    // no-arg constructor required so JNA can instantiate and reflect
    public UniffiVTableCallbackInterfaceLoroValueLike() {
        super();
    }
    
    public UniffiVTableCallbackInterfaceLoroValueLike(
        UniffiCallbackInterfaceLoroValueLikeMethod0 asLoroValue,
        UniffiCallbackInterfaceFree uniffiFree
    ) {
        this.asLoroValue = asLoroValue;
        this.uniffiFree = uniffiFree;
    }

    public static class UniffiByValue extends UniffiVTableCallbackInterfaceLoroValueLike implements Structure.ByValue {
        public UniffiByValue(
            UniffiCallbackInterfaceLoroValueLikeMethod0 asLoroValue,
            UniffiCallbackInterfaceFree uniffiFree
        ) {
            super(asLoroValue,        
            uniffiFree        
            );
        }
    }

    void uniffiSetValue(UniffiVTableCallbackInterfaceLoroValueLike other) {
        asLoroValue = other.asLoroValue;
        uniffiFree = other.uniffiFree;
    }

}
