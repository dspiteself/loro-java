package com.loro;



public class LoroException extends Exception {
    private LoroException(String message) {
      super(message);
    }

    
    public static class UnmatchedContext extends LoroException {
      public UnmatchedContext(String message) {
        super(message);
      }
    }
    
    public static class DecodeVersionVectorException extends LoroException {
      public DecodeVersionVectorException(String message) {
        super(message);
      }
    }
    
    public static class DecodeException extends LoroException {
      public DecodeException(String message) {
        super(message);
      }
    }
    
    public static class DecodeDataCorruptionException extends LoroException {
      public DecodeDataCorruptionException(String message) {
        super(message);
      }
    }
    
    public static class DecodeChecksumMismatchException extends LoroException {
      public DecodeChecksumMismatchException(String message) {
        super(message);
      }
    }
    
    public static class IncompatibleFutureEncodingException extends LoroException {
      public IncompatibleFutureEncodingException(String message) {
        super(message);
      }
    }
    
    public static class JsException extends LoroException {
      public JsException(String message) {
        super(message);
      }
    }
    
    public static class LockException extends LoroException {
      public LockException(String message) {
        super(message);
      }
    }
    
    public static class DuplicatedTransactionException extends LoroException {
      public DuplicatedTransactionException(String message) {
        super(message);
      }
    }
    
    public static class NotFoundException extends LoroException {
      public NotFoundException(String message) {
        super(message);
      }
    }
    
    public static class TransactionException extends LoroException {
      public TransactionException(String message) {
        super(message);
      }
    }
    
    public static class OutOfBound extends LoroException {
      public OutOfBound(String message) {
        super(message);
      }
    }
    
    public static class UsedOpId extends LoroException {
      public UsedOpId(String message) {
        super(message);
      }
    }
    
    public static class TreeException extends LoroException {
      public TreeException(String message) {
        super(message);
      }
    }
    
    public static class ArgErr extends LoroException {
      public ArgErr(String message) {
        super(message);
      }
    }
    
    public static class AutoCommitNotStarted extends LoroException {
      public AutoCommitNotStarted(String message) {
        super(message);
      }
    }
    
    public static class StyleConfigMissing extends LoroException {
      public StyleConfigMissing(String message) {
        super(message);
      }
    }
    
    public static class Unknown extends LoroException {
      public Unknown(String message) {
        super(message);
      }
    }
    
    public static class FrontiersNotFound extends LoroException {
      public FrontiersNotFound(String message) {
        super(message);
      }
    }
    
    public static class ImportWhenInTxn extends LoroException {
      public ImportWhenInTxn(String message) {
        super(message);
      }
    }
    
    public static class MisuseDetachedContainer extends LoroException {
      public MisuseDetachedContainer(String message) {
        super(message);
      }
    }
    
    public static class NotImplemented extends LoroException {
      public NotImplemented(String message) {
        super(message);
      }
    }
    
    public static class ReattachAttachedContainer extends LoroException {
      public ReattachAttachedContainer(String message) {
        super(message);
      }
    }
    
    public static class EditWhenDetached extends LoroException {
      public EditWhenDetached(String message) {
        super(message);
      }
    }
    
    public static class UndoInvalidIdSpan extends LoroException {
      public UndoInvalidIdSpan(String message) {
        super(message);
      }
    }
    
    public static class UndoWithDifferentPeerId extends LoroException {
      public UndoWithDifferentPeerId(String message) {
        super(message);
      }
    }
    
    public static class InvalidJsonSchema extends LoroException {
      public InvalidJsonSchema(String message) {
        super(message);
      }
    }
    
    public static class Utf8InUnicodeCodePoint extends LoroException {
      public Utf8InUnicodeCodePoint(String message) {
        super(message);
      }
    }
    
    public static class Utf16InUnicodeCodePoint extends LoroException {
      public Utf16InUnicodeCodePoint(String message) {
        super(message);
      }
    }
    
    public static class EndIndexLessThanStartIndex extends LoroException {
      public EndIndexLessThanStartIndex(String message) {
        super(message);
      }
    }
    
    public static class InvalidRootContainerName extends LoroException {
      public InvalidRootContainerName(String message) {
        super(message);
      }
    }
    
    public static class ImportUpdatesThatDependsOnOutdatedVersion extends LoroException {
      public ImportUpdatesThatDependsOnOutdatedVersion(String message) {
        super(message);
      }
    }
    
    public static class SwitchToVersionBeforeShallowRoot extends LoroException {
      public SwitchToVersionBeforeShallowRoot(String message) {
        super(message);
      }
    }
    
    public static class ContainerDeleted extends LoroException {
      public ContainerDeleted(String message) {
        super(message);
      }
    }
    
    public static class ConcurrentOpsWithSamePeerId extends LoroException {
      public ConcurrentOpsWithSamePeerId(String message) {
        super(message);
      }
    }
    
    public static class InvalidPeerId extends LoroException {
      public InvalidPeerId(String message) {
        super(message);
      }
    }
    
    public static class ContainersNotFound extends LoroException {
      public ContainersNotFound(String message) {
        super(message);
      }
    }
    
}

