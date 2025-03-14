package com.loro;


import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import com.sun.jna.*;
import com.sun.jna.ptr.*;
public interface ValueOrContainerInterface {
    
    public ContainerId asContainer();
    
    public LoroCounter asLoroCounter();
    
    public LoroList asLoroList();
    
    public LoroMap asLoroMap();
    
    public LoroMovableList asLoroMovableList();
    
    public LoroText asLoroText();
    
    public LoroTree asLoroTree();
    
    public LoroUnknown asLoroUnknown();
    
    public LoroValue asValue();
    
    public ContainerType containerType();
    
    public Boolean isContainer();
    
    public Boolean isValue();
    
}

