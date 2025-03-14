package com.loro;


import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import com.sun.jna.*;
import com.sun.jna.ptr.*;
public interface LoroTextInterface {
    
    /**
     * Apply a [delta](https://quilljs.com/docs/delta/) to the text container.
     */
    public void applyDelta(List<TextDelta> delta) throws LoroException;
    
    /**
     * Delete a range of text at the given unicode position with unicode length.
     */
    public void delete(Integer pos, Integer len) throws LoroException;
    
    /**
     * Delete a range of text at the given utf-8 position with utf-8 length.
     */
    public void deleteUtf8(Integer pos, Integer len) throws LoroException;
    
    /**
     * Get the LoroDoc from this container
     */
    public LoroDoc doc();
    
    /**
     * If a detached container is attached, this method will return its corresponding attached handler.
     */
    public LoroText getAttached();
    
    /**
     * Get the cursor at the given position in the given Unicode position..
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
     * Get the editor of the text at the given position.
     */
    public Long getEditorAtUnicodePos(Integer pos);
    
    /**
     * Get the text in [Delta](https://quilljs.com/docs/delta/) format.
     */
    public LoroValue getRichtextValue();
    
    /**
     * Get the [ContainerID]  of the text container.
     */
    public ContainerId id();
    
    /**
     * Insert a string at the given unicode position.
     */
    public void insert(Integer pos, String s) throws LoroException;
    
    /**
     * Insert a string at the given utf-8 position.
     */
    public void insertUtf8(Integer pos, String s) throws LoroException;
    
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
     * Whether the text container is empty.
     */
    public Boolean isEmpty();
    
    /**
     * Get the length of the text container in Unicode.
     */
    public Integer lenUnicode();
    
    /**
     * Get the length of the text container in UTF-16.
     */
    public Integer lenUtf16();
    
    /**
     * Get the length of the text container in UTF-8.
     */
    public Integer lenUtf8();
    
    /**
     * Mark a range of text with a key-value pair.
     *
     * You can use it to create a highlight, make a range of text bold, or add a link to a range of text.
     *
     * You can specify the `expand` option to set the behavior when inserting text at the boundary of the range.
     *
     * - `after`(default): when inserting text right after the given range, the mark will be expanded to include the inserted text
     * - `before`: when inserting text right before the given range, the mark will be expanded to include the inserted text
     * - `none`: the mark will not be expanded to include the inserted text at the boundaries
     * - `both`: when inserting text either right before or right after the given range, the mark will be expanded to include the inserted text
     *
     * *You should make sure that a key is always associated with the same expand type.*
     *
     * Note: this is not suitable for unmergeable annotations like comments.
     */
    public void mark(Integer from, Integer to, String key, LoroValueLike value) throws LoroException;
    
    /**
     * Push a string to the end of the text container.
     */
    public void pushStr(String s) throws LoroException;
    
    /**
     * Get a string slice at the given Unicode range
     */
    public String slice(Integer startIndex, Integer endIndex) throws LoroException;
    
    /**
     * Delete specified character and insert string at the same position at given unicode position.
     */
    public String splice(Integer pos, Integer len, String s) throws LoroException;
    
    /**
     * Get the text in [Delta](https://quilljs.com/docs/delta/) format.
     */
    public List<TextDelta> toDelta();
    
    /**
     * Get the text content of the text container.
     */
    public String toString();
    
    /**
     * Unmark a range of text with a key and a value.
     *
     * You can use it to remove highlights, bolds or links
     *
     * You can specify the `expand` option to set the behavior when inserting text at the boundary of the range.
     *
     * **Note: You should specify the same expand type as when you mark the text.**
     *
     * - `after`(default): when inserting text right after the given range, the mark will be expanded to include the inserted text
     * - `before`: when inserting text right before the given range, the mark will be expanded to include the inserted text
     * - `none`: the mark will not be expanded to include the inserted text at the boundaries
     * - `both`: when inserting text either right before or right after the given range, the mark will be expanded to include the inserted text
     *
     * *You should make sure that a key is always associated with the same expand type.*
     *
     * Note: you cannot delete unmergeable annotations like comments by this method.
     */
    public void unmark(Integer from, Integer to, String key) throws LoroException;
    
    /**
     * Update the current text based on the provided text.
     *
     * It will calculate the minimal difference and apply it to the current text.
     * It uses Myers' diff algorithm to compute the optimal difference.
     *
     * This could take a long time for large texts (e.g. > 50_000 characters).
     * In that case, you should use `updateByLine` instead.
     */
    public void update(String s, UpdateOptions options) throws UpdateTimeoutException;
    
    /**
     * Update the current text based on the provided text.
     *
     * This update calculation is line-based, which will be more efficient but less precise.
     */
    public void updateByLine(String s, UpdateOptions options) throws UpdateTimeoutException;
    
}

