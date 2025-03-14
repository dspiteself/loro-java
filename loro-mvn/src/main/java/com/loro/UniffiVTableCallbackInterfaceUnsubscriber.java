package com.loro;


import com.sun.jna.Structure;
import com.sun.jna.Pointer;

@Structure.FieldOrder({ "onUnsubscribe", "uniffiFree" })
public class UniffiVTableCallbackInterfaceUnsubscriber extends Structure {
    public UniffiCallbackInterfaceUnsubscriberMethod0 onUnsubscribe = null;
    public UniffiCallbackInterfaceFree uniffiFree = null;

    // no-arg constructor required so JNA can instantiate and reflect
    public UniffiVTableCallbackInterfaceUnsubscriber() {
        super();
    }
    
    public UniffiVTableCallbackInterfaceUnsubscriber(
        UniffiCallbackInterfaceUnsubscriberMethod0 onUnsubscribe,
        UniffiCallbackInterfaceFree uniffiFree
    ) {
        this.onUnsubscribe = onUnsubscribe;
        this.uniffiFree = uniffiFree;
    }

    public static class UniffiByValue extends UniffiVTableCallbackInterfaceUnsubscriber implements Structure.ByValue {
        public UniffiByValue(
            UniffiCallbackInterfaceUnsubscriberMethod0 onUnsubscribe,
            UniffiCallbackInterfaceFree uniffiFree
        ) {
            super(onUnsubscribe,        
            uniffiFree        
            );
        }
    }

    void uniffiSetValue(UniffiVTableCallbackInterfaceUnsubscriber other) {
        onUnsubscribe = other.onUnsubscribe;
        uniffiFree = other.uniffiFree;
    }

}
