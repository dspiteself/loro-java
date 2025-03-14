package com.loro;


import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import com.sun.jna.*;
import com.sun.jna.ptr.*;
public interface LoroMovableListInterface {
    
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
    public LoroMovableList getAttached();
    
    public Long getCreatorAt(Integer pos);
    
    /**
     * Get the cursor at the given position.
     *
     * Using "index" to denote cursor positions can be unstable, as positions may
     * shift with document edits. To reliably represent a position or range within
     * a document, it is more effective to leverage the unique ID of each item/character
     * in a List CRDT or Text CRDT.
     *
     * Loro optimizes State metadata by not storing the IDs of deleted elements. This
     * approach complicates tracking cursors since they rely on these IDs. The solution
     * recalculates position by replaying relevant history to update stable positions
     * accurately. To minimize the performance impact of history replay, the system
     * updates cursor info to reference only the IDs of currently present elements,
     * thereby reducing the need for replay.
     */
    public Cursor getCursor(Integer pos, Side side);
    
    /**
     * Get the deep value of the container.
     */
    public LoroValue getDeepValue();
    
    /**
     * Get the last editor of the list item at the given position.
     */
    public Long getLastEditorAt(Integer pos);
    
    /**
     * Get the last mover of the list item at the given position.
     */
    public Long getLastMoverAt(Integer pos);
    
    /**
     * Get the shallow value of the container.
     *
     * This does not convert the state of sub-containers; instead, it represents them as [LoroValue::Container].
     */
    public LoroValue getValue();
    
    /**
     * Get the container id.
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
     * Move the value at the given position to the given position.
     */
    public void mov(Integer from, Integer to) throws LoroException;
    
    /**
     * Pop the last element of the list.
     */
    public ValueOrContainer pop() throws LoroException;
    
    public void push(LoroValueLike v) throws LoroException;
    
    /**
     * Set the value at the given position.
     */
    public void set(Integer pos, LoroValueLike value) throws LoroException;
    
    public LoroCounter setCounterContainer(Integer pos, LoroCounter child) throws LoroException;
    
    public LoroList setListContainer(Integer pos, LoroList child) throws LoroException;
    
    public LoroMap setMapContainer(Integer pos, LoroMap child) throws LoroException;
    
    public LoroMovableList setMovableListContainer(Integer pos, LoroMovableList child) throws LoroException;
    
    public LoroText setTextContainer(Integer pos, LoroText child) throws LoroException;
    
    public LoroTree setTreeContainer(Integer pos, LoroTree child) throws LoroException;
    
    /**
     * Get the elements of the list as a vector of LoroValues.
     *
     * This method returns a vector containing all the elements in the list as LoroValues.
     * It provides a convenient way to access the entire contents of the LoroMovableList
     * as a standard Rust vector.
     */
    public List<LoroValue> toVec();
    
}

