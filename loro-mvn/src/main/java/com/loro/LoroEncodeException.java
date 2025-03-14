package com.loro;



public class LoroEncodeException extends Exception {
    private LoroEncodeException(String message) {
      super(message);
    }

    
    public static class FrontiersNotFound extends LoroEncodeException {
      public FrontiersNotFound(String message) {
        super(message);
      }
    }
    
    public static class ShallowSnapshotIncompatibleWithOldFormat extends LoroEncodeException {
      public ShallowSnapshotIncompatibleWithOldFormat(String message) {
        super(message);
      }
    }
    
    public static class UnknownContainer extends LoroEncodeException {
      public UnknownContainer(String message) {
        super(message);
      }
    }
    
}

