package com.loro;


import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import com.sun.jna.*;
import com.sun.jna.ptr.*;
public interface LoroListInterface {
    
    /**
     * Delete all elements in the list.
     */
    public void clear() throws LoroException;
    
    /**
     * Delete values at the given position.
     */
    public void delete(Integer pos, Integer len) throws LoroException;
    
    /**
     * Get the LoroDoc from this container
     */
    public LoroDoc doc();
    
    /**
     * Get the value at the given position.
     */
    public ValueOrContainer get(Integer index);
    
    /**
     * If a detached container is attached, this method will return its corresponding attached handler.
     */
    public LoroList getAttached();
    
    public Cursor getCursor(Integer pos, Side side);
    
    /**
     * Get the deep value of the container.
     */
    public LoroValue getDeepValue();
    
    /**
     * Get the ID of the list item at the given position.
     */
    public Id getIdAt(Integer pos);
    
    /**
     * Get the shallow value of the container.
     *
     * This does not convert the state of sub-containers; instead, it represents them as [LoroValue::Container].
     */
    public LoroValue getValue();
    
    /**
     * Get the ID of the container.
     */
    public ContainerId id();
    
    /**
     * Insert a value at the given position.
     */
    public void insert(Integer pos, LoroValueLike v) throws LoroException;
    
    public LoroCounter insertCounterContainer(Integer pos, LoroCounter child) throws LoroException;
    
    public LoroList insertListContainer(Integer pos, LoroList child) throws LoroException;
    
    public LoroMap insertMapContainer(Integer pos, LoroMap child) throws LoroException;
    
    public LoroMovableList insertMovableListContainer(Integer pos, LoroMovableList child) throws LoroException;
    
    public LoroText insertTextContainer(Integer pos, LoroText child) throws LoroException;
    
    public LoroTree insertTreeContainer(Integer pos, LoroTree child) throws LoroException;
    
    /**
     * Whether the container is attached to a document
     *
     * The edits on a detached container will not be persisted.
     * To attach the container to the document, please insert it into an attached container.
     */
    public Boolean isAttached();
    
    /**
     * Whether the container is deleted.
     */
    public Boolean isDeleted();
    
    public Boolean isEmpty();
    
    public Integer len();
    
    /**
     * Pop the last element of the list.
     */
    public LoroValue pop() throws LoroException;
    
    public void push(LoroValueLike v) throws LoroException;
    
    /**
     * Converts the LoroList to a Vec of LoroValue.
     *
     * This method unwraps the internal Arc and clones the data if necessary,
     * returning a Vec containing all the elements of the LoroList as LoroValue.
     */
    public List<LoroValue> toVec();
    
}

