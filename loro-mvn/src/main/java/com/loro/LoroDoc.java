package com.loro;


import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Function;
import java.util.function.Consumer;
import com.sun.jna.Pointer;
import java.util.concurrent.CompletableFuture;
/**
 * `LoroDoc` is the entry for the whole document.
 * When it's dropped, all the associated [`Handler`]s will be invalidated.
 *
 * **Important:** Loro is a pure library and does not handle network protocols.
 * It is the responsibility of the user to manage the storage, loading, and synchronization
 * of the bytes exported by Loro in a manner suitable for their specific environment.
 */
public class LoroDoc implements AutoCloseable, LoroDocInterface {
  protected Pointer pointer;
  protected UniffiCleaner.Cleanable cleanable;

  private AtomicBoolean wasDestroyed = new AtomicBoolean(false);
  private AtomicLong callCounter = new AtomicLong(1);

  public LoroDoc(Pointer pointer) {
    this.pointer = pointer;
    this.cleanable = UniffiLib.CLEANER.register(this, new UniffiCleanAction(pointer));
  }

  /**
   * This constructor can be used to instantiate a fake object. Only used for tests. Any
   * attempt to actually use an object constructed this way will fail as there is no
   * connected Rust object.
   */
  public LoroDoc(NoPointer noPointer) {
    this.pointer = null;
    this.cleanable = UniffiLib.CLEANER.register(this, new UniffiCleanAction(pointer));
  }

  
    /**
     * Create a new `LoroDoc` instance.
     */
  public LoroDoc() {
    this((Pointer)
    UniffiHelpers.uniffiRustCall( _status -> {
        return UniffiLib.INSTANCE.uniffi_loro_fn_constructor_lorodoc_new(
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
        throw new IllegalStateException("LoroDoc object has already been destroyed");
      }
      if (c == Long.MAX_VALUE) {
        throw new IllegalStateException("LoroDoc call counter would overflow");
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
          UniffiLib.INSTANCE.uniffi_loro_fn_free_lorodoc(pointer, status);
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
      return UniffiLib.INSTANCE.uniffi_loro_fn_clone_lorodoc(pointer, status);
    });
  }

  
    /**
     * Apply a diff to the current document state.
     *
     * Internally, it will apply the diff to the current state.
     */
    @Override
    public void applyDiff(DiffBatch diff) throws LoroException {
            try {
                
    callWithPointer(it -> {
        try {
    
    UniffiHelpers.uniffiRustCallWithError(new LoroExceptionErrorHandler(), _status -> {
        UniffiLib.INSTANCE.uniffi_loro_fn_method_lorodoc_apply_diff(
            it, FfiConverterTypeDiffBatch.INSTANCE.lower(diff), _status);
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
     * Attach the document state to the latest known version.
     *
     * > The document becomes detached during a `checkout` operation.
     * > Being `detached` implies that the `DocState` is not synchronized with the latest version of the `OpLog`.
     * > In a detached state, the document is not editable, and any `import` operations will be
     * > recorded in the `OpLog` without being applied to the `DocState`.
     */
    @Override
    public void attach()  {
            try {
                
    callWithPointer(it -> {
        try {
    
    UniffiHelpers.uniffiRustCall( _status -> {
        UniffiLib.INSTANCE.uniffi_loro_fn_method_lorodoc_attach(
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
     * Check the correctness of the document state by comparing it with the state
     * calculated by applying all the history.
     */
    @Override
    public void checkStateCorrectnessSlow()  {
            try {
                
    callWithPointer(it -> {
        try {
    
    UniffiHelpers.uniffiRustCall( _status -> {
        UniffiLib.INSTANCE.uniffi_loro_fn_method_lorodoc_check_state_correctness_slow(
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
     * Checkout the `DocState` to a specific version.
     *
     * The document becomes detached during a `checkout` operation.
     * Being `detached` implies that the `DocState` is not synchronized with the latest version of the `OpLog`.
     * In a detached state, the document is not editable, and any `import` operations will be
     * recorded in the `OpLog` without being applied to the `DocState`.
     *
     * You should call `attach` to attach the `DocState` to the latest version of `OpLog`.
     */
    @Override
    public void checkout(Frontiers frontiers) throws LoroException {
            try {
                
    callWithPointer(it -> {
        try {
    
    UniffiHelpers.uniffiRustCallWithError(new LoroExceptionErrorHandler(), _status -> {
        UniffiLib.INSTANCE.uniffi_loro_fn_method_lorodoc_checkout(
            it, FfiConverterTypeFrontiers.INSTANCE.lower(frontiers), _status);
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
     * Checkout the `DocState` to the latest version.
     *
     * > The document becomes detached during a `checkout` operation.
     * > Being `detached` implies that the `DocState` is not synchronized with the latest version of the `OpLog`.
     * > In a detached state, the document is not editable, and any `import` operations will be
     * > recorded in the `OpLog` without being applied to the `DocState`.
     *
     * This has the same effect as `attach`.
     */
    @Override
    public void checkoutToLatest()  {
            try {
                
    callWithPointer(it -> {
        try {
    
    UniffiHelpers.uniffiRustCall( _status -> {
        UniffiLib.INSTANCE.uniffi_loro_fn_method_lorodoc_checkout_to_latest(
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
     * Clear the options of the next commit.
     */
    @Override
    public void clearNextCommitOptions()  {
            try {
                
    callWithPointer(it -> {
        try {
    
    UniffiHelpers.uniffiRustCall( _status -> {
        UniffiLib.INSTANCE.uniffi_loro_fn_method_lorodoc_clear_next_commit_options(
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
     * Compare the frontiers with the current OpLog's version.
     *
     * If `other` contains any version that's not contained in the current OpLog, return [Ordering::Less].
     */
    @Override
    public Ordering cmpWithFrontiers(Frontiers other)  {
            try {
                return FfiConverterTypeOrdering.INSTANCE.lift(
    callWithPointer(it -> {
        try {
    
            return
    UniffiHelpers.uniffiRustCall( _status -> {
        return UniffiLib.INSTANCE.uniffi_loro_fn_method_lorodoc_cmp_with_frontiers(
            it, FfiConverterTypeFrontiers.INSTANCE.lower(other), _status);
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
    @Override
    public void commit()  {
            try {
                
    callWithPointer(it -> {
        try {
    
    UniffiHelpers.uniffiRustCall( _status -> {
        UniffiLib.INSTANCE.uniffi_loro_fn_method_lorodoc_commit(
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
    

  
    @Override
    public void commitWith(CommitOptions options)  {
            try {
                
    callWithPointer(it -> {
        try {
    
    UniffiHelpers.uniffiRustCall( _status -> {
        UniffiLib.INSTANCE.uniffi_loro_fn_method_lorodoc_commit_with(
            it, FfiConverterTypeCommitOptions.INSTANCE.lower(options), _status);
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
     * Encoded all ops and history cache to bytes and store them in the kv store.
     *
     * The parsed ops will be dropped
     */
    @Override
    public void compactChangeStore()  {
            try {
                
    callWithPointer(it -> {
        try {
    
    UniffiHelpers.uniffiRustCall( _status -> {
        UniffiLib.INSTANCE.uniffi_loro_fn_method_lorodoc_compact_change_store(
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
     * Get the configurations of the document.
     */
    @Override
    public Configure config()  {
            try {
                return FfiConverterTypeConfigure.INSTANCE.lift(
    callWithPointer(it -> {
        try {
    
            return
    UniffiHelpers.uniffiRustCall( _status -> {
        return UniffiLib.INSTANCE.uniffi_loro_fn_method_lorodoc_config(
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
     * Configures the default text style for the document.
     *
     * This method sets the default text style configuration for the document when using LoroText.
     * If `None` is provided, the default style is reset.
     *
     * # Parameters
     *
     * - `text_style`: The style configuration to set as the default. `None` to reset.
     */
    @Override
    public void configDefaultTextStyle(StyleConfig textStyle)  {
            try {
                
    callWithPointer(it -> {
        try {
    
    UniffiHelpers.uniffiRustCall( _status -> {
        UniffiLib.INSTANCE.uniffi_loro_fn_method_lorodoc_config_default_text_style(
            it, FfiConverterOptionalTypeStyleConfig.INSTANCE.lower(textStyle), _status);
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
     * Set the rich text format configuration of the document.
     *
     * You need to config it if you use rich text `mark` method.
     * Specifically, you need to config the `expand` property of each style.
     *
     * Expand is used to specify the behavior of expanding when new text is inserted at the
     * beginning or end of the style.
     */
    @Override
    public void configTextStyle(StyleConfigMap textStyle)  {
            try {
                
    callWithPointer(it -> {
        try {
    
    UniffiHelpers.uniffiRustCall( _status -> {
        UniffiLib.INSTANCE.uniffi_loro_fn_method_lorodoc_config_text_style(
            it, FfiConverterTypeStyleConfigMap.INSTANCE.lower(textStyle), _status);
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
     * Force the document enter the detached mode.
     *
     * In this mode, when you importing new updates, the [loro_internal::DocState] will not be changed.
     *
     * Learn more at https://loro.dev/docs/advanced/doc_state_and_oplog#attacheddetached-status
     */
    @Override
    public void detach()  {
            try {
                
    callWithPointer(it -> {
        try {
    
    UniffiHelpers.uniffiRustCall( _status -> {
        UniffiLib.INSTANCE.uniffi_loro_fn_method_lorodoc_detach(
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
     * Calculate the diff between two versions
     */
    @Override
    public DiffBatch diff(Frontiers a, Frontiers b) throws LoroException {
            try {
                return FfiConverterTypeDiffBatch.INSTANCE.lift(
    callWithPointer(it -> {
        try {
    
            return
    UniffiHelpers.uniffiRustCallWithError(new LoroExceptionErrorHandler(), _status -> {
        return UniffiLib.INSTANCE.uniffi_loro_fn_method_lorodoc_diff(
            it, FfiConverterTypeFrontiers.INSTANCE.lower(a), FfiConverterTypeFrontiers.INSTANCE.lower(b), _status);
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
     * Export the readable [`Change`]s in the given [`IdSpan`]
     */
    @Override
    public List<String> exportJsonInIdSpan(IdSpan idSpan)  {
            try {
                return FfiConverterSequenceString.INSTANCE.lift(
    callWithPointer(it -> {
        try {
    
            return
    UniffiHelpers.uniffiRustCall( _status -> {
        return UniffiLib.INSTANCE.uniffi_loro_fn_method_lorodoc_export_json_in_id_span(
            it, FfiConverterTypeIdSpan.INSTANCE.lower(idSpan), _status);
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
     * Export the current state with json-string format of the document.
     */
    @Override
    public String exportJsonUpdates(VersionVector startVv, VersionVector endVv)  {
            try {
                return FfiConverterString.INSTANCE.lift(
    callWithPointer(it -> {
        try {
    
            return
    UniffiHelpers.uniffiRustCall( _status -> {
        return UniffiLib.INSTANCE.uniffi_loro_fn_method_lorodoc_export_json_updates(
            it, FfiConverterTypeVersionVector.INSTANCE.lower(startVv), FfiConverterTypeVersionVector.INSTANCE.lower(endVv), _status);
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
     * Export the current state with json-string format of the document, without peer compression.
     *
     * Compared to [`export_json_updates`], this method does not compress the peer IDs in the updates.
     * So the operations are easier to be processed by application code.
     */
    @Override
    public String exportJsonUpdatesWithoutPeerCompression(VersionVector startVv, VersionVector endVv)  {
            try {
                return FfiConverterString.INSTANCE.lift(
    callWithPointer(it -> {
        try {
    
            return
    UniffiHelpers.uniffiRustCall( _status -> {
        return UniffiLib.INSTANCE.uniffi_loro_fn_method_lorodoc_export_json_updates_without_peer_compression(
            it, FfiConverterTypeVersionVector.INSTANCE.lower(startVv), FfiConverterTypeVersionVector.INSTANCE.lower(endVv), _status);
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
    public byte[] exportShallowSnapshot(Frontiers frontiers) throws LoroEncodeException {
            try {
                return FfiConverterByteArray.INSTANCE.lift(
    callWithPointer(it -> {
        try {
    
            return
    UniffiHelpers.uniffiRustCallWithError(new LoroEncodeExceptionErrorHandler(), _status -> {
        return UniffiLib.INSTANCE.uniffi_loro_fn_method_lorodoc_export_shallow_snapshot(
            it, FfiConverterTypeFrontiers.INSTANCE.lower(frontiers), _status);
    });
    
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    })
    );
            } catch (RuntimeException _e) {
                
                if (LoroEncodeException.class.isInstance(_e.getCause())) {
                    throw (LoroEncodeException)_e.getCause();
                }
                
                if (InternalException.class.isInstance(_e.getCause())) {
                    throw (InternalException)_e.getCause();
                }
                throw _e;
            }
    }
    

  
    /**
     * Export the current state and history of the document.
     */
    @Override
    public byte[] exportSnapshot() throws LoroEncodeException {
            try {
                return FfiConverterByteArray.INSTANCE.lift(
    callWithPointer(it -> {
        try {
    
            return
    UniffiHelpers.uniffiRustCallWithError(new LoroEncodeExceptionErrorHandler(), _status -> {
        return UniffiLib.INSTANCE.uniffi_loro_fn_method_lorodoc_export_snapshot(
            it, _status);
    });
    
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    })
    );
            } catch (RuntimeException _e) {
                
                if (LoroEncodeException.class.isInstance(_e.getCause())) {
                    throw (LoroEncodeException)_e.getCause();
                }
                
                if (InternalException.class.isInstance(_e.getCause())) {
                    throw (InternalException)_e.getCause();
                }
                throw _e;
            }
    }
    

  
    @Override
    public byte[] exportSnapshotAt(Frontiers frontiers) throws LoroEncodeException {
            try {
                return FfiConverterByteArray.INSTANCE.lift(
    callWithPointer(it -> {
        try {
    
            return
    UniffiHelpers.uniffiRustCallWithError(new LoroEncodeExceptionErrorHandler(), _status -> {
        return UniffiLib.INSTANCE.uniffi_loro_fn_method_lorodoc_export_snapshot_at(
            it, FfiConverterTypeFrontiers.INSTANCE.lower(frontiers), _status);
    });
    
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    })
    );
            } catch (RuntimeException _e) {
                
                if (LoroEncodeException.class.isInstance(_e.getCause())) {
                    throw (LoroEncodeException)_e.getCause();
                }
                
                if (InternalException.class.isInstance(_e.getCause())) {
                    throw (InternalException)_e.getCause();
                }
                throw _e;
            }
    }
    

  
    @Override
    public byte[] exportStateOnly(Frontiers frontiers) throws LoroEncodeException {
            try {
                return FfiConverterByteArray.INSTANCE.lift(
    callWithPointer(it -> {
        try {
    
            return
    UniffiHelpers.uniffiRustCallWithError(new LoroEncodeExceptionErrorHandler(), _status -> {
        return UniffiLib.INSTANCE.uniffi_loro_fn_method_lorodoc_export_state_only(
            it, FfiConverterOptionalTypeFrontiers.INSTANCE.lower(frontiers), _status);
    });
    
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    })
    );
            } catch (RuntimeException _e) {
                
                if (LoroEncodeException.class.isInstance(_e.getCause())) {
                    throw (LoroEncodeException)_e.getCause();
                }
                
                if (InternalException.class.isInstance(_e.getCause())) {
                    throw (InternalException)_e.getCause();
                }
                throw _e;
            }
    }
    

  
    /**
     * Export all the ops not included in the given `VersionVector`
     */
    @Override
    public byte[] exportUpdates(VersionVector vv) throws LoroEncodeException {
            try {
                return FfiConverterByteArray.INSTANCE.lift(
    callWithPointer(it -> {
        try {
    
            return
    UniffiHelpers.uniffiRustCallWithError(new LoroEncodeExceptionErrorHandler(), _status -> {
        return UniffiLib.INSTANCE.uniffi_loro_fn_method_lorodoc_export_updates(
            it, FfiConverterTypeVersionVector.INSTANCE.lower(vv), _status);
    });
    
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    })
    );
            } catch (RuntimeException _e) {
                
                if (LoroEncodeException.class.isInstance(_e.getCause())) {
                    throw (LoroEncodeException)_e.getCause();
                }
                
                if (InternalException.class.isInstance(_e.getCause())) {
                    throw (InternalException)_e.getCause();
                }
                throw _e;
            }
    }
    

  
    @Override
    public byte[] exportUpdatesInRange(List<IdSpan> spans) throws LoroEncodeException {
            try {
                return FfiConverterByteArray.INSTANCE.lift(
    callWithPointer(it -> {
        try {
    
            return
    UniffiHelpers.uniffiRustCallWithError(new LoroEncodeExceptionErrorHandler(), _status -> {
        return UniffiLib.INSTANCE.uniffi_loro_fn_method_lorodoc_export_updates_in_range(
            it, FfiConverterSequenceTypeIdSpan.INSTANCE.lower(spans), _status);
    });
    
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    })
    );
            } catch (RuntimeException _e) {
                
                if (LoroEncodeException.class.isInstance(_e.getCause())) {
                    throw (LoroEncodeException)_e.getCause();
                }
                
                if (InternalException.class.isInstance(_e.getCause())) {
                    throw (InternalException)_e.getCause();
                }
                throw _e;
            }
    }
    

  
    /**
     * Find the operation id spans that between the `from` version and the `to` version.
     */
    @Override
    public VersionVectorDiff findIdSpansBetween(Frontiers from, Frontiers to)  {
            try {
                return FfiConverterTypeVersionVectorDiff.INSTANCE.lift(
    callWithPointer(it -> {
        try {
    
            return
    UniffiHelpers.uniffiRustCall( _status -> {
        return UniffiLib.INSTANCE.uniffi_loro_fn_method_lorodoc_find_id_spans_between(
            it, FfiConverterTypeFrontiers.INSTANCE.lower(from), FfiConverterTypeFrontiers.INSTANCE.lower(to), _status);
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
     * Duplicate the document with a different PeerID
     *
     * The time complexity and space complexity of this operation are both O(n),
     *
     * When called in detached mode, it will fork at the current state frontiers.
     * It will have the same effect as `fork_at(&self.state_frontiers())`.
     */
    @Override
    public LoroDoc fork()  {
            try {
                return FfiConverterTypeLoroDoc.INSTANCE.lift(
    callWithPointer(it -> {
        try {
    
            return
    UniffiHelpers.uniffiRustCall( _status -> {
        return UniffiLib.INSTANCE.uniffi_loro_fn_method_lorodoc_fork(
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
     * Fork the document at the given frontiers.
     *
     * The created doc will only contain the history before the specified frontiers.
     */
    @Override
    public LoroDoc forkAt(Frontiers frontiers)  {
            try {
                return FfiConverterTypeLoroDoc.INSTANCE.lift(
    callWithPointer(it -> {
        try {
    
            return
    UniffiHelpers.uniffiRustCall( _status -> {
        return UniffiLib.INSTANCE.uniffi_loro_fn_method_lorodoc_fork_at(
            it, FfiConverterTypeFrontiers.INSTANCE.lower(frontiers), _status);
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
     * Free the cached diff calculator that is used for checkout.
     */
    @Override
    public void freeDiffCalculator()  {
            try {
                
    callWithPointer(it -> {
        try {
    
    UniffiHelpers.uniffiRustCall( _status -> {
        UniffiLib.INSTANCE.uniffi_loro_fn_method_lorodoc_free_diff_calculator(
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
     * Free the history cache that is used for making checkout faster.
     *
     * If you use checkout that switching to an old/concurrent version, the history cache will be built.
     * You can free it by calling this method.
     */
    @Override
    public void freeHistoryCache()  {
            try {
                
    callWithPointer(it -> {
        try {
    
    UniffiHelpers.uniffiRustCall( _status -> {
        UniffiLib.INSTANCE.uniffi_loro_fn_method_lorodoc_free_history_cache(
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
     * Convert `Frontiers` into `VersionVector`
     */
    @Override
    public VersionVector frontiersToVv(Frontiers frontiers)  {
            try {
                return FfiConverterOptionalTypeVersionVector.INSTANCE.lift(
    callWithPointer(it -> {
        try {
    
            return
    UniffiHelpers.uniffiRustCall( _status -> {
        return UniffiLib.INSTANCE.uniffi_loro_fn_method_lorodoc_frontiers_to_vv(
            it, FfiConverterTypeFrontiers.INSTANCE.lower(frontiers), _status);
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
     * Get the handler by the path.
     */
    @Override
    public ValueOrContainer getByPath(List<Index> path)  {
            try {
                return FfiConverterOptionalTypeValueOrContainer.INSTANCE.lift(
    callWithPointer(it -> {
        try {
    
            return
    UniffiHelpers.uniffiRustCall( _status -> {
        return UniffiLib.INSTANCE.uniffi_loro_fn_method_lorodoc_get_by_path(
            it, FfiConverterSequenceTypeIndex.INSTANCE.lower(path), _status);
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
    @Override
    public ValueOrContainer getByStrPath(String path)  {
            try {
                return FfiConverterOptionalTypeValueOrContainer.INSTANCE.lift(
    callWithPointer(it -> {
        try {
    
            return
    UniffiHelpers.uniffiRustCall( _status -> {
        return UniffiLib.INSTANCE.uniffi_loro_fn_method_lorodoc_get_by_str_path(
            it, FfiConverterString.INSTANCE.lower(path), _status);
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
    @Override
    public ChangeMeta getChange(Id id)  {
            try {
                return FfiConverterOptionalTypeChangeMeta.INSTANCE.lift(
    callWithPointer(it -> {
        try {
    
            return
    UniffiHelpers.uniffiRustCall( _status -> {
        return UniffiLib.INSTANCE.uniffi_loro_fn_method_lorodoc_get_change(
            it, FfiConverterTypeID.INSTANCE.lower(id), _status);
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
    @Override
    public List<ContainerId> getChangedContainersIn(Id id, Integer len)  {
            try {
                return FfiConverterSequenceTypeContainerID.INSTANCE.lift(
    callWithPointer(it -> {
        try {
    
            return
    UniffiHelpers.uniffiRustCall( _status -> {
        return UniffiLib.INSTANCE.uniffi_loro_fn_method_lorodoc_get_changed_containers_in(
            it, FfiConverterTypeID.INSTANCE.lower(id), FfiConverterInteger.INSTANCE.lower(len), _status);
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
     * Get a [LoroCounter] by container id.
     *
     * If the provided id is string, it will be converted into a root container id with the name of the string.
     */
    @Override
    public LoroCounter getCounter(ContainerIdLike id)  {
            try {
                return FfiConverterTypeLoroCounter.INSTANCE.lift(
    callWithPointer(it -> {
        try {
    
            return
    UniffiHelpers.uniffiRustCall( _status -> {
        return UniffiLib.INSTANCE.uniffi_loro_fn_method_lorodoc_get_counter(
            it, FfiConverterTypeContainerIdLike.INSTANCE.lower(id), _status);
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
    public PosQueryResult getCursorPos(Cursor cursor) throws CannotFindRelativePosition {
            try {
                return FfiConverterTypePosQueryResult.INSTANCE.lift(
    callWithPointer(it -> {
        try {
    
            return
    UniffiHelpers.uniffiRustCallWithError(new CannotFindRelativePositionErrorHandler(), _status -> {
        return UniffiLib.INSTANCE.uniffi_loro_fn_method_lorodoc_get_cursor_pos(
            it, FfiConverterTypeCursor.INSTANCE.lower(cursor), _status);
    });
    
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    })
    );
            } catch (RuntimeException _e) {
                
                if (CannotFindRelativePosition.class.isInstance(_e.getCause())) {
                    throw (CannotFindRelativePosition)_e.getCause();
                }
                
                if (InternalException.class.isInstance(_e.getCause())) {
                    throw (InternalException)_e.getCause();
                }
                throw _e;
            }
    }
    

  
    /**
     * Get the entire state of the current DocState
     */
    @Override
    public LoroValue getDeepValue()  {
            try {
                return FfiConverterTypeLoroValue.INSTANCE.lift(
    callWithPointer(it -> {
        try {
    
            return
    UniffiHelpers.uniffiRustCall( _status -> {
        return UniffiLib.INSTANCE.uniffi_loro_fn_method_lorodoc_get_deep_value(
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
     * Get the entire state of the current DocState with container id
     */
    @Override
    public LoroValue getDeepValueWithId()  {
            try {
                return FfiConverterTypeLoroValue.INSTANCE.lift(
    callWithPointer(it -> {
        try {
    
            return
    UniffiHelpers.uniffiRustCall( _status -> {
        return UniffiLib.INSTANCE.uniffi_loro_fn_method_lorodoc_get_deep_value_with_id(
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
     * Get a [LoroList] by container id.
     *
     * If the provided id is string, it will be converted into a root container id with the name of the string.
     */
    @Override
    public LoroList getList(ContainerIdLike id)  {
            try {
                return FfiConverterTypeLoroList.INSTANCE.lift(
    callWithPointer(it -> {
        try {
    
            return
    UniffiHelpers.uniffiRustCall( _status -> {
        return UniffiLib.INSTANCE.uniffi_loro_fn_method_lorodoc_get_list(
            it, FfiConverterTypeContainerIdLike.INSTANCE.lower(id), _status);
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
     * Get a [LoroMap] by container id.
     *
     * If the provided id is string, it will be converted into a root container id with the name of the string.
     */
    @Override
    public LoroMap getMap(ContainerIdLike id)  {
            try {
                return FfiConverterTypeLoroMap.INSTANCE.lift(
    callWithPointer(it -> {
        try {
    
            return
    UniffiHelpers.uniffiRustCall( _status -> {
        return UniffiLib.INSTANCE.uniffi_loro_fn_method_lorodoc_get_map(
            it, FfiConverterTypeContainerIdLike.INSTANCE.lower(id), _status);
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
     * Get a [LoroMovableList] by container id.
     *
     * If the provided id is string, it will be converted into a root container id with the name of the string.
     */
    @Override
    public LoroMovableList getMovableList(ContainerIdLike id)  {
            try {
                return FfiConverterTypeLoroMovableList.INSTANCE.lift(
    callWithPointer(it -> {
        try {
    
            return
    UniffiHelpers.uniffiRustCall( _status -> {
        return UniffiLib.INSTANCE.uniffi_loro_fn_method_lorodoc_get_movable_list(
            it, FfiConverterTypeContainerIdLike.INSTANCE.lower(id), _status);
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
     * Get the path from the root to the container
     */
    @Override
    public List<ContainerPath> getPathToContainer(ContainerId id)  {
            try {
                return FfiConverterOptionalSequenceTypeContainerPath.INSTANCE.lift(
    callWithPointer(it -> {
        try {
    
            return
    UniffiHelpers.uniffiRustCall( _status -> {
        return UniffiLib.INSTANCE.uniffi_loro_fn_method_lorodoc_get_path_to_container(
            it, FfiConverterTypeContainerID.INSTANCE.lower(id), _status);
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
     * Get the number of operations in the pending transaction.
     *
     * The pending transaction is the one that is not committed yet. It will be committed
     * after calling `doc.commit()`, `doc.export(mode)` or `doc.checkout(version)`.
     */
    @Override
    public Integer getPendingTxnLen()  {
            try {
                return FfiConverterInteger.INSTANCE.lift(
    callWithPointer(it -> {
        try {
    
            return
    UniffiHelpers.uniffiRustCall( _status -> {
        return UniffiLib.INSTANCE.uniffi_loro_fn_method_lorodoc_get_pending_txn_len(
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
     * Get a [LoroText] by container id.
     *
     * If the provided id is string, it will be converted into a root container id with the name of the string.
     */
    @Override
    public LoroText getText(ContainerIdLike id)  {
            try {
                return FfiConverterTypeLoroText.INSTANCE.lift(
    callWithPointer(it -> {
        try {
    
            return
    UniffiHelpers.uniffiRustCall( _status -> {
        return UniffiLib.INSTANCE.uniffi_loro_fn_method_lorodoc_get_text(
            it, FfiConverterTypeContainerIdLike.INSTANCE.lower(id), _status);
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
     * Get a [LoroTree] by container id.
     *
     * If the provided id is string, it will be converted into a root container id with the name of the string.
     */
    @Override
    public LoroTree getTree(ContainerIdLike id)  {
            try {
                return FfiConverterTypeLoroTree.INSTANCE.lift(
    callWithPointer(it -> {
        try {
    
            return
    UniffiHelpers.uniffiRustCall( _status -> {
        return UniffiLib.INSTANCE.uniffi_loro_fn_method_lorodoc_get_tree(
            it, FfiConverterTypeContainerIdLike.INSTANCE.lower(id), _status);
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
     * Get the shallow value of the document.
     */
    @Override
    public LoroValue getValue()  {
            try {
                return FfiConverterTypeLoroValue.INSTANCE.lift(
    callWithPointer(it -> {
        try {
    
            return
    UniffiHelpers.uniffiRustCall( _status -> {
        return UniffiLib.INSTANCE.uniffi_loro_fn_method_lorodoc_get_value(
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
     * Check if the doc contains the target container.
     *
     * A root container always exists, while a normal container exists
     * if it has ever been created on the doc.
     */
    @Override
    public Boolean hasContainer(ContainerId id)  {
            try {
                return FfiConverterBoolean.INSTANCE.lift(
    callWithPointer(it -> {
        try {
    
            return
    UniffiHelpers.uniffiRustCall( _status -> {
        return UniffiLib.INSTANCE.uniffi_loro_fn_method_lorodoc_has_container(
            it, FfiConverterTypeContainerID.INSTANCE.lower(id), _status);
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
    public Boolean hasHistoryCache()  {
            try {
                return FfiConverterBoolean.INSTANCE.lift(
    callWithPointer(it -> {
        try {
    
            return
    UniffiHelpers.uniffiRustCall( _status -> {
        return UniffiLib.INSTANCE.uniffi_loro_fn_method_lorodoc_has_history_cache(
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
     * Import updates/snapshot exported by [`LoroDoc::export_snapshot`] or [`LoroDoc::export_from`].
     */
    @Override
    public ImportStatus _import(byte[] bytes) throws LoroException {
            try {
                return FfiConverterTypeImportStatus.INSTANCE.lift(
    callWithPointer(it -> {
        try {
    
            return
    UniffiHelpers.uniffiRustCallWithError(new LoroExceptionErrorHandler(), _status -> {
        return UniffiLib.INSTANCE.uniffi_loro_fn_method_lorodoc_import(
            it, FfiConverterByteArray.INSTANCE.lower(bytes), _status);
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
     * Import a batch of updates/snapshot.
     *
     * The data can be in arbitrary order. The import result will be the same.
     */
    @Override
    public ImportStatus importBatch(List<byte[]> bytes) throws LoroException {
            try {
                return FfiConverterTypeImportStatus.INSTANCE.lift(
    callWithPointer(it -> {
        try {
    
            return
    UniffiHelpers.uniffiRustCallWithError(new LoroExceptionErrorHandler(), _status -> {
        return UniffiLib.INSTANCE.uniffi_loro_fn_method_lorodoc_import_batch(
            it, FfiConverterSequenceByteArray.INSTANCE.lower(bytes), _status);
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
    public ImportStatus importJsonUpdates(String json) throws LoroException {
            try {
                return FfiConverterTypeImportStatus.INSTANCE.lift(
    callWithPointer(it -> {
        try {
    
            return
    UniffiHelpers.uniffiRustCallWithError(new LoroExceptionErrorHandler(), _status -> {
        return UniffiLib.INSTANCE.uniffi_loro_fn_method_lorodoc_import_json_updates(
            it, FfiConverterString.INSTANCE.lower(json), _status);
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
     * Import updates/snapshot exported by [`LoroDoc::export_snapshot`] or [`LoroDoc::export_from`].
     *
     * It marks the import with a custom `origin` string. It can be used to track the import source
     * in the generated events.
     */
    @Override
    public ImportStatus importWith(byte[] bytes, String origin) throws LoroException {
            try {
                return FfiConverterTypeImportStatus.INSTANCE.lift(
    callWithPointer(it -> {
        try {
    
            return
    UniffiHelpers.uniffiRustCallWithError(new LoroExceptionErrorHandler(), _status -> {
        return UniffiLib.INSTANCE.uniffi_loro_fn_method_lorodoc_import_with(
            it, FfiConverterByteArray.INSTANCE.lower(bytes), FfiConverterString.INSTANCE.lower(origin), _status);
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
     * Whether the document is in detached mode, where the [loro_internal::DocState] is not
     * synchronized with the latest version of the [loro_internal::OpLog].
     */
    @Override
    public Boolean isDetached()  {
            try {
                return FfiConverterBoolean.INSTANCE.lift(
    callWithPointer(it -> {
        try {
    
            return
    UniffiHelpers.uniffiRustCall( _status -> {
        return UniffiLib.INSTANCE.uniffi_loro_fn_method_lorodoc_is_detached(
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
     * Check if the doc contains the full history.
     */
    @Override
    public Boolean isShallow()  {
            try {
                return FfiConverterBoolean.INSTANCE.lift(
    callWithPointer(it -> {
        try {
    
            return
    UniffiHelpers.uniffiRustCall( _status -> {
        return UniffiLib.INSTANCE.uniffi_loro_fn_method_lorodoc_is_shallow(
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
    @Override
    public List<ValueOrContainer> jsonpath(String path) throws JsonPathException {
            try {
                return FfiConverterSequenceTypeValueOrContainer.INSTANCE.lift(
    callWithPointer(it -> {
        try {
    
            return
    UniffiHelpers.uniffiRustCallWithError(new JsonPathExceptionErrorHandler(), _status -> {
        return UniffiLib.INSTANCE.uniffi_loro_fn_method_lorodoc_jsonpath(
            it, FfiConverterString.INSTANCE.lower(path), _status);
    });
    
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    })
    );
            } catch (RuntimeException _e) {
                
                if (JsonPathException.class.isInstance(_e.getCause())) {
                    throw (JsonPathException)_e.getCause();
                }
                
                if (InternalException.class.isInstance(_e.getCause())) {
                    throw (InternalException)_e.getCause();
                }
                throw _e;
            }
    }
    

  
    /**
     * Get the total number of changes in the `OpLog`
     */
    @Override
    public Long lenChanges()  {
            try {
                return FfiConverterLong.INSTANCE.lift(
    callWithPointer(it -> {
        try {
    
            return
    UniffiHelpers.uniffiRustCall( _status -> {
        return UniffiLib.INSTANCE.uniffi_loro_fn_method_lorodoc_len_changes(
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
     * Get the total number of operations in the `OpLog`
     */
    @Override
    public Long lenOps()  {
            try {
                return FfiConverterLong.INSTANCE.lift(
    callWithPointer(it -> {
        try {
    
            return
    UniffiHelpers.uniffiRustCall( _status -> {
        return UniffiLib.INSTANCE.uniffi_loro_fn_method_lorodoc_len_ops(
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
     * Estimate the size of the document states in memory.
     */
    @Override
    public void logEstimateSize()  {
            try {
                
    callWithPointer(it -> {
        try {
    
    UniffiHelpers.uniffiRustCall( _status -> {
        UniffiLib.INSTANCE.uniffi_loro_fn_method_lorodoc_log_estimate_size(
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
     * Minimize the frontiers by removing the unnecessary entries.
     */
    @Override
    public FrontiersOrId minimizeFrontiers(Frontiers frontiers)  {
            try {
                return FfiConverterTypeFrontiersOrID.INSTANCE.lift(
    callWithPointer(it -> {
        try {
    
            return
    UniffiHelpers.uniffiRustCall( _status -> {
        return UniffiLib.INSTANCE.uniffi_loro_fn_method_lorodoc_minimize_frontiers(
            it, FfiConverterTypeFrontiers.INSTANCE.lower(frontiers), _status);
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
     * Get the `Frontiers` version of `OpLog`
     */
    @Override
    public Frontiers oplogFrontiers()  {
            try {
                return FfiConverterTypeFrontiers.INSTANCE.lift(
    callWithPointer(it -> {
        try {
    
            return
    UniffiHelpers.uniffiRustCall( _status -> {
        return UniffiLib.INSTANCE.uniffi_loro_fn_method_lorodoc_oplog_frontiers(
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
     * Get the `VersionVector` version of `OpLog`
     */
    @Override
    public VersionVector oplogVv()  {
            try {
                return FfiConverterTypeVersionVector.INSTANCE.lift(
    callWithPointer(it -> {
        try {
    
            return
    UniffiHelpers.uniffiRustCall( _status -> {
        return UniffiLib.INSTANCE.uniffi_loro_fn_method_lorodoc_oplog_vv(
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
     * Get the PeerID
     */
    @Override
    public Long peerId()  {
            try {
                return FfiConverterLong.INSTANCE.lift(
    callWithPointer(it -> {
        try {
    
            return
    UniffiHelpers.uniffiRustCall( _status -> {
        return UniffiLib.INSTANCE.uniffi_loro_fn_method_lorodoc_peer_id(
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
     * Revert the current document state back to the target version
     *
     * Internally, it will generate a series of local operations that can revert the
     * current doc to the target version. It will calculate the diff between the current
     * state and the target state, and apply the diff to the current state.
     */
    @Override
    public void revertTo(Frontiers version) throws LoroException {
            try {
                
    callWithPointer(it -> {
        try {
    
    UniffiHelpers.uniffiRustCallWithError(new LoroExceptionErrorHandler(), _status -> {
        UniffiLib.INSTANCE.uniffi_loro_fn_method_lorodoc_revert_to(
            it, FfiConverterTypeFrontiers.INSTANCE.lower(version), _status);
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
     * Set the interval of mergeable changes, **in seconds**.
     *
     * If two continuous local changes are within the interval, they will be merged into one change.
     * The default value is 1000 seconds.
     *
     * By default, we record timestamps in seconds for each change. So if the merge interval is 1, and changes A and B
     * have timestamps of 3 and 4 respectively, then they will be merged into one change
     */
    @Override
    public void setChangeMergeInterval(Long interval)  {
            try {
                
    callWithPointer(it -> {
        try {
    
    UniffiHelpers.uniffiRustCall( _status -> {
        UniffiLib.INSTANCE.uniffi_loro_fn_method_lorodoc_set_change_merge_interval(
            it, FfiConverterLong.INSTANCE.lower(interval), _status);
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
     * Set commit message for the current uncommitted changes
     *
     * It will be persisted.
     */
    @Override
    public void setNextCommitMessage(String msg)  {
            try {
                
    callWithPointer(it -> {
        try {
    
    UniffiHelpers.uniffiRustCall( _status -> {
        UniffiLib.INSTANCE.uniffi_loro_fn_method_lorodoc_set_next_commit_message(
            it, FfiConverterString.INSTANCE.lower(msg), _status);
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
     * Set the options of the next commit.
     *
     * It will be used when the next commit is performed.
     */
    @Override
    public void setNextCommitOptions(CommitOptions options)  {
            try {
                
    callWithPointer(it -> {
        try {
    
    UniffiHelpers.uniffiRustCall( _status -> {
        UniffiLib.INSTANCE.uniffi_loro_fn_method_lorodoc_set_next_commit_options(
            it, FfiConverterTypeCommitOptions.INSTANCE.lower(options), _status);
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
     * Set `origin` for the current uncommitted changes, it can be used to track the source of changes in an event.
     *
     * It will NOT be persisted.
     */
    @Override
    public void setNextCommitOrigin(String origin)  {
            try {
                
    callWithPointer(it -> {
        try {
    
    UniffiHelpers.uniffiRustCall( _status -> {
        UniffiLib.INSTANCE.uniffi_loro_fn_method_lorodoc_set_next_commit_origin(
            it, FfiConverterString.INSTANCE.lower(origin), _status);
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
     * Set the timestamp of the next commit.
     *
     * It will be persisted and stored in the `OpLog`.
     * You can get the timestamp from the [`Change`] type.
     */
    @Override
    public void setNextCommitTimestamp(Long timestamp)  {
            try {
                
    callWithPointer(it -> {
        try {
    
    UniffiHelpers.uniffiRustCall( _status -> {
        UniffiLib.INSTANCE.uniffi_loro_fn_method_lorodoc_set_next_commit_timestamp(
            it, FfiConverterLong.INSTANCE.lower(timestamp), _status);
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
     * Change the PeerID
     *
     * NOTE: You need to make sure there is no chance two peer have the same PeerID.
     * If it happens, the document will be corrupted.
     */
    @Override
    public void setPeerId(Long peer) throws LoroException {
            try {
                
    callWithPointer(it -> {
        try {
    
    UniffiHelpers.uniffiRustCallWithError(new LoroExceptionErrorHandler(), _status -> {
        UniffiLib.INSTANCE.uniffi_loro_fn_method_lorodoc_set_peer_id(
            it, FfiConverterLong.INSTANCE.lower(peer), _status);
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
    @Override
    public void setRecordTimestamp(Boolean record)  {
            try {
                
    callWithPointer(it -> {
        try {
    
    UniffiHelpers.uniffiRustCall( _status -> {
        UniffiLib.INSTANCE.uniffi_loro_fn_method_lorodoc_set_record_timestamp(
            it, FfiConverterBoolean.INSTANCE.lower(record), _status);
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
     * Get the `VersionVector` of trimmed history
     *
     * The ops included by the trimmed history are not in the doc.
     */
    @Override
    public VersionVector shallowSinceVv()  {
            try {
                return FfiConverterTypeVersionVector.INSTANCE.lift(
    callWithPointer(it -> {
        try {
    
            return
    UniffiHelpers.uniffiRustCall( _status -> {
        return UniffiLib.INSTANCE.uniffi_loro_fn_method_lorodoc_shallow_since_vv(
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
     * Get the `Frontiers` version of `DocState`
     *
     * Learn more about [`Frontiers`](https://loro.dev/docs/advanced/version_deep_dive)
     */
    @Override
    public Frontiers stateFrontiers()  {
            try {
                return FfiConverterTypeFrontiers.INSTANCE.lift(
    callWithPointer(it -> {
        try {
    
            return
    UniffiHelpers.uniffiRustCall( _status -> {
        return UniffiLib.INSTANCE.uniffi_loro_fn_method_lorodoc_state_frontiers(
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
     * Get the `VersionVector` version of `DocState`
     */
    @Override
    public VersionVector stateVv()  {
            try {
                return FfiConverterTypeVersionVector.INSTANCE.lift(
    callWithPointer(it -> {
        try {
    
            return
    UniffiHelpers.uniffiRustCall( _status -> {
        return UniffiLib.INSTANCE.uniffi_loro_fn_method_lorodoc_state_vv(
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
    @Override
    public Subscription subscribe(ContainerId containerId, Subscriber subscriber)  {
            try {
                return FfiConverterTypeSubscription.INSTANCE.lift(
    callWithPointer(it -> {
        try {
    
            return
    UniffiHelpers.uniffiRustCall( _status -> {
        return UniffiLib.INSTANCE.uniffi_loro_fn_method_lorodoc_subscribe(
            it, FfiConverterTypeContainerID.INSTANCE.lower(containerId), FfiConverterTypeSubscriber.INSTANCE.lower(subscriber), _status);
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
     * Subscribe the local update of the document.
     */
    @Override
    public Subscription subscribeLocalUpdate(LocalUpdateCallback callback)  {
            try {
                return FfiConverterTypeSubscription.INSTANCE.lift(
    callWithPointer(it -> {
        try {
    
            return
    UniffiHelpers.uniffiRustCall( _status -> {
        return UniffiLib.INSTANCE.uniffi_loro_fn_method_lorodoc_subscribe_local_update(
            it, FfiConverterTypeLocalUpdateCallback.INSTANCE.lower(callback), _status);
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
     * Subscribe all the events.
     *
     * The callback will be invoked when any part of the [loro_internal::DocState] is changed.
     * Returns a subscription that can be used to unsubscribe.
     */
    @Override
    public Subscription subscribeRoot(Subscriber subscriber)  {
            try {
                return FfiConverterTypeSubscription.INSTANCE.lift(
    callWithPointer(it -> {
        try {
    
            return
    UniffiHelpers.uniffiRustCall( _status -> {
        return UniffiLib.INSTANCE.uniffi_loro_fn_method_lorodoc_subscribe_root(
            it, FfiConverterTypeSubscriber.INSTANCE.lower(subscriber), _status);
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
    @Override
    public void travelChangeAncestors(List<Id> ids, ChangeAncestorsTraveler f) throws ChangeTravelException {
            try {
                
    callWithPointer(it -> {
        try {
    
    UniffiHelpers.uniffiRustCallWithError(new ChangeTravelExceptionErrorHandler(), _status -> {
        UniffiLib.INSTANCE.uniffi_loro_fn_method_lorodoc_travel_change_ancestors(
            it, FfiConverterSequenceTypeID.INSTANCE.lower(ids), FfiConverterTypeChangeAncestorsTraveler.INSTANCE.lower(f), _status);
    });
    
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    })
    ;
            } catch (RuntimeException _e) {
                
                if (ChangeTravelException.class.isInstance(_e.getCause())) {
                    throw (ChangeTravelException)_e.getCause();
                }
                
                if (InternalException.class.isInstance(_e.getCause())) {
                    throw (InternalException)_e.getCause();
                }
                throw _e;
            }
    }
    

  
    /**
     * Convert `VersionVector` into `Frontiers`
     */
    @Override
    public Frontiers vvToFrontiers(VersionVector vv)  {
            try {
                return FfiConverterTypeFrontiers.INSTANCE.lift(
    callWithPointer(it -> {
        try {
    
            return
    UniffiHelpers.uniffiRustCall( _status -> {
        return UniffiLib.INSTANCE.uniffi_loro_fn_method_lorodoc_vv_to_frontiers(
            it, FfiConverterTypeVersionVector.INSTANCE.lower(vv), _status);
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



