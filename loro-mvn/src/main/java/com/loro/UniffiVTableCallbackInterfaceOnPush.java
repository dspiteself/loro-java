package com.loro;


import com.sun.jna.Structure;
import com.sun.jna.Pointer;

@Structure.FieldOrder({ "onPush", "uniffiFree" })
public class UniffiVTableCallbackInterfaceOnPush extends Structure {
    public UniffiCallbackInterfaceOnPushMethod0 onPush = null;
    public UniffiCallbackInterfaceFree uniffiFree = null;

    // no-arg constructor required so JNA can instantiate and reflect
    public UniffiVTableCallbackInterfaceOnPush() {
        super();
    }
    
    public UniffiVTableCallbackInterfaceOnPush(
        UniffiCallbackInterfaceOnPushMethod0 onPush,
        UniffiCallbackInterfaceFree uniffiFree
    ) {
        this.onPush = onPush;
        this.uniffiFree = uniffiFree;
    }

    public static class UniffiByValue extends UniffiVTableCallbackInterfaceOnPush implements Structure.ByValue {
        public UniffiByValue(
            UniffiCallbackInterfaceOnPushMethod0 onPush,
            UniffiCallbackInterfaceFree uniffiFree
        ) {
            super(onPush,        
            uniffiFree        
            );
        }
    }

    void uniffiSetValue(UniffiVTableCallbackInterfaceOnPush other) {
        onPush = other.onPush;
        uniffiFree = other.uniffiFree;
    }

}
