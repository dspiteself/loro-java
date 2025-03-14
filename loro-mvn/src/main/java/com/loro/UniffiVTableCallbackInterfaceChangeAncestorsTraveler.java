package com.loro;


import com.sun.jna.Structure;
import com.sun.jna.Pointer;

@Structure.FieldOrder({ "travel", "uniffiFree" })
public class UniffiVTableCallbackInterfaceChangeAncestorsTraveler extends Structure {
    public UniffiCallbackInterfaceChangeAncestorsTravelerMethod0 travel = null;
    public UniffiCallbackInterfaceFree uniffiFree = null;

    // no-arg constructor required so JNA can instantiate and reflect
    public UniffiVTableCallbackInterfaceChangeAncestorsTraveler() {
        super();
    }
    
    public UniffiVTableCallbackInterfaceChangeAncestorsTraveler(
        UniffiCallbackInterfaceChangeAncestorsTravelerMethod0 travel,
        UniffiCallbackInterfaceFree uniffiFree
    ) {
        this.travel = travel;
        this.uniffiFree = uniffiFree;
    }

    public static class UniffiByValue extends UniffiVTableCallbackInterfaceChangeAncestorsTraveler implements Structure.ByValue {
        public UniffiByValue(
            UniffiCallbackInterfaceChangeAncestorsTravelerMethod0 travel,
            UniffiCallbackInterfaceFree uniffiFree
        ) {
            super(travel,        
            uniffiFree        
            );
        }
    }

    void uniffiSetValue(UniffiVTableCallbackInterfaceChangeAncestorsTraveler other) {
        travel = other.travel;
        uniffiFree = other.uniffiFree;
    }

}
