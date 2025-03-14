package com.loro;


import com.sun.jna.Structure;
import com.sun.jna.Pointer;

@Structure.FieldOrder({ "asContainer", "asLoroCounter", "asLoroList", "asLoroMap", "asLoroMovableList", "asLoroText", "asLoroTree", "asLoroUnknown", "asValue", "containerType", "isContainer", "isValue", "uniffiFree" })
public class UniffiVTableCallbackInterfaceValueOrContainer extends Structure {
    public UniffiCallbackInterfaceValueOrContainerMethod0 asContainer = null;
    public UniffiCallbackInterfaceValueOrContainerMethod1 asLoroCounter = null;
    public UniffiCallbackInterfaceValueOrContainerMethod2 asLoroList = null;
    public UniffiCallbackInterfaceValueOrContainerMethod3 asLoroMap = null;
    public UniffiCallbackInterfaceValueOrContainerMethod4 asLoroMovableList = null;
    public UniffiCallbackInterfaceValueOrContainerMethod5 asLoroText = null;
    public UniffiCallbackInterfaceValueOrContainerMethod6 asLoroTree = null;
    public UniffiCallbackInterfaceValueOrContainerMethod7 asLoroUnknown = null;
    public UniffiCallbackInterfaceValueOrContainerMethod8 asValue = null;
    public UniffiCallbackInterfaceValueOrContainerMethod9 containerType = null;
    public UniffiCallbackInterfaceValueOrContainerMethod10 isContainer = null;
    public UniffiCallbackInterfaceValueOrContainerMethod11 isValue = null;
    public UniffiCallbackInterfaceFree uniffiFree = null;

    // no-arg constructor required so JNA can instantiate and reflect
    public UniffiVTableCallbackInterfaceValueOrContainer() {
        super();
    }
    
    public UniffiVTableCallbackInterfaceValueOrContainer(
        UniffiCallbackInterfaceValueOrContainerMethod0 asContainer,
        UniffiCallbackInterfaceValueOrContainerMethod1 asLoroCounter,
        UniffiCallbackInterfaceValueOrContainerMethod2 asLoroList,
        UniffiCallbackInterfaceValueOrContainerMethod3 asLoroMap,
        UniffiCallbackInterfaceValueOrContainerMethod4 asLoroMovableList,
        UniffiCallbackInterfaceValueOrContainerMethod5 asLoroText,
        UniffiCallbackInterfaceValueOrContainerMethod6 asLoroTree,
        UniffiCallbackInterfaceValueOrContainerMethod7 asLoroUnknown,
        UniffiCallbackInterfaceValueOrContainerMethod8 asValue,
        UniffiCallbackInterfaceValueOrContainerMethod9 containerType,
        UniffiCallbackInterfaceValueOrContainerMethod10 isContainer,
        UniffiCallbackInterfaceValueOrContainerMethod11 isValue,
        UniffiCallbackInterfaceFree uniffiFree
    ) {
        this.asContainer = asContainer;
        this.asLoroCounter = asLoroCounter;
        this.asLoroList = asLoroList;
        this.asLoroMap = asLoroMap;
        this.asLoroMovableList = asLoroMovableList;
        this.asLoroText = asLoroText;
        this.asLoroTree = asLoroTree;
        this.asLoroUnknown = asLoroUnknown;
        this.asValue = asValue;
        this.containerType = containerType;
        this.isContainer = isContainer;
        this.isValue = isValue;
        this.uniffiFree = uniffiFree;
    }

    public static class UniffiByValue extends UniffiVTableCallbackInterfaceValueOrContainer implements Structure.ByValue {
        public UniffiByValue(
            UniffiCallbackInterfaceValueOrContainerMethod0 asContainer,
            UniffiCallbackInterfaceValueOrContainerMethod1 asLoroCounter,
            UniffiCallbackInterfaceValueOrContainerMethod2 asLoroList,
            UniffiCallbackInterfaceValueOrContainerMethod3 asLoroMap,
            UniffiCallbackInterfaceValueOrContainerMethod4 asLoroMovableList,
            UniffiCallbackInterfaceValueOrContainerMethod5 asLoroText,
            UniffiCallbackInterfaceValueOrContainerMethod6 asLoroTree,
            UniffiCallbackInterfaceValueOrContainerMethod7 asLoroUnknown,
            UniffiCallbackInterfaceValueOrContainerMethod8 asValue,
            UniffiCallbackInterfaceValueOrContainerMethod9 containerType,
            UniffiCallbackInterfaceValueOrContainerMethod10 isContainer,
            UniffiCallbackInterfaceValueOrContainerMethod11 isValue,
            UniffiCallbackInterfaceFree uniffiFree
        ) {
            super(asContainer,        
            asLoroCounter,        
            asLoroList,        
            asLoroMap,        
            asLoroMovableList,        
            asLoroText,        
            asLoroTree,        
            asLoroUnknown,        
            asValue,        
            containerType,        
            isContainer,        
            isValue,        
            uniffiFree        
            );
        }
    }

    void uniffiSetValue(UniffiVTableCallbackInterfaceValueOrContainer other) {
        asContainer = other.asContainer;
        asLoroCounter = other.asLoroCounter;
        asLoroList = other.asLoroList;
        asLoroMap = other.asLoroMap;
        asLoroMovableList = other.asLoroMovableList;
        asLoroText = other.asLoroText;
        asLoroTree = other.asLoroTree;
        asLoroUnknown = other.asLoroUnknown;
        asValue = other.asValue;
        containerType = other.containerType;
        isContainer = other.isContainer;
        isValue = other.isValue;
        uniffiFree = other.uniffiFree;
    }

}














































































































































































































































































































































































































































































































































































































































































































































































