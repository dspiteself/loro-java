package com.loro;


import com.sun.jna.Structure;
import com.sun.jna.Pointer;

@Structure.FieldOrder({ "onDiff", "uniffiFree" })
public class UniffiVTableCallbackInterfaceSubscriber extends Structure {
    public UniffiCallbackInterfaceSubscriberMethod0 onDiff = null;
    public UniffiCallbackInterfaceFree uniffiFree = null;

    // no-arg constructor required so JNA can instantiate and reflect
    public UniffiVTableCallbackInterfaceSubscriber() {
        super();
    }
    
    public UniffiVTableCallbackInterfaceSubscriber(
        UniffiCallbackInterfaceSubscriberMethod0 onDiff,
        UniffiCallbackInterfaceFree uniffiFree
    ) {
        this.onDiff = onDiff;
        this.uniffiFree = uniffiFree;
    }

    public static class UniffiByValue extends UniffiVTableCallbackInterfaceSubscriber implements Structure.ByValue {
        public UniffiByValue(
            UniffiCallbackInterfaceSubscriberMethod0 onDiff,
            UniffiCallbackInterfaceFree uniffiFree
        ) {
            super(onDiff,        
            uniffiFree        
            );
        }
    }

    void uniffiSetValue(UniffiVTableCallbackInterfaceSubscriber other) {
        onDiff = other.onDiff;
        uniffiFree = other.uniffiFree;
    }

}
