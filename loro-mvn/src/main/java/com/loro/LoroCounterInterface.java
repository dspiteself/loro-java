package com.loro;


import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import com.sun.jna.*;
import com.sun.jna.ptr.*;
public interface LoroCounterInterface {
    
    /**
     * Decrement the counter by the given value.
     */
    public void decrement(Double value) throws LoroException;
    
    /**
     * Get the LoroDoc from this container
     */
    public LoroDoc doc();
    
    /**
     * If a detached container is attached, this method will return its corresponding attached handler.
     */
    public LoroCounter getAttached();
    
    /**
     * Get the current value of the counter.
     */
    public Double getValue();
    
    /**
     * Return container id of the Counter.
     */
    public ContainerId id();
    
    /**
     * Increment the counter by the given value.
     */
    public void increment(Double value) throws LoroException;
    
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
    
}

