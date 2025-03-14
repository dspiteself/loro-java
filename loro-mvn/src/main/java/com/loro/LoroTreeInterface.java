package com.loro;


import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import com.sun.jna.*;
import com.sun.jna.ptr.*;
public interface LoroTreeInterface {
    
    /**
     * Return all children of the target node.
     *
     * If the parent node does not exist, return `None`.
     */
    public List<TreeId> children(TreeParentId parent);
    
    /**
     * Return the number of children of the target node.
     */
    public Integer childrenNum(TreeParentId parent);
    
    /**
     * Return whether target node exists.
     */
    public Boolean contains(TreeId target);
    
    /**
     * Create a new tree node and return the [`TreeID`].
     *
     * If the `parent` is `None`, the created node is the root of a tree.
     * Otherwise, the created node is a child of the parent tree node.
     */
    public TreeId create(TreeParentId parent) throws LoroException;
    
    /**
     * Create a new tree node at the given index and return the [`TreeID`].
     *
     * If the `parent` is `None`, the created node is the root of a tree.
     * If the `index` is greater than the number of children of the parent, error will be returned.
     */
    public TreeId createAt(TreeParentId parent, Integer index) throws LoroException;
    
    /**
     * Delete a tree node.
     *
     * Note: If the deleted node has children, the children do not appear in the state
     * rather than actually being deleted.
     */
    public void delete(TreeId target) throws LoroException;
    
    /**
     * Disable the fractional index generation when you don't need the Tree's siblings to be sorted.
     * The fractional index will always be set to the same default value 0.
     *
     * After calling this, you cannot use `tree.moveTo()`, `tree.moveBefore()`, `tree.moveAfter()`,
     * and `tree.createAt()`.
     */
    public void disableFractionalIndex();
    
    /**
     * Get the LoroDoc from this container
     */
    public LoroDoc doc();
    
    /**
     * Enable fractional index for Tree Position.
     *
     * The jitter is used to avoid conflicts when multiple users are creating the node at the same position.
     * value 0 is default, which means no jitter, any value larger than 0 will enable jitter.
     *
     * Generally speaking, jitter will affect the growth rate of document size.
     * [Read more about it](https://www.loro.dev/blog/movable-tree#implementation-and-encoding-size)
     */
    public void enableFractionalIndex(Byte jitter);
    
    /**
     * Return the fractional index of the target node with hex format.
     */
    public String fractionalIndex(TreeId target);
    
    /**
     * If a detached container is attached, this method will return its corresponding attached handler.
     */
    public LoroTree getAttached();
    
    /**
     * Get the last move id of the target node.
     */
    public Id getLastMoveId(TreeId target);
    
    /**
     * Get the associated metadata map handler of a tree node.
     */
    public LoroMap getMeta(TreeId target) throws LoroException;
    
    /**
     * Return the flat array of the forest.
     *
     * Note: the metadata will be not resolved. So if you don't only care about hierarchy
     * but also the metadata, you should use `get_value_with_meta()`.
     */
    public LoroValue getValue();
    
    /**
     * Return the flat array of the forest, each node is with metadata.
     */
    public LoroValue getValueWithMeta();
    
    /**
     * Return container id of the tree.
     */
    public ContainerId id();
    
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
    
    /**
     * Whether the fractional index is enabled.
     */
    public Boolean isFractionalIndexEnabled();
    
    /**
     * Return whether target node is deleted.
     *
     * # Errors
     * - If the target node does not exist, return `LoroTreeError::TreeNodeNotExist`.
     */
    public Boolean isNodeDeleted(TreeId target) throws LoroException;
    
    /**
     * Move the `target` node to be a child of the `parent` node.
     *
     * If the `parent` is `None`, the `target` node will be a root.
     */
    public void mov(TreeId target, TreeParentId parent) throws LoroException;
    
    /**
     * Move the `target` node to be a child after the `after` node with the same parent.
     */
    public void movAfter(TreeId target, TreeId after) throws LoroException;
    
    /**
     * Move the `target` node to be a child before the `before` node with the same parent.
     */
    public void movBefore(TreeId target, TreeId before) throws LoroException;
    
    /**
     * Move the `target` node to be a child of the `parent` node at the given index.
     * If the `parent` is `None`, the `target` node will be a root.
     */
    public void movTo(TreeId target, TreeParentId parent, Integer to) throws LoroException;
    
    /**
     * Return all nodes, including deleted nodes
     */
    public List<TreeId> nodes();
    
    /**
     * Return the parent of target node.
     *
     * - If the target node does not exist, throws Error.
     * - If the target node is a root node, return nil.
     */
    public TreeParentId parent(TreeId target) throws LoroException;
    
    /**
     * Get the root nodes of the forest.
     */
    public List<TreeId> roots();
    
}

