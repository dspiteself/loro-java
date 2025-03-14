package com.loro;


import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Function;
import java.util.function.Consumer;
import com.sun.jna.Pointer;
import java.util.concurrent.CompletableFuture;
public class LoroText implements AutoCloseable, LoroTextInterface {
  protected Pointer pointer;
  protected UniffiCleaner.Cleanable cleanable;

  private AtomicBoolean wasDestroyed = new AtomicBoolean(false);
  private AtomicLong callCounter = new AtomicLong(1);

  public LoroText(Pointer pointer) {
    this.pointer = pointer;
    this.cleanable = UniffiLib.CLEANER.register(this, new UniffiCleanAction(pointer));
  }

  /**
   * This constructor can be used to instantiate a fake object. Only used for tests. Any
   * attempt to actually use an object constructed this way will fail as there is no
   * connected Rust object.
   */
  public LoroText(NoPointer noPointer) {
    this.pointer = null;
    this.cleanable = UniffiLib.CLEANER.register(this, new UniffiCleanAction(pointer));
  }

  
    /**
     * Create a new container that is detached from the document.
     *
     * The edits on a detached container will not be persisted.
     * To attach the container to the document, please insert it into an attached container.
     */
  public LoroText() {
    this((Pointer)
    UniffiHelpers.uniffiRustCall( _status -> {
        return UniffiLib.INSTANCE.uniffi_loro_fn_constructor_lorotext_new(
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
        throw new IllegalStateException("LoroText object has already been destroyed");
      }
      if (c == Long.MAX_VALUE) {
        throw new IllegalStateException("LoroText call counter would overflow");
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
          UniffiLib.INSTANCE.uniffi_loro_fn_free_lorotext(pointer, status);
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
      return UniffiLib.INSTANCE.uniffi_loro_fn_clone_lorotext(pointer, status);
    });
  }

  
    /**
     * Apply a [delta](https://quilljs.com/docs/delta/) to the text container.
     */
    @Override
    public void applyDelta(List<TextDelta> delta) throws LoroException {
            try {
                
    callWithPointer(it -> {
        try {
    
    UniffiHelpers.uniffiRustCallWithError(new LoroExceptionErrorHandler(), _status -> {
        UniffiLib.INSTANCE.uniffi_loro_fn_method_lorotext_apply_delta(
            it, FfiConverterSequenceTypeTextDelta.INSTANCE.lower(delta), _status);
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
     * Delete a range of text at the given unicode position with unicode length.
     */
    @Override
    public void delete(Integer pos, Integer len) throws LoroException {
            try {
                
    callWithPointer(it -> {
        try {
    
    UniffiHelpers.uniffiRustCallWithError(new LoroExceptionErrorHandler(), _status -> {
        UniffiLib.INSTANCE.uniffi_loro_fn_method_lorotext_delete(
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
     * Delete a range of text at the given utf-8 position with utf-8 length.
     */
    @Override
    public void deleteUtf8(Integer pos, Integer len) throws LoroException {
            try {
                
    callWithPointer(it -> {
        try {
    
    UniffiHelpers.uniffiRustCallWithError(new LoroExceptionErrorHandler(), _status -> {
        UniffiLib.INSTANCE.uniffi_loro_fn_method_lorotext_delete_utf8(
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
        return UniffiLib.INSTANCE.uniffi_loro_fn_method_lorotext_doc(
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
     * If a detached container is attached, this method will return its corresponding attached handler.
     */
    @Override
    public LoroText getAttached()  {
            try {
                return FfiConverterOptionalTypeLoroText.INSTANCE.lift(
    callWithPointer(it -> {
        try {
    
            return
    UniffiHelpers.uniffiRustCall( _status -> {
        return UniffiLib.INSTANCE.uniffi_loro_fn_method_lorotext_get_attached(
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
    @Override
    public Cursor getCursor(Integer pos, Side side)  {
            try {
                return FfiConverterOptionalTypeCursor.INSTANCE.lift(
    callWithPointer(it -> {
        try {
    
            return
    UniffiHelpers.uniffiRustCall( _status -> {
        return UniffiLib.INSTANCE.uniffi_loro_fn_method_lorotext_get_cursor(
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
     * Get the editor of the text at the given position.
     */
    @Override
    public Long getEditorAtUnicodePos(Integer pos)  {
            try {
                return FfiConverterOptionalLong.INSTANCE.lift(
    callWithPointer(it -> {
        try {
    
            return
    UniffiHelpers.uniffiRustCall( _status -> {
        return UniffiLib.INSTANCE.uniffi_loro_fn_method_lorotext_get_editor_at_unicode_pos(
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
     * Get the text in [Delta](https://quilljs.com/docs/delta/) format.
     */
    @Override
    public LoroValue getRichtextValue()  {
            try {
                return FfiConverterTypeLoroValue.INSTANCE.lift(
    callWithPointer(it -> {
        try {
    
            return
    UniffiHelpers.uniffiRustCall( _status -> {
        return UniffiLib.INSTANCE.uniffi_loro_fn_method_lorotext_get_richtext_value(
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
     * Get the [ContainerID]  of the text container.
     */
    @Override
    public ContainerId id()  {
            try {
                return FfiConverterTypeContainerID.INSTANCE.lift(
    callWithPointer(it -> {
        try {
    
            return
    UniffiHelpers.uniffiRustCall( _status -> {
        return UniffiLib.INSTANCE.uniffi_loro_fn_method_lorotext_id(
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
     * Insert a string at the given unicode position.
     */
    @Override
    public void insert(Integer pos, String s) throws LoroException {
            try {
                
    callWithPointer(it -> {
        try {
    
    UniffiHelpers.uniffiRustCallWithError(new LoroExceptionErrorHandler(), _status -> {
        UniffiLib.INSTANCE.uniffi_loro_fn_method_lorotext_insert(
            it, FfiConverterInteger.INSTANCE.lower(pos), FfiConverterString.INSTANCE.lower(s), _status);
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
     * Insert a string at the given utf-8 position.
     */
    @Override
    public void insertUtf8(Integer pos, String s) throws LoroException {
            try {
                
    callWithPointer(it -> {
        try {
    
    UniffiHelpers.uniffiRustCallWithError(new LoroExceptionErrorHandler(), _status -> {
        UniffiLib.INSTANCE.uniffi_loro_fn_method_lorotext_insert_utf8(
            it, FfiConverterInteger.INSTANCE.lower(pos), FfiConverterString.INSTANCE.lower(s), _status);
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
        return UniffiLib.INSTANCE.uniffi_loro_fn_method_lorotext_is_attached(
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
        return UniffiLib.INSTANCE.uniffi_loro_fn_method_lorotext_is_deleted(
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
     * Whether the text container is empty.
     */
    @Override
    public Boolean isEmpty()  {
            try {
                return FfiConverterBoolean.INSTANCE.lift(
    callWithPointer(it -> {
        try {
    
            return
    UniffiHelpers.uniffiRustCall( _status -> {
        return UniffiLib.INSTANCE.uniffi_loro_fn_method_lorotext_is_empty(
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
     * Get the length of the text container in Unicode.
     */
    @Override
    public Integer lenUnicode()  {
            try {
                return FfiConverterInteger.INSTANCE.lift(
    callWithPointer(it -> {
        try {
    
            return
    UniffiHelpers.uniffiRustCall( _status -> {
        return UniffiLib.INSTANCE.uniffi_loro_fn_method_lorotext_len_unicode(
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
     * Get the length of the text container in UTF-16.
     */
    @Override
    public Integer lenUtf16()  {
            try {
                return FfiConverterInteger.INSTANCE.lift(
    callWithPointer(it -> {
        try {
    
            return
    UniffiHelpers.uniffiRustCall( _status -> {
        return UniffiLib.INSTANCE.uniffi_loro_fn_method_lorotext_len_utf16(
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
     * Get the length of the text container in UTF-8.
     */
    @Override
    public Integer lenUtf8()  {
            try {
                return FfiConverterInteger.INSTANCE.lift(
    callWithPointer(it -> {
        try {
    
            return
    UniffiHelpers.uniffiRustCall( _status -> {
        return UniffiLib.INSTANCE.uniffi_loro_fn_method_lorotext_len_utf8(
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
    @Override
    public void mark(Integer from, Integer to, String key, LoroValueLike value) throws LoroException {
            try {
                
    callWithPointer(it -> {
        try {
    
    UniffiHelpers.uniffiRustCallWithError(new LoroExceptionErrorHandler(), _status -> {
        UniffiLib.INSTANCE.uniffi_loro_fn_method_lorotext_mark(
            it, FfiConverterInteger.INSTANCE.lower(from), FfiConverterInteger.INSTANCE.lower(to), FfiConverterString.INSTANCE.lower(key), FfiConverterTypeLoroValueLike.INSTANCE.lower(value), _status);
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
     * Push a string to the end of the text container.
     */
    @Override
    public void pushStr(String s) throws LoroException {
            try {
                
    callWithPointer(it -> {
        try {
    
    UniffiHelpers.uniffiRustCallWithError(new LoroExceptionErrorHandler(), _status -> {
        UniffiLib.INSTANCE.uniffi_loro_fn_method_lorotext_push_str(
            it, FfiConverterString.INSTANCE.lower(s), _status);
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
     * Get a string slice at the given Unicode range
     */
    @Override
    public String slice(Integer startIndex, Integer endIndex) throws LoroException {
            try {
                return FfiConverterString.INSTANCE.lift(
    callWithPointer(it -> {
        try {
    
            return
    UniffiHelpers.uniffiRustCallWithError(new LoroExceptionErrorHandler(), _status -> {
        return UniffiLib.INSTANCE.uniffi_loro_fn_method_lorotext_slice(
            it, FfiConverterInteger.INSTANCE.lower(startIndex), FfiConverterInteger.INSTANCE.lower(endIndex), _status);
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
     * Delete specified character and insert string at the same position at given unicode position.
     */
    @Override
    public String splice(Integer pos, Integer len, String s) throws LoroException {
            try {
                return FfiConverterString.INSTANCE.lift(
    callWithPointer(it -> {
        try {
    
            return
    UniffiHelpers.uniffiRustCallWithError(new LoroExceptionErrorHandler(), _status -> {
        return UniffiLib.INSTANCE.uniffi_loro_fn_method_lorotext_splice(
            it, FfiConverterInteger.INSTANCE.lower(pos), FfiConverterInteger.INSTANCE.lower(len), FfiConverterString.INSTANCE.lower(s), _status);
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
     * Get the text in [Delta](https://quilljs.com/docs/delta/) format.
     */
    @Override
    public List<TextDelta> toDelta()  {
            try {
                return FfiConverterSequenceTypeTextDelta.INSTANCE.lift(
    callWithPointer(it -> {
        try {
    
            return
    UniffiHelpers.uniffiRustCall( _status -> {
        return UniffiLib.INSTANCE.uniffi_loro_fn_method_lorotext_to_delta(
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
     * Get the text content of the text container.
     */
    @Override
    public String toString()  {
            try {
                return FfiConverterString.INSTANCE.lift(
    callWithPointer(it -> {
        try {
    
            return
    UniffiHelpers.uniffiRustCall( _status -> {
        return UniffiLib.INSTANCE.uniffi_loro_fn_method_lorotext_to_string(
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
    @Override
    public void unmark(Integer from, Integer to, String key) throws LoroException {
            try {
                
    callWithPointer(it -> {
        try {
    
    UniffiHelpers.uniffiRustCallWithError(new LoroExceptionErrorHandler(), _status -> {
        UniffiLib.INSTANCE.uniffi_loro_fn_method_lorotext_unmark(
            it, FfiConverterInteger.INSTANCE.lower(from), FfiConverterInteger.INSTANCE.lower(to), FfiConverterString.INSTANCE.lower(key), _status);
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
     * Update the current text based on the provided text.
     *
     * It will calculate the minimal difference and apply it to the current text.
     * It uses Myers' diff algorithm to compute the optimal difference.
     *
     * This could take a long time for large texts (e.g. > 50_000 characters).
     * In that case, you should use `updateByLine` instead.
     */
    @Override
    public void update(String s, UpdateOptions options) throws UpdateTimeoutException {
            try {
                
    callWithPointer(it -> {
        try {
    
    UniffiHelpers.uniffiRustCallWithError(new UpdateTimeoutExceptionErrorHandler(), _status -> {
        UniffiLib.INSTANCE.uniffi_loro_fn_method_lorotext_update(
            it, FfiConverterString.INSTANCE.lower(s), FfiConverterTypeUpdateOptions.INSTANCE.lower(options), _status);
    });
    
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    })
    ;
            } catch (RuntimeException _e) {
                
                if (UpdateTimeoutException.class.isInstance(_e.getCause())) {
                    throw (UpdateTimeoutException)_e.getCause();
                }
                
                if (InternalException.class.isInstance(_e.getCause())) {
                    throw (InternalException)_e.getCause();
                }
                throw _e;
            }
    }
    

  
    /**
     * Update the current text based on the provided text.
     *
     * This update calculation is line-based, which will be more efficient but less precise.
     */
    @Override
    public void updateByLine(String s, UpdateOptions options) throws UpdateTimeoutException {
            try {
                
    callWithPointer(it -> {
        try {
    
    UniffiHelpers.uniffiRustCallWithError(new UpdateTimeoutExceptionErrorHandler(), _status -> {
        UniffiLib.INSTANCE.uniffi_loro_fn_method_lorotext_update_by_line(
            it, FfiConverterString.INSTANCE.lower(s), FfiConverterTypeUpdateOptions.INSTANCE.lower(options), _status);
    });
    
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    })
    ;
            } catch (RuntimeException _e) {
                
                if (UpdateTimeoutException.class.isInstance(_e.getCause())) {
                    throw (UpdateTimeoutException)_e.getCause();
                }
                
                if (InternalException.class.isInstance(_e.getCause())) {
                    throw (InternalException)_e.getCause();
                }
                throw _e;
            }
    }
    

  

  
}



