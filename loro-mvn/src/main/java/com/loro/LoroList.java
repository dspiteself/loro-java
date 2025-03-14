package com.loro;


import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Function;
import java.util.function.Consumer;
import com.sun.jna.Pointer;
import java.util.concurrent.CompletableFuture;
public class LoroList implements AutoCloseable, LoroListInterface {
  protected Pointer pointer;
  protected UniffiCleaner.Cleanable cleanable;

  private AtomicBoolean wasDestroyed = new AtomicBoolean(false);
  private AtomicLong callCounter = new AtomicLong(1);

  public LoroList(Pointer pointer) {
    this.pointer = pointer;
    this.cleanable = UniffiLib.CLEANER.register(this, new UniffiCleanAction(pointer));
  }

  /**
   * This constructor can be used to instantiate a fake object. Only used for tests. Any
   * attempt to actually use an object constructed this way will fail as there is no
   * connected Rust object.
   */
  public LoroList(NoPointer noPointer) {
    this.pointer = null;
    this.cleanable = UniffiLib.CLEANER.register(this, new UniffiCleanAction(pointer));
  }

  
    /**
     * Create a new container that is detached from the document.
     *
     * The edits on a detached container will not be persisted.
     * To attach the container to the document, please insert it into an attached container.
     */
  public LoroList() {
    this((Pointer)
    UniffiHelpers.uniffiRustCall( _status -> {
        return UniffiLib.INSTANCE.uniffi_loro_fn_constructor_lorolist_new(
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
        throw new IllegalStateException("LoroList object has already been destroyed");
      }
      if (c == Long.MAX_VALUE) {
        throw new IllegalStateException("LoroList call counter would overflow");
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
          UniffiLib.INSTANCE.uniffi_loro_fn_free_lorolist(pointer, status);
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
      return UniffiLib.INSTANCE.uniffi_loro_fn_clone_lorolist(pointer, status);
    });
  }

  
    /**
     * Delete all elements in the list.
     */
    @Override
    public void clear() throws LoroException {
            try {
                
    callWithPointer(it -> {
        try {
    
    UniffiHelpers.uniffiRustCallWithError(new LoroExceptionErrorHandler(), _status -> {
        UniffiLib.INSTANCE.uniffi_loro_fn_method_lorolist_clear(
            it, _status);
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
     * Delete values at the given position.
     */
    @Override
    public void delete(Integer pos, Integer len) throws LoroException {
            try {
                
    callWithPointer(it -> {
        try {
    
    UniffiHelpers.uniffiRustCallWithError(new LoroExceptionErrorHandler(), _status -> {
        UniffiLib.INSTANCE.uniffi_loro_fn_method_lorolist_delete(
            it, FfiConverterInteger.INSTANCE.lower(pos), FfiConverterInteger.INSTANCE.lower(len), _status);
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
        return UniffiLib.INSTANCE.uniffi_loro_fn_method_lorolist_doc(
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
     * Get the value at the given position.
     */
    @Override
    public ValueOrContainer get(Integer index)  {
            try {
                return FfiConverterOptionalTypeValueOrContainer.INSTANCE.lift(
    callWithPointer(it -> {
        try {
    
            return
    UniffiHelpers.uniffiRustCall( _status -> {
        return UniffiLib.INSTANCE.uniffi_loro_fn_method_lorolist_get(
            it, FfiConverterInteger.INSTANCE.lower(index), _status);
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
    public LoroList getAttached()  {
            try {
                return FfiConverterOptionalTypeLoroList.INSTANCE.lift(
    callWithPointer(it -> {
        try {
    
            return
    UniffiHelpers.uniffiRustCall( _status -> {
        return UniffiLib.INSTANCE.uniffi_loro_fn_method_lorolist_get_attached(
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
    

  
    @Override
    public Cursor getCursor(Integer pos, Side side)  {
            try {
                return FfiConverterOptionalTypeCursor.INSTANCE.lift(
    callWithPointer(it -> {
        try {
    
            return
    UniffiHelpers.uniffiRustCall( _status -> {
        return UniffiLib.INSTANCE.uniffi_loro_fn_method_lorolist_get_cursor(
            it, FfiConverterInteger.INSTANCE.lower(pos), FfiConverterTypeSide.INSTANCE.lower(side), _status);
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
     * Get the deep value of the container.
     */
    @Override
    public LoroValue getDeepValue()  {
            try {
                return FfiConverterTypeLoroValue.INSTANCE.lift(
    callWithPointer(it -> {
        try {
    
            return
    UniffiHelpers.uniffiRustCall( _status -> {
        return UniffiLib.INSTANCE.uniffi_loro_fn_method_lorolist_get_deep_value(
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
     * Get the ID of the list item at the given position.
     */
    @Override
    public Id getIdAt(Integer pos)  {
            try {
                return FfiConverterOptionalTypeID.INSTANCE.lift(
    callWithPointer(it -> {
        try {
    
            return
    UniffiHelpers.uniffiRustCall( _status -> {
        return UniffiLib.INSTANCE.uniffi_loro_fn_method_lorolist_get_id_at(
            it, FfiConverterInteger.INSTANCE.lower(pos), _status);
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
     * Get the shallow value of the container.
     *
     * This does not convert the state of sub-containers; instead, it represents them as [LoroValue::Container].
     */
    @Override
    public LoroValue getValue()  {
            try {
                return FfiConverterTypeLoroValue.INSTANCE.lift(
    callWithPointer(it -> {
        try {
    
            return
    UniffiHelpers.uniffiRustCall( _status -> {
        return UniffiLib.INSTANCE.uniffi_loro_fn_method_lorolist_get_value(
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
     * Get the ID of the container.
     */
    @Override
    public ContainerId id()  {
            try {
                return FfiConverterTypeContainerID.INSTANCE.lift(
    callWithPointer(it -> {
        try {
    
            return
    UniffiHelpers.uniffiRustCall( _status -> {
        return UniffiLib.INSTANCE.uniffi_loro_fn_method_lorolist_id(
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
     * Insert a value at the given position.
     */
    @Override
    public void insert(Integer pos, LoroValueLike v) throws LoroException {
            try {
                
    callWithPointer(it -> {
        try {
    
    UniffiHelpers.uniffiRustCallWithError(new LoroExceptionErrorHandler(), _status -> {
        UniffiLib.INSTANCE.uniffi_loro_fn_method_lorolist_insert(
            it, FfiConverterInteger.INSTANCE.lower(pos), FfiConverterTypeLoroValueLike.INSTANCE.lower(v), _status);
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
    

  
    @Override
    public LoroCounter insertCounterContainer(Integer pos, LoroCounter child) throws LoroException {
            try {
                return FfiConverterTypeLoroCounter.INSTANCE.lift(
    callWithPointer(it -> {
        try {
    
            return
    UniffiHelpers.uniffiRustCallWithError(new LoroExceptionErrorHandler(), _status -> {
        return UniffiLib.INSTANCE.uniffi_loro_fn_method_lorolist_insert_counter_container(
            it, FfiConverterInteger.INSTANCE.lower(pos), FfiConverterTypeLoroCounter.INSTANCE.lower(child), _status);
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
    

  
    @Override
    public LoroList insertListContainer(Integer pos, LoroList child) throws LoroException {
            try {
                return FfiConverterTypeLoroList.INSTANCE.lift(
    callWithPointer(it -> {
        try {
    
            return
    UniffiHelpers.uniffiRustCallWithError(new LoroExceptionErrorHandler(), _status -> {
        return UniffiLib.INSTANCE.uniffi_loro_fn_method_lorolist_insert_list_container(
            it, FfiConverterInteger.INSTANCE.lower(pos), FfiConverterTypeLoroList.INSTANCE.lower(child), _status);
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
    

  
    @Override
    public LoroMap insertMapContainer(Integer pos, LoroMap child) throws LoroException {
            try {
                return FfiConverterTypeLoroMap.INSTANCE.lift(
    callWithPointer(it -> {
        try {
    
            return
    UniffiHelpers.uniffiRustCallWithError(new LoroExceptionErrorHandler(), _status -> {
        return UniffiLib.INSTANCE.uniffi_loro_fn_method_lorolist_insert_map_container(
            it, FfiConverterInteger.INSTANCE.lower(pos), FfiConverterTypeLoroMap.INSTANCE.lower(child), _status);
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
    

  
    @Override
    public LoroMovableList insertMovableListContainer(Integer pos, LoroMovableList child) throws LoroException {
            try {
                return FfiConverterTypeLoroMovableList.INSTANCE.lift(
    callWithPointer(it -> {
        try {
    
            return
    UniffiHelpers.uniffiRustCallWithError(new LoroExceptionErrorHandler(), _status -> {
        return UniffiLib.INSTANCE.uniffi_loro_fn_method_lorolist_insert_movable_list_container(
            it, FfiConverterInteger.INSTANCE.lower(pos), FfiConverterTypeLoroMovableList.INSTANCE.lower(child), _status);
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
    

  
    @Override
    public LoroText insertTextContainer(Integer pos, LoroText child) throws LoroException {
            try {
                return FfiConverterTypeLoroText.INSTANCE.lift(
    callWithPointer(it -> {
        try {
    
            return
    UniffiHelpers.uniffiRustCallWithError(new LoroExceptionErrorHandler(), _status -> {
        return UniffiLib.INSTANCE.uniffi_loro_fn_method_lorolist_insert_text_container(
            it, FfiConverterInteger.INSTANCE.lower(pos), FfiConverterTypeLoroText.INSTANCE.lower(child), _status);
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
    

  
    @Override
    public LoroTree insertTreeContainer(Integer pos, LoroTree child) throws LoroException {
            try {
                return FfiConverterTypeLoroTree.INSTANCE.lift(
    callWithPointer(it -> {
        try {
    
            return
    UniffiHelpers.uniffiRustCallWithError(new LoroExceptionErrorHandler(), _status -> {
        return UniffiLib.INSTANCE.uniffi_loro_fn_method_lorolist_insert_tree_container(
            it, FfiConverterInteger.INSTANCE.lower(pos), FfiConverterTypeLoroTree.INSTANCE.lower(child), _status);
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
        return UniffiLib.INSTANCE.uniffi_loro_fn_method_lorolist_is_attached(
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
        return UniffiLib.INSTANCE.uniffi_loro_fn_method_lorolist_is_deleted(
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
    

  
    @Override
    public Boolean isEmpty()  {
            try {
                return FfiConverterBoolean.INSTANCE.lift(
    callWithPointer(it -> {
        try {
    
            return
    UniffiHelpers.uniffiRustCall( _status -> {
        return UniffiLib.INSTANCE.uniffi_loro_fn_method_lorolist_is_empty(
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
    

  
    @Override
    public Integer len()  {
            try {
                return FfiConverterInteger.INSTANCE.lift(
    callWithPointer(it -> {
        try {
    
            return
    UniffiHelpers.uniffiRustCall( _status -> {
        return UniffiLib.INSTANCE.uniffi_loro_fn_method_lorolist_len(
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
     * Pop the last element of the list.
     */
    @Override
    public LoroValue pop() throws LoroException {
            try {
                return FfiConverterOptionalTypeLoroValue.INSTANCE.lift(
    callWithPointer(it -> {
        try {
    
            return
    UniffiHelpers.uniffiRustCallWithError(new LoroExceptionErrorHandler(), _status -> {
        return UniffiLib.INSTANCE.uniffi_loro_fn_method_lorolist_pop(
            it, _status);
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
    

  
    @Override
    public void push(LoroValueLike v) throws LoroException {
            try {
                
    callWithPointer(it -> {
        try {
    
    UniffiHelpers.uniffiRustCallWithError(new LoroExceptionErrorHandler(), _status -> {
        UniffiLib.INSTANCE.uniffi_loro_fn_method_lorolist_push(
            it, FfiConverterTypeLoroValueLike.INSTANCE.lower(v), _status);
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
     * Converts the LoroList to a Vec of LoroValue.
     *
     * This method unwraps the internal Arc and clones the data if necessary,
     * returning a Vec containing all the elements of the LoroList as LoroValue.
     */
    @Override
    public List<LoroValue> toVec()  {
            try {
                return FfiConverterSequenceTypeLoroValue.INSTANCE.lift(
    callWithPointer(it -> {
        try {
    
            return
    UniffiHelpers.uniffiRustCall( _status -> {
        return UniffiLib.INSTANCE.uniffi_loro_fn_method_lorolist_to_vec(
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



