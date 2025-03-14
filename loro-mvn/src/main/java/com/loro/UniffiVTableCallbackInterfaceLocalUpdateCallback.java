package com.loro;


import com.sun.jna.Structure;
import com.sun.jna.Pointer;

@Structure.FieldOrder({ "onLocalUpdate", "uniffiFree" })
public class UniffiVTableCallbackInterfaceLocalUpdateCallback extends Structure {
    public UniffiCallbackInterfaceLocalUpdateCallbackMethod0 onLocalUpdate = null;
    public UniffiCallbackInterfaceFree uniffiFree = null;

    // no-arg constructor required so JNA can instantiate and reflect
    public UniffiVTableCallbackInterfaceLocalUpdateCallback() {
        super();
    }
    
    public UniffiVTableCallbackInterfaceLocalUpdateCallback(
        UniffiCallbackInterfaceLocalUpdateCallbackMethod0 onLocalUpdate,
        UniffiCallbackInterfaceFree uniffiFree
    ) {
        this.onLocalUpdate = onLocalUpdate;
        this.uniffiFree = uniffiFree;
    }

    public static class UniffiByValue extends UniffiVTableCallbackInterfaceLocalUpdateCallback implements Structure.ByValue {
        public UniffiByValue(
            UniffiCallbackInterfaceLocalUpdateCallbackMethod0 onLocalUpdate,
            UniffiCallbackInterfaceFree uniffiFree
        ) {
            super(onLocalUpdate,        
            uniffiFree        
            );
        }
    }

    void uniffiSetValue(UniffiVTableCallbackInterfaceLocalUpdateCallback other) {
        onLocalUpdate = other.onLocalUpdate;
        uniffiFree = other.uniffiFree;
    }

}
