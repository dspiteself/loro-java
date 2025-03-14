package com.loro;


import com.sun.jna.Structure;
import com.sun.jna.Pointer;

@Structure.FieldOrder({ "onPop", "uniffiFree" })
public class UniffiVTableCallbackInterfaceOnPop extends Structure {
    public UniffiCallbackInterfaceOnPopMethod0 onPop = null;
    public UniffiCallbackInterfaceFree uniffiFree = null;

    // no-arg constructor required so JNA can instantiate and reflect
    public UniffiVTableCallbackInterfaceOnPop() {
        super();
    }
    
    public UniffiVTableCallbackInterfaceOnPop(
        UniffiCallbackInterfaceOnPopMethod0 onPop,
        UniffiCallbackInterfaceFree uniffiFree
    ) {
        this.onPop = onPop;
        this.uniffiFree = uniffiFree;
    }

    public static class UniffiByValue extends UniffiVTableCallbackInterfaceOnPop implements Structure.ByValue {
        public UniffiByValue(
            UniffiCallbackInterfaceOnPopMethod0 onPop,
            UniffiCallbackInterfaceFree uniffiFree
        ) {
            super(onPop,        
            uniffiFree        
            );
        }
    }

    void uniffiSetValue(UniffiVTableCallbackInterfaceOnPop other) {
        onPop = other.onPop;
        uniffiFree = other.uniffiFree;
    }

}
