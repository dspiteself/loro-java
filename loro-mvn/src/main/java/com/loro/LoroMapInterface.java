package com.loro;


import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import com.sun.jna.*;
import com.sun.jna.ptr.*;
public interface LoroMapInterface {
    
    /**
     * Delete all key-value pairs in the map.
     */
    public void clear() throws LoroException;
    
    /**
     * Delete a key-value pair from the map.
     */
    public void delete(String key) throws LoroException;
    
    /**
     * Get the LoroDoc from this container
     */
    public LoroDoc doc();
    
    /**
     * Get the value of the map with the given key.
     */
    public ValueOrContainer get(String key);
    
    /**
     * If a detached container is attached, this method will return its corresponding attached handler.
     */
    public LoroMap getAttached();
    
    /**
     * Get the deep value of the map.
     *
     * It will convert the state of sub-containers into a nested JSON value.
     */
    public LoroValue getDeepValue();
    
    /**
     * Get the peer id of the last editor on the given entry
     */
    public Long getLastEditor(String key);
    
    public LoroCounter getOrCreateCounterContainer(String key, LoroCounter child) throws LoroException;
    
    public LoroList getOrCreateListContainer(String key, LoroList child) throws LoroException;
    
    public LoroMap getOrCreateMapContainer(String key, LoroMap child) throws LoroException;
    
    public LoroMovableList getOrCreateMovableListContainer(String key, LoroMovableList child) throws LoroException;
    
    public LoroText getOrCreateTextContainer(String key, LoroText child) throws LoroException;
    
    public LoroTree getOrCreateTreeContainer(String key, LoroTree child) throws LoroException;
    
    /**
     * Get the shallow value of the map.
     *
     * It will not convert the state of sub-containers, but represent them as [LoroValue::Container].
     */
    public LoroValue getValue();
    
    /**
     * Get the ID of the map.
     */
    public ContainerId id();
    
    /**
     * Insert a key-value pair into the map.
     *
     * > **Note**: When calling `map.set(key, value)` on a LoroMap, if `map.get(key)` already returns `value`,
     * > the operation will be a no-op (no operation recorded) to avoid unnecessary updates.
     */
    public void insert(String key, LoroValueLike v) throws LoroException;
    
    public LoroCounter insertCounterContainer(String key, LoroCounter child) throws LoroException;
    
    public LoroList insertListContainer(String key, LoroList child) throws LoroException;
    
    public LoroMap insertMapContainer(String key, LoroMap child) throws LoroException;
    
    public LoroMovableList insertMovableListContainer(String key, LoroMovableList child) throws LoroException;
    
    public LoroText insertTextContainer(String key, LoroText child) throws LoroException;
    
    public LoroTree insertTreeContainer(String key, LoroTree child) throws LoroException;
    
    /**
     * Whether the container is attached to a document.
     */
    public Boolean isAttached();
    
    /**
     * Whether the container is deleted.
     */
    public Boolean isDeleted();
    
    /**
     * Whether the map is empty.
     */
    public Boolean isEmpty();
    
    /**
     * Get the keys of the map.
     */
    public List<String> keys();
    
    /**
     * Get the length of the map.
     */
    public Integer len();
    
    /**
     * Get the values of the map.
     */
    public List<ValueOrContainer> values();
    
}

