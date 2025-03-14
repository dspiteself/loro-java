package com.loro;


import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Function;
import java.util.function.Consumer;
import com.sun.jna.Pointer;
import java.util.concurrent.CompletableFuture;
public class LoroTree implements AutoCloseable, LoroTreeInterface {
  protected Pointer pointer;
  protected UniffiCleaner.Cleanable cleanable;

  private AtomicBoolean wasDestroyed = new AtomicBoolean(false);
  private AtomicLong callCounter = new AtomicLong(1);

  public LoroTree(Pointer pointer) {
    this.pointer = pointer;
    this.cleanable = UniffiLib.CLEANER.register(this, new UniffiCleanAction(pointer));
  }

  /**
   * This constructor can be used to instantiate a fake object. Only used for tests. Any
   * attempt to actually use an object constructed this way will fail as there is no
   * connected Rust object.
   */
  public LoroTree(NoPointer noPointer) {
    this.pointer = null;
    this.cleanable = UniffiLib.CLEANER.register(this, new UniffiCleanAction(pointer));
  }

  
    /**
     * Create a new container that is detached from the document.
     *
     * The edits on a detached container will not be persisted.
     * To attach the container to the document, please insert it into an attached container.
     */
  public LoroTree() {
    this((Pointer)
    UniffiHelpers.uniffiRustCall( _status -> {
        return UniffiLib.INSTANCE.uniffi_loro_fn_constructor_lorotree_new(
            _status);
    })
    );
  }

  @Override
  public synchronized void close() {
    // Only allow a single call to this method.
    // TODO(uniffi): maybe we should log a warning if called more than once?
    if (this.wasDestroyed.compareAndSet(false, true)) {
      // This decrement always matches the initial count of 1 given at creation time.
      if (this.callCounter.decrementAndGet() == 0L) {
        cleanable.clean();
      }
    }
  }

  public <R> R callWithPointer(Function<Pointer, R> block) {
    // Check and increment the call counter, to keep the object alive.
    // This needs a compare-and-set retry loop in case of concurrent updates.
    long c;
    do {
      c = this.callCounter.get();
      if (c == 0L) {
        throw new IllegalStateException("LoroTree object has already been destroyed");
      }
      if (c == Long.MAX_VALUE) {
        throw new IllegalStateException("LoroTree call counter would overflow");
      }
    } while (! this.callCounter.compareAndSet(c, c + 1L));
    // Now we can safely do the method call without the pointer being freed concurrently.
    try {
      return block.apply(this.uniffiClonePointer());
    } finally {
      // This decrement always matches the increment we performed above.
      if (this.callCounter.decrementAndGet() == 0L) {
          cleanable.clean();
      }
    }
  }

  public void callWithPointer(Consumer<Pointer> block) {
    callWithPointer((Pointer p) -> {
      block.accept(p);
      return (Void)null;
    });
  }

  private class UniffiCleanAction implements Runnable {
    private final Pointer pointer;

    public UniffiCleanAction(Pointer pointer) {
      this.pointer = pointer;
    }

    @Override
    public void run() {
      if (pointer != null) {
        UniffiHelpers.uniffiRustCall(status -> {
          UniffiLib.INSTANCE.uniffi_loro_fn_free_lorotree(pointer, status);
          return null;
        });
      }
    }
  }

  Pointer uniffiClonePointer() {
    return UniffiHelpers.uniffiRustCall(status -> {
      if (pointer == null) {
        throw new NullPointerException();
      }
      return UniffiLib.INSTANCE.uniffi_loro_fn_clone_lorotree(pointer, status);
    });
  }

  
    /**
     * Return all children of the target node.
     *
     * If the parent node does not exist, return `None`.
     */
    @Override
    public List<TreeId> children(TreeParentId parent)  {
            try {
                return FfiConverterOptionalSequenceTypeTreeID.INSTANCE.lift(
    callWithPointer(it -> {
        try {
    
            return
    UniffiHelpers.uniffiRustCall( _status -> {
        return UniffiLib.INSTANCE.uniffi_loro_fn_method_lorotree_children(
            it, FfiConverterTypeTreeParentId.INSTANCE.lower(parent), _status);
    });
    
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    })
    );
            } catch (RuntimeException _e) {
                
                
                if (InternalException.class.isInstance(_e.getCause())) {
                    throw (InternalException)_e.getCause();
                }
                throw _e;
            }
    }
    

  
    /**
     * Return the number of children of the target node.
     */
    @Override
    public Integer childrenNum(TreeParentId parent)  {
            try {
                return FfiConverterOptionalInteger.INSTANCE.lift(
    callWithPointer(it -> {
        try {
    
            return
    UniffiHelpers.uniffiRustCall( _status -> {
        return UniffiLib.INSTANCE.uniffi_loro_fn_method_lorotree_children_num(
            it, FfiConverterTypeTreeParentId.INSTANCE.lower(parent), _status);
    });
    
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    })
    );
            } catch (RuntimeException _e) {
                
                
                if (InternalException.class.isInstance(_e.getCause())) {
                    throw (InternalException)_e.getCause();
                }
                throw _e;
            }
    }
    

  
    /**
     * Return whether target node exists.
     */
    @Override
    public Boolean contains(TreeId target)  {
            try {
                return FfiConverterBoolean.INSTANCE.lift(
    callWithPointer(it -> {
        try {
    
            return
    UniffiHelpers.uniffiRustCall( _status -> {
        return UniffiLib.INSTANCE.uniffi_loro_fn_method_lorotree_contains(
            it, FfiConverterTypeTreeID.INSTANCE.lower(target), _status);
    });
    
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    })
    );
            } catch (RuntimeException _e) {
                
                
                if (InternalException.class.isInstance(_e.getCause())) {
                    throw (InternalException)_e.getCause();
                }
                throw _e;
            }
    }
    

  
    /**
     * Create a new tree node and return the [`TreeID`].
     *
     * If the `parent` is `None`, the created node is the root of a tree.
     * Otherwise, the created node is a child of the parent tree node.
     */
    @Override
    public TreeId create(TreeParentId parent) throws LoroException {
            try {
                return FfiConverterTypeTreeID.INSTANCE.lift(
    callWithPointer(it -> {
        try {
    
            return
    UniffiHelpers.uniffiRustCallWithError(new LoroExceptionErrorHandler(), _status -> {
        return UniffiLib.INSTANCE.uniffi_loro_fn_method_lorotree_create(
            it, FfiConverterTypeTreeParentId.INSTANCE.lower(parent), _status);
    });
    
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    })
    );
            } catch (RuntimeException _e) {
                
                if (LoroException.class.isInstance(_e.getCause())) {
                    throw (LoroException)_e.getCause();
                }
                
                if (InternalException.class.isInstance(_e.getCause())) {
                    throw (InternalException)_e.getCause();
                }
                throw _e;
            }
    }
    

  
    /**
     * Create a new tree node at the given index and return the [`TreeID`].
     *
     * If the `parent` is `None`, the created node is the root of a tree.
     * If the `index` is greater than the number of children of the parent, error will be returned.
     */
    @Override
    public TreeId createAt(TreeParentId parent, Integer index) throws LoroException {
            try {
                return FfiConverterTypeTreeID.INSTANCE.lift(
    callWithPointer(it -> {
        try {
    
            return
    UniffiHelpers.uniffiRustCallWithError(new LoroExceptionErrorHandler(), _status -> {
        return UniffiLib.INSTANCE.uniffi_loro_fn_method_lorotree_create_at(
            it, FfiConverterTypeTreeParentId.INSTANCE.lower(parent), FfiConverterInteger.INSTANCE.lower(index), _status);
    });
    
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    })
    );
            } catch (RuntimeException _e) {
                
                if (LoroException.class.isInstance(_e.getCause())) {
                    throw (LoroException)_e.getCause();
                }
                
                if (InternalException.class.isInstance(_e.getCause())) {
                    throw (InternalException)_e.getCause();
                }
                throw _e;
            }
    }
    

  
    /**
     * Delete a tree node.
     *
     * Note: If the deleted node has children, the children do not appear in the state
     * rather than actually being deleted.
     */
    @Override
    public void delete(TreeId target) throws LoroException {
            try {
                
    callWithPointer(it -> {
        try {
    
    UniffiHelpers.uniffiRustCallWithError(new LoroExceptionErrorHandler(), _status -> {
        UniffiLib.INSTANCE.uniffi_loro_fn_method_lorotree_delete(
            it, FfiConverterTypeTreeID.INSTANCE.lower(target), _status);
    });
    
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    })
    ;
            } catch (RuntimeException _e) {
                
                if (LoroException.class.isInstance(_e.getCause())) {
                    throw (LoroException)_e.getCause();
                }
                
                if (InternalException.class.isInstance(_e.getCause())) {
                    throw (InternalException)_e.getCause();
                }
                throw _e;
            }
    }
    

  
    /**
     * Disable the fractional index generation when you don't need the Tree's siblings to be sorted.
     * The fractional index will always be set to the same default value 0.
     *
     * After calling this, you cannot use `tree.moveTo()`, `tree.moveBefore()`, `tree.moveAfter()`,
     * and `tree.createAt()`.
     */
    @Override
    public void disableFractionalIndex()  {
            try {
                
    callWithPointer(it -> {
        try {
    
    UniffiHelpers.uniffiRustCall( _status -> {
        UniffiLib.INSTANCE.uniffi_loro_fn_method_lorotree_disable_fractional_index(
            it, _status);
    });
    
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    })
    ;
            } catch (RuntimeException _e) {
                
                
                if (InternalException.class.isInstance(_e.getCause())) {
                    throw (InternalException)_e.getCause();
                }
                throw _e;
            }
    }
    

  
    /**
     * Get the LoroDoc from this container
     */
    @Override
    public LoroDoc doc()  {
            try {
                return FfiConverterOptionalTypeLoroDoc.INSTANCE.lift(
    callWithPointer(it -> {
        try {
    
            return
    UniffiHelpers.uniffiRustCall( _status -> {
        return UniffiLib.INSTANCE.uniffi_loro_fn_method_lorotree_doc(
            it, _status);
    });
    
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    })
    );
            } catch (RuntimeException _e) {
                
                
                if (InternalException.class.isInstance(_e.getCause())) {
                    throw (InternalException)_e.getCause();
                }
                throw _e;
            }
    }
    

  
    /**
     * Enable fractional index for Tree Position.
     *
     * The jitter is used to avoid conflicts when multiple users are creating the node at the same position.
     * value 0 is default, which means no jitter, any value larger than 0 will enable jitter.
     *
     * Generally speaking, jitter will affect the growth rate of document size.
     * [Read more about it](https://www.loro.dev/blog/movable-tree#implementation-and-encoding-size)
     */
    @Override
    public void enableFractionalIndex(Byte jitter)  {
            try {
                
    callWithPointer(it -> {
        try {
    
    UniffiHelpers.uniffiRustCall( _status -> {
        UniffiLib.INSTANCE.uniffi_loro_fn_method_lorotree_enable_fractional_index(
            it, FfiConverterByte.INSTANCE.lower(jitter), _status);
    });
    
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    })
    ;
            } catch (RuntimeException _e) {
                
                
                if (InternalException.class.isInstance(_e.getCause())) {
                    throw (InternalException)_e.getCause();
                }
                throw _e;
            }
    }
    

  
    /**
     * Return the fractional index of the target node with hex format.
     */
    @Override
    public String fractionalIndex(TreeId target)  {
            try {
                return FfiConverterOptionalString.INSTANCE.lift(
    callWithPointer(it -> {
        try {
    
            return
    UniffiHelpers.uniffiRustCall( _status -> {
        return UniffiLib.INSTANCE.uniffi_loro_fn_method_lorotree_fractional_index(
            it, FfiConverterTypeTreeID.INSTANCE.lower(target), _status);
    });
    
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    })
    );
            } catch (RuntimeException _e) {
                
                
                if (InternalException.class.isInstance(_e.getCause())) {
                    throw (InternalException)_e.getCause();
                }
                throw _e;
            }
    }
    

  
    /**
     * If a detached container is attached, this method will return its corresponding attached handler.
     */
    @Override
    public LoroTree getAttached()  {
            try {
                return FfiConverterOptionalTypeLoroTree.INSTANCE.lift(
    callWithPointer(it -> {
        try {
    
            return
    UniffiHelpers.uniffiRustCall( _status -> {
        return UniffiLib.INSTANCE.uniffi_loro_fn_method_lorotree_get_attached(
            it, _status);
    });
    
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    })
    );
            } catch (RuntimeException _e) {
                
                
                if (InternalException.class.isInstance(_e.getCause())) {
                    throw (InternalException)_e.getCause();
                }
                throw _e;
            }
    }
    

  
    /**
     * Get the last move id of the target node.
     */
    @Override
    public Id getLastMoveId(TreeId target)  {
            try {
                return FfiConverterOptionalTypeID.INSTANCE.lift(
    callWithPointer(it -> {
        try {
    
            return
    UniffiHelpers.uniffiRustCall( _status -> {
        return UniffiLib.INSTANCE.uniffi_loro_fn_method_lorotree_get_last_move_id(
            it, FfiConverterTypeTreeID.INSTANCE.lower(target), _status);
    });
    
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    })
    );
            } catch (RuntimeException _e) {
                
                
                if (InternalException.class.isInstance(_e.getCause())) {
                    throw (InternalException)_e.getCause();
                }
                throw _e;
            }
    }
    

  
    /**
     * Get the associated metadata map handler of a tree node.
     */
    @Override
    public LoroMap getMeta(TreeId target) throws LoroException {
            try {
                return FfiConverterTypeLoroMap.INSTANCE.lift(
    callWithPointer(it -> {
        try {
    
            return
    UniffiHelpers.uniffiRustCallWithError(new LoroExceptionErrorHandler(), _status -> {
        return UniffiLib.INSTANCE.uniffi_loro_fn_method_lorotree_get_meta(
            it, FfiConverterTypeTreeID.INSTANCE.lower(target), _status);
    });
    
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    })
    );
            } catch (RuntimeException _e) {
                
                if (LoroException.class.isInstance(_e.getCause())) {
                    throw (LoroException)_e.getCause();
                }
                
                if (InternalException.class.isInstance(_e.getCause())) {
                    throw (InternalException)_e.getCause();
                }
                throw _e;
            }
    }
    

  
    /**
     * Return the flat array of the forest.
     *
     * Note: the metadata will be not resolved. So if you don't only care about hierarchy
     * but also the metadata, you should use `get_value_with_meta()`.
     */
    @Override
    public LoroValue getValue()  {
            try {
                return FfiConverterTypeLoroValue.INSTANCE.lift(
    callWithPointer(it -> {
        try {
    
            return
    UniffiHelpers.uniffiRustCall( _status -> {
        return UniffiLib.INSTANCE.uniffi_loro_fn_method_lorotree_get_value(
            it, _status);
    });
    
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    })
    );
            } catch (RuntimeException _e) {
                
                
                if (InternalException.class.isInstance(_e.getCause())) {
                    throw (InternalException)_e.getCause();
                }
                throw _e;
            }
    }
    

  
    /**
     * Return the flat array of the forest, each node is with metadata.
     */
    @Override
    public LoroValue getValueWithMeta()  {
            try {
                return FfiConverterTypeLoroValue.INSTANCE.lift(
    callWithPointer(it -> {
        try {
    
            return
    UniffiHelpers.uniffiRustCall( _status -> {
        return UniffiLib.INSTANCE.uniffi_loro_fn_method_lorotree_get_value_with_meta(
            it, _status);
    });
    
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    })
    );
            } catch (RuntimeException _e) {
                
                
                if (InternalException.class.isInstance(_e.getCause())) {
                    throw (InternalException)_e.getCause();
                }
                throw _e;
            }
    }
    

  
    /**
     * Return container id of the tree.
     */
    @Override
    public ContainerId id()  {
            try {
                return FfiConverterTypeContainerID.INSTANCE.lift(
    callWithPointer(it -> {
        try {
    
            return
    UniffiHelpers.uniffiRustCall( _status -> {
        return UniffiLib.INSTANCE.uniffi_loro_fn_method_lorotree_id(
            it, _status);
    });
    
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    })
    );
            } catch (RuntimeException _e) {
                
                
                if (InternalException.class.isInstance(_e.getCause())) {
                    throw (InternalException)_e.getCause();
                }
                throw _e;
            }
    }
    

  
    /**
     * Whether the container is attached to a document
     *
     * The edits on a detached container will not be persisted.
     * To attach the container to the document, please insert it into an attached container.
     */
    @Override
    public Boolean isAttached()  {
            try {
                return FfiConverterBoolean.INSTANCE.lift(
    callWithPointer(it -> {
        try {
    
            return
    UniffiHelpers.uniffiRustCall( _status -> {
        return UniffiLib.INSTANCE.uniffi_loro_fn_method_lorotree_is_attached(
            it, _status);
    });
    
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    })
    );
            } catch (RuntimeException _e) {
                
                
                if (InternalException.class.isInstance(_e.getCause())) {
                    throw (InternalException)_e.getCause();
                }
                throw _e;
            }
    }
    

  
    /**
     * Whether the container is deleted.
     */
    @Override
    public Boolean isDeleted()  {
            try {
                return FfiConverterBoolean.INSTANCE.lift(
    callWithPointer(it -> {
        try {
    
            return
    UniffiHelpers.uniffiRustCall( _status -> {
        return UniffiLib.INSTANCE.uniffi_loro_fn_method_lorotree_is_deleted(
            it, _status);
    });
    
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    })
    );
            } catch (RuntimeException _e) {
                
                
                if (InternalException.class.isInstance(_e.getCause())) {
                    throw (InternalException)_e.getCause();
                }
                throw _e;
            }
    }
    

  
    /**
     * Whether the fractional index is enabled.
     */
    @Override
    public Boolean isFractionalIndexEnabled()  {
            try {
                return FfiConverterBoolean.INSTANCE.lift(
    callWithPointer(it -> {
        try {
    
            return
    UniffiHelpers.uniffiRustCall( _status -> {
        return UniffiLib.INSTANCE.uniffi_loro_fn_method_lorotree_is_fractional_index_enabled(
            it, _status);
    });
    
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    })
    );
            } catch (RuntimeException _e) {
                
                
                if (InternalException.class.isInstance(_e.getCause())) {
                    throw (InternalException)_e.getCause();
                }
                throw _e;
            }
    }
    

  
    /**
     * Return whether target node is deleted.
     *
     * # Errors
     * - If the target node does not exist, return `LoroTreeError::TreeNodeNotExist`.
     */
    @Override
    public Boolean isNodeDeleted(TreeId target) throws LoroException {
            try {
                return FfiConverterBoolean.INSTANCE.lift(
    callWithPointer(it -> {
        try {
    
            return
    UniffiHelpers.uniffiRustCallWithError(new LoroExceptionErrorHandler(), _status -> {
        return UniffiLib.INSTANCE.uniffi_loro_fn_method_lorotree_is_node_deleted(
            it, FfiConverterTypeTreeID.INSTANCE.lower(target), _status);
    });
    
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    })
    );
            } catch (RuntimeException _e) {
                
                if (LoroException.class.isInstance(_e.getCause())) {
                    throw (LoroException)_e.getCause();
                }
                
                if (InternalException.class.isInstance(_e.getCause())) {
                    throw (InternalException)_e.getCause();
                }
                throw _e;
            }
    }
    

  
    /**
     * Move the `target` node to be a child of the `parent` node.
     *
     * If the `parent` is `None`, the `target` node will be a root.
     */
    @Override
    public void mov(TreeId target, TreeParentId parent) throws LoroException {
            try {
                
    callWithPointer(it -> {
        try {
    
    UniffiHelpers.uniffiRustCallWithError(new LoroExceptionErrorHandler(), _status -> {
        UniffiLib.INSTANCE.uniffi_loro_fn_method_lorotree_mov(
            it, FfiConverterTypeTreeID.INSTANCE.lower(target), FfiConverterTypeTreeParentId.INSTANCE.lower(parent), _status);
    });
    
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    })
    ;
            } catch (RuntimeException _e) {
                
                if (LoroException.class.isInstance(_e.getCause())) {
                    throw (LoroException)_e.getCause();
                }
                
                if (InternalException.class.isInstance(_e.getCause())) {
                    throw (InternalException)_e.getCause();
                }
                throw _e;
            }
    }
    

  
    /**
     * Move the `target` node to be a child after the `after` node with the same parent.
     */
    @Override
    public void movAfter(TreeId target, TreeId after) throws LoroException {
            try {
                
    callWithPointer(it -> {
        try {
    
    UniffiHelpers.uniffiRustCallWithError(new LoroExceptionErrorHandler(), _status -> {
        UniffiLib.INSTANCE.uniffi_loro_fn_method_lorotree_mov_after(
            it, FfiConverterTypeTreeID.INSTANCE.lower(target), FfiConverterTypeTreeID.INSTANCE.lower(after), _status);
    });
    
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    })
    ;
            } catch (RuntimeException _e) {
                
                if (LoroException.class.isInstance(_e.getCause())) {
                    throw (LoroException)_e.getCause();
                }
                
                if (InternalException.class.isInstance(_e.getCause())) {
                    throw (InternalException)_e.getCause();
                }
                throw _e;
            }
    }
    

  
    /**
     * Move the `target` node to be a child before the `before` node with the same parent.
     */
    @Override
    public void movBefore(TreeId target, TreeId before) throws LoroException {
            try {
                
    callWithPointer(it -> {
        try {
    
    UniffiHelpers.uniffiRustCallWithError(new LoroExceptionErrorHandler(), _status -> {
        UniffiLib.INSTANCE.uniffi_loro_fn_method_lorotree_mov_before(
            it, FfiConverterTypeTreeID.INSTANCE.lower(target), FfiConverterTypeTreeID.INSTANCE.lower(before), _status);
    });
    
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    })
    ;
            } catch (RuntimeException _e) {
                
                if (LoroException.class.isInstance(_e.getCause())) {
                    throw (LoroException)_e.getCause();
                }
                
                if (InternalException.class.isInstance(_e.getCause())) {
                    throw (InternalException)_e.getCause();
                }
                throw _e;
            }
    }
    

  
    /**
     * Move the `target` node to be a child of the `parent` node at the given index.
     * If the `parent` is `None`, the `target` node will be a root.
     */
    @Override
    public void movTo(TreeId target, TreeParentId parent, Integer to) throws LoroException {
            try {
                
    callWithPointer(it -> {
        try {
    
    UniffiHelpers.uniffiRustCallWithError(new LoroExceptionErrorHandler(), _status -> {
        UniffiLib.INSTANCE.uniffi_loro_fn_method_lorotree_mov_to(
            it, FfiConverterTypeTreeID.INSTANCE.lower(target), FfiConverterTypeTreeParentId.INSTANCE.lower(parent), FfiConverterInteger.INSTANCE.lower(to), _status);
    });
    
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    })
    ;
            } catch (RuntimeException _e) {
                
                if (LoroException.class.isInstance(_e.getCause())) {
                    throw (LoroException)_e.getCause();
                }
                
                if (InternalException.class.isInstance(_e.getCause())) {
                    throw (InternalException)_e.getCause();
                }
                throw _e;
            }
    }
    

  
    /**
     * Return all nodes, including deleted nodes
     */
    @Override
    public List<TreeId> nodes()  {
            try {
                return FfiConverterSequenceTypeTreeID.INSTANCE.lift(
    callWithPointer(it -> {
        try {
    
            return
    UniffiHelpers.uniffiRustCall( _status -> {
        return UniffiLib.INSTANCE.uniffi_loro_fn_method_lorotree_nodes(
            it, _status);
    });
    
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    })
    );
            } catch (RuntimeException _e) {
                
                
                if (InternalException.class.isInstance(_e.getCause())) {
                    throw (InternalException)_e.getCause();
                }
                throw _e;
            }
    }
    

  
    /**
     * Return the parent of target node.
     *
     * - If the target node does not exist, throws Error.
     * - If the target node is a root node, return nil.
     */
    @Override
    public TreeParentId parent(TreeId target) throws LoroException {
            try {
                return FfiConverterTypeTreeParentId.INSTANCE.lift(
    callWithPointer(it -> {
        try {
    
            return
    UniffiHelpers.uniffiRustCallWithError(new LoroExceptionErrorHandler(), _status -> {
        return UniffiLib.INSTANCE.uniffi_loro_fn_method_lorotree_parent(
            it, FfiConverterTypeTreeID.INSTANCE.lower(target), _status);
    });
    
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    })
    );
            } catch (RuntimeException _e) {
                
                if (LoroException.class.isInstance(_e.getCause())) {
                    throw (LoroException)_e.getCause();
                }
                
                if (InternalException.class.isInstance(_e.getCause())) {
                    throw (InternalException)_e.getCause();
                }
                throw _e;
            }
    }
    

  
    /**
     * Get the root nodes of the forest.
     */
    @Override
    public List<TreeId> roots()  {
            try {
                return FfiConverterSequenceTypeTreeID.INSTANCE.lift(
    callWithPointer(it -> {
        try {
    
            return
    UniffiHelpers.uniffiRustCall( _status -> {
        return UniffiLib.INSTANCE.uniffi_loro_fn_method_lorotree_roots(
            it, _status);
    });
    
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    })
    );
            } catch (RuntimeException _e) {
                
                
                if (InternalException.class.isInstance(_e.getCause())) {
                    throw (InternalException)_e.getCause();
                }
                throw _e;
            }
    }
    

  

  
}



