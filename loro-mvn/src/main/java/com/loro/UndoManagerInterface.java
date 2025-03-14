package com.loro;


import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import com.sun.jna.*;
import com.sun.jna.ptr.*;
public interface UndoManagerInterface {
    
    /**
     * If a local event's origin matches the given prefix, it will not be recorded in the
     * undo stack.
     */
    public void addExcludeOriginPrefix(String prefix);
    
    /**
     * Whether the undo manager can redo.
     */
    public Boolean canRedo();
    
    /**
     * Whether the undo manager can undo.
     */
    public Boolean canUndo();
    
    /**
     * Record a new checkpoint.
     */
    public void recordNewCheckpoint() throws LoroException;
    
    /**
     * Redo the last change made by the peer.
     */
    public Boolean redo() throws LoroException;
    
    /**
     * Set the maximum number of undo steps. The default value is 100.
     */
    public void setMaxUndoSteps(Integer size);
    
    /**
     * Set the merge interval in ms. The default value is 0, which means no merge.
     */
    public void setMergeInterval(Long interval);
    
    /**
     * Set the listener for pop events.
     * The listener will be called when an undo/redo item is popped from the stack.
     */
    public void setOnPop(OnPop onPop);
    
    /**
     * Set the listener for push events.
     * The listener will be called when a new undo/redo item is pushed into the stack.
     */
    public void setOnPush(OnPush onPush);
    
    /**
     * Undo the last change made by the peer.
     */
    public Boolean undo() throws LoroException;
    
}

