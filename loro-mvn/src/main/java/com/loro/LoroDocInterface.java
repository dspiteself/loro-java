package com.loro;


import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import com.sun.jna.*;
import com.sun.jna.ptr.*;
/**
 * `LoroDoc` is the entry for the whole document.
 * When it's dropped, all the associated [`Handler`]s will be invalidated.
 *
 * **Important:** Loro is a pure library and does not handle network protocols.
 * It is the responsibility of the user to manage the storage, loading, and synchronization
 * of the bytes exported by Loro in a manner suitable for their specific environment.
 */
public interface LoroDocInterface {
    
    /**
     * Apply a diff to the current document state.
     *
     * Internally, it will apply the diff to the current state.
     */
    public void applyDiff(DiffBatch diff) throws LoroException;
    
    /**
     * Attach the document state to the latest known version.
     *
     * > The document becomes detached during a `checkout` operation.
     * > Being `detached` implies that the `DocState` is not synchronized with the latest version of the `OpLog`.
     * > In a detached state, the document is not editable, and any `import` operations will be
     * > recorded in the `OpLog` without being applied to the `DocState`.
     */
    public void attach();
    
    /**
     * Check the correctness of the document state by comparing it with the state
     * calculated by applying all the history.
     */
    public void checkStateCorrectnessSlow();
    
    /**
     * Checkout the `DocState` to a specific version.
     *
     * The document becomes detached during a `checkout` operation.
     * Being `detached` implies that the `DocState` is not synchronized with the latest version of the `OpLog`.
     * In a detached state, the document is not editable, and any `import` operations will be
     * recorded in the `OpLog` without being applied to the `DocState`.
     *
     * You should call `attach` to attach the `DocState` to the latest version of `OpLog`.
     */
    public void checkout(Frontiers frontiers) throws LoroException;
    
    /**
     * Checkout the `DocState` to the latest version.
     *
     * > The document becomes detached during a `checkout` operation.
     * > Being `detached` implies that the `DocState` is not synchronized with the latest version of the `OpLog`.
     * > In a detached state, the document is not editable, and any `import` operations will be
     * > recorded in the `OpLog` without being applied to the `DocState`.
     *
     * This has the same effect as `attach`.
     */
    public void checkoutToLatest();
    
    /**
     * Clear the options of the next commit.
     */
    public void clearNextCommitOptions();
    
    /**
     * Compare the frontiers with the current OpLog's version.
     *
     * If `other` contains any version that's not contained in the current OpLog, return [Ordering::Less].
     */
    public Ordering cmpWithFrontiers(Frontiers other);
    
    /**
     * Commit the cumulative auto commit transaction.
     *
     * There is a transaction behind every operation.
     * The events will be emitted after a transaction is committed. A transaction is committed when:
     *
     * - `doc.commit()` is called.
     * - `doc.export(mode)` is called.
     * - `doc.import(data)` is called.
     * - `doc.checkout(version)` is called.
     */
    public void commit();
    
    public void commitWith(CommitOptions options);
    
    /**
     * Encoded all ops and history cache to bytes and store them in the kv store.
     *
     * The parsed ops will be dropped
     */
    public void compactChangeStore();
    
    /**
     * Get the configurations of the document.
     */
    public Configure config();
    
    /**
     * Configures the default text style for the document.
     *
     * This method sets the default text style configuration for the document when using LoroText.
     * If `None` is provided, the default style is reset.
     *
     * # Parameters
     *
     * - `text_style`: The style configuration to set as the default. `None` to reset.
     */
    public void configDefaultTextStyle(StyleConfig textStyle);
    
    /**
     * Set the rich text format configuration of the document.
     *
     * You need to config it if you use rich text `mark` method.
     * Specifically, you need to config the `expand` property of each style.
     *
     * Expand is used to specify the behavior of expanding when new text is inserted at the
     * beginning or end of the style.
     */
    public void configTextStyle(StyleConfigMap textStyle);
    
    /**
     * Force the document enter the detached mode.
     *
     * In this mode, when you importing new updates, the [loro_internal::DocState] will not be changed.
     *
     * Learn more at https://loro.dev/docs/advanced/doc_state_and_oplog#attacheddetached-status
     */
    public void detach();
    
    /**
     * Calculate the diff between two versions
     */
    public DiffBatch diff(Frontiers a, Frontiers b) throws LoroException;
    
    /**
     * Export the readable [`Change`]s in the given [`IdSpan`]
     */
    public List<String> exportJsonInIdSpan(IdSpan idSpan);
    
    /**
     * Export the current state with json-string format of the document.
     */
    public String exportJsonUpdates(VersionVector startVv, VersionVector endVv);
    
    /**
     * Export the current state with json-string format of the document, without peer compression.
     *
     * Compared to [`export_json_updates`], this method does not compress the peer IDs in the updates.
     * So the operations are easier to be processed by application code.
     */
    public String exportJsonUpdatesWithoutPeerCompression(VersionVector startVv, VersionVector endVv);
    
    public byte[] exportShallowSnapshot(Frontiers frontiers) throws LoroEncodeException;
    
    /**
     * Export the current state and history of the document.
     */
    public byte[] exportSnapshot() throws LoroEncodeException;
    
    public byte[] exportSnapshotAt(Frontiers frontiers) throws LoroEncodeException;
    
    public byte[] exportStateOnly(Frontiers frontiers) throws LoroEncodeException;
    
    /**
     * Export all the ops not included in the given `VersionVector`
     */
    public byte[] exportUpdates(VersionVector vv) throws LoroEncodeException;
    
    public byte[] exportUpdatesInRange(List<IdSpan> spans) throws LoroEncodeException;
    
    /**
     * Find the operation id spans that between the `from` version and the `to` version.
     */
    public VersionVectorDiff findIdSpansBetween(Frontiers from, Frontiers to);
    
    /**
     * Duplicate the document with a different PeerID
     *
     * The time complexity and space complexity of this operation are both O(n),
     *
     * When called in detached mode, it will fork at the current state frontiers.
     * It will have the same effect as `fork_at(&self.state_frontiers())`.
     */
    public LoroDoc fork();
    
    /**
     * Fork the document at the given frontiers.
     *
     * The created doc will only contain the history before the specified frontiers.
     */
    public LoroDoc forkAt(Frontiers frontiers);
    
    /**
     * Free the cached diff calculator that is used for checkout.
     */
    public void freeDiffCalculator();
    
    /**
     * Free the history cache that is used for making checkout faster.
     *
     * If you use checkout that switching to an old/concurrent version, the history cache will be built.
     * You can free it by calling this method.
     */
    public void freeHistoryCache();
    
    /**
     * Convert `Frontiers` into `VersionVector`
     */
    public VersionVector frontiersToVv(Frontiers frontiers);
    
    /**
     * Get the handler by the path.
     */
    public ValueOrContainer getByPath(List<Index> path);
    
    /**
     * The path can be specified in different ways depending on the container type:
     *
     * For Tree:
     * 1. Using node IDs: `tree/{node_id}/property`
     * 2. Using indices: `tree/0/1/property`
     *
     * For List and MovableList:
     * - Using indices: `list/0` or `list/1/property`
     *
     * For Map:
     * - Using keys: `map/key` or `map/nested/property`
     *
     * For tree structures, index-based paths follow depth-first traversal order.
     * The indices start from 0 and represent the position of a node among its siblings.
     *
     * # Examples
     * ```
     * # use loro::{LoroDoc, LoroValue};
     * let doc = LoroDoc::new();
     *
     * // Tree example
     * let tree = doc.get_tree("tree");
     * let root = tree.create(None).unwrap();
     * tree.get_meta(root).unwrap().insert("name", "root").unwrap();
     * // Access tree by ID or index
     * let name1 = doc.get_by_str_path(&format!("tree/{}/name", root)).unwrap().into_value().unwrap();
     * let name2 = doc.get_by_str_path("tree/0/name").unwrap().into_value().unwrap();
     * assert_eq!(name1, name2);
     *
     * // List example
     * let list = doc.get_list("list");
     * list.insert(0, "first").unwrap();
     * list.insert(1, "second").unwrap();
     * // Access list by index
     * let item = doc.get_by_str_path("list/0");
     * assert_eq!(item.unwrap().into_value().unwrap().into_string().unwrap(), "first".into());
     *
     * // Map example
     * let map = doc.get_map("map");
     * map.insert("key", "value").unwrap();
     * // Access map by key
     * let value = doc.get_by_str_path("map/key");
     * assert_eq!(value.unwrap().into_value().unwrap().into_string().unwrap(), "value".into());
     *
     * // MovableList example
     * let mlist = doc.get_movable_list("mlist");
     * mlist.insert(0, "item").unwrap();
     * // Access movable list by index
     * let item = doc.get_by_str_path("mlist/0");
     * assert_eq!(item.unwrap().into_value().unwrap().into_string().unwrap(), "item".into());
     * ```
     */
    public ValueOrContainer getByStrPath(String path);
    
    /**
     * Get `Change` at the given id.
     *
     * `Change` is a grouped continuous operations that share the same id, timestamp, commit message.
     *
     * - The id of the `Change` is the id of its first op.
     * - The second op's id is `{ peer: change.id.peer, counter: change.id.counter + 1 }`
     *
     * The same applies on `Lamport`:
     *
     * - The lamport of the `Change` is the lamport of its first op.
     * - The second op's lamport is `change.lamport + 1`
     *
     * The length of the `Change` is how many operations it contains
     */
    public ChangeMeta getChange(Id id);
    
    /**
     * Gets container IDs modified in the given ID range.
     *
     * **NOTE:** This method will implicitly commit.
     *
     * This method can be used in conjunction with `doc.travel_change_ancestors()` to traverse
     * the history and identify all changes that affected specific containers.
     *
     * # Arguments
     *
     * * `id` - The starting ID of the change range
     * * `len` - The length of the change range to check
     */
    public List<ContainerId> getChangedContainersIn(Id id, Integer len);
    
    /**
     * Get a [LoroCounter] by container id.
     *
     * If the provided id is string, it will be converted into a root container id with the name of the string.
     */
    public LoroCounter getCounter(ContainerIdLike id);
    
    public PosQueryResult getCursorPos(Cursor cursor) throws CannotFindRelativePosition;
    
    /**
     * Get the entire state of the current DocState
     */
    public LoroValue getDeepValue();
    
    /**
     * Get the entire state of the current DocState with container id
     */
    public LoroValue getDeepValueWithId();
    
    /**
     * Get a [LoroList] by container id.
     *
     * If the provided id is string, it will be converted into a root container id with the name of the string.
     */
    public LoroList getList(ContainerIdLike id);
    
    /**
     * Get a [LoroMap] by container id.
     *
     * If the provided id is string, it will be converted into a root container id with the name of the string.
     */
    public LoroMap getMap(ContainerIdLike id);
    
    /**
     * Get a [LoroMovableList] by container id.
     *
     * If the provided id is string, it will be converted into a root container id with the name of the string.
     */
    public LoroMovableList getMovableList(ContainerIdLike id);
    
    /**
     * Get the path from the root to the container
     */
    public List<ContainerPath> getPathToContainer(ContainerId id);
    
    /**
     * Get the number of operations in the pending transaction.
     *
     * The pending transaction is the one that is not committed yet. It will be committed
     * after calling `doc.commit()`, `doc.export(mode)` or `doc.checkout(version)`.
     */
    public Integer getPendingTxnLen();
    
    /**
     * Get a [LoroText] by container id.
     *
     * If the provided id is string, it will be converted into a root container id with the name of the string.
     */
    public LoroText getText(ContainerIdLike id);
    
    /**
     * Get a [LoroTree] by container id.
     *
     * If the provided id is string, it will be converted into a root container id with the name of the string.
     */
    public LoroTree getTree(ContainerIdLike id);
    
    /**
     * Get the shallow value of the document.
     */
    public LoroValue getValue();
    
    /**
     * Check if the doc contains the target container.
     *
     * A root container always exists, while a normal container exists
     * if it has ever been created on the doc.
     */
    public Boolean hasContainer(ContainerId id);
    
    public Boolean hasHistoryCache();
    
    /**
     * Import updates/snapshot exported by [`LoroDoc::export_snapshot`] or [`LoroDoc::export_from`].
     */
    public ImportStatus _import(byte[] bytes) throws LoroException;
    
    /**
     * Import a batch of updates/snapshot.
     *
     * The data can be in arbitrary order. The import result will be the same.
     */
    public ImportStatus importBatch(List<byte[]> bytes) throws LoroException;
    
    public ImportStatus importJsonUpdates(String json) throws LoroException;
    
    /**
     * Import updates/snapshot exported by [`LoroDoc::export_snapshot`] or [`LoroDoc::export_from`].
     *
     * It marks the import with a custom `origin` string. It can be used to track the import source
     * in the generated events.
     */
    public ImportStatus importWith(byte[] bytes, String origin) throws LoroException;
    
    /**
     * Whether the document is in detached mode, where the [loro_internal::DocState] is not
     * synchronized with the latest version of the [loro_internal::OpLog].
     */
    public Boolean isDetached();
    
    /**
     * Check if the doc contains the full history.
     */
    public Boolean isShallow();
    
    /**
     * Evaluate a JSONPath expression on the document and return matching values or handlers.
     *
     * This method allows querying the document structure using JSONPath syntax.
     * It returns a vector of `ValueOrHandler` which can represent either primitive values
     * or container handlers, depending on what the JSONPath expression matches.
     *
     * # Arguments
     *
     * * `path` - A string slice containing the JSONPath expression to evaluate.
     *
     * # Returns
     *
     * A `Result` containing either:
     * - `Ok(Vec<ValueOrHandler>)`: A vector of matching values or handlers.
     * - `Err(String)`: An error message if the JSONPath expression is invalid or evaluation fails.
     *
     * # Example
     *
     * ```
     * # use loro::LoroDoc;
     * let doc = LoroDoc::new();
     * let map = doc.get_map("users");
     * map.insert("alice", 30).unwrap();
     * map.insert("bob", 25).unwrap();
     *
     * let result = doc.jsonpath("$.users.alice").unwrap();
     * assert_eq!(result.len(), 1);
     * assert_eq!(result[0].to_json_value(), serde_json::json!(30));
     * ```
     */
    public List<ValueOrContainer> jsonpath(String path) throws JsonPathException;
    
    /**
     * Get the total number of changes in the `OpLog`
     */
    public Long lenChanges();
    
    /**
     * Get the total number of operations in the `OpLog`
     */
    public Long lenOps();
    
    /**
     * Estimate the size of the document states in memory.
     */
    public void logEstimateSize();
    
    /**
     * Minimize the frontiers by removing the unnecessary entries.
     */
    public FrontiersOrId minimizeFrontiers(Frontiers frontiers);
    
    /**
     * Get the `Frontiers` version of `OpLog`
     */
    public Frontiers oplogFrontiers();
    
    /**
     * Get the `VersionVector` version of `OpLog`
     */
    public VersionVector oplogVv();
    
    /**
     * Get the PeerID
     */
    public Long peerId();
    
    /**
     * Revert the current document state back to the target version
     *
     * Internally, it will generate a series of local operations that can revert the
     * current doc to the target version. It will calculate the diff between the current
     * state and the target state, and apply the diff to the current state.
     */
    public void revertTo(Frontiers version) throws LoroException;
    
    /**
     * Set the interval of mergeable changes, **in seconds**.
     *
     * If two continuous local changes are within the interval, they will be merged into one change.
     * The default value is 1000 seconds.
     *
     * By default, we record timestamps in seconds for each change. So if the merge interval is 1, and changes A and B
     * have timestamps of 3 and 4 respectively, then they will be merged into one change
     */
    public void setChangeMergeInterval(Long interval);
    
    /**
     * Set commit message for the current uncommitted changes
     *
     * It will be persisted.
     */
    public void setNextCommitMessage(String msg);
    
    /**
     * Set the options of the next commit.
     *
     * It will be used when the next commit is performed.
     */
    public void setNextCommitOptions(CommitOptions options);
    
    /**
     * Set `origin` for the current uncommitted changes, it can be used to track the source of changes in an event.
     *
     * It will NOT be persisted.
     */
    public void setNextCommitOrigin(String origin);
    
    /**
     * Set the timestamp of the next commit.
     *
     * It will be persisted and stored in the `OpLog`.
     * You can get the timestamp from the [`Change`] type.
     */
    public void setNextCommitTimestamp(Long timestamp);
    
    /**
     * Change the PeerID
     *
     * NOTE: You need to make sure there is no chance two peer have the same PeerID.
     * If it happens, the document will be corrupted.
     */
    public void setPeerId(Long peer) throws LoroException;
    
    /**
     * Set whether to record the timestamp of each change. Default is `false`.
     *
     * If enabled, the Unix timestamp will be recorded for each change automatically.
     *
     * You can set each timestamp manually when committing a change.
     *
     * NOTE: Timestamps are forced to be in ascending order.
     * If you commit a new change with a timestamp that is less than the existing one,
     * the largest existing timestamp will be used instead.
     */
    public void setRecordTimestamp(Boolean record);
    
    /**
     * Get the `VersionVector` of trimmed history
     *
     * The ops included by the trimmed history are not in the doc.
     */
    public VersionVector shallowSinceVv();
    
    /**
     * Get the `Frontiers` version of `DocState`
     *
     * Learn more about [`Frontiers`](https://loro.dev/docs/advanced/version_deep_dive)
     */
    public Frontiers stateFrontiers();
    
    /**
     * Get the `VersionVector` version of `DocState`
     */
    public VersionVector stateVv();
    
    /**
     * Subscribe the events of a container.
     *
     * The callback will be invoked when the container is changed.
     * Returns a subscription that can be used to unsubscribe.
     *
     * The events will be emitted after a transaction is committed. A transaction is committed when:
     *
     * - `doc.commit()` is called.
     * - `doc.export(mode)` is called.
     * - `doc.import(data)` is called.
     * - `doc.checkout(version)` is called.
     */
    public Subscription subscribe(ContainerId containerId, Subscriber subscriber);
    
    /**
     * Subscribe the local update of the document.
     */
    public Subscription subscribeLocalUpdate(LocalUpdateCallback callback);
    
    /**
     * Subscribe all the events.
     *
     * The callback will be invoked when any part of the [loro_internal::DocState] is changed.
     * Returns a subscription that can be used to unsubscribe.
     */
    public Subscription subscribeRoot(Subscriber subscriber);
    
    /**
     * Traverses the ancestors of the Change containing the given ID, including itself.
     *
     * This method visits all ancestors in causal order, from the latest to the oldest,
     * based on their Lamport timestamps.
     *
     * # Arguments
     *
     * * `ids` - The IDs of the Change to start the traversal from.
     * * `f` - A mutable function that is called for each ancestor. It can return `ControlFlow::Break(())` to stop the traversal.
     */
    public void travelChangeAncestors(List<Id> ids, ChangeAncestorsTraveler f) throws ChangeTravelException;
    
    /**
     * Convert `VersionVector` into `Frontiers`
     */
    public Frontiers vvToFrontiers(VersionVector vv);
    
}

