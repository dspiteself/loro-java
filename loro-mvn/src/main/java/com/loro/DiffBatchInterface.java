package com.loro;


import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import com.sun.jna.*;
import com.sun.jna.ptr.*;
public interface DiffBatchInterface {
    
    /**
     * Returns an iterator over the diffs in this batch, in the order they were added.
     *
     * The iterator yields tuples of `(&ContainerID, &Diff)` where:
     * - `ContainerID` is the ID of the container that was modified
     * - `Diff` contains the actual changes made to that container
     *
     * The order of the diffs is preserved from when they were originally added to the batch.
     */
    public List<ContainerIdAndDiff> getDiff();
    
    /**
     * Push a new event to the batch.
     *
     * If the cid already exists in the batch, return Err
     */
    public Diff push(ContainerId cid, Diff diff);
    
}

