package com.loro;


import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import com.sun.jna.*;
import com.sun.jna.ptr.*;
public interface StyleConfigMapInterface {
    
    public StyleConfig get(String key);
    
    public void insert(String key, StyleConfig value);
    
}

