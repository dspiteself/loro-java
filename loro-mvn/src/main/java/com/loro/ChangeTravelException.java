package com.loro;



public class ChangeTravelException extends Exception {
    private ChangeTravelException(String message) {
      super(message);
    }

    
    public static class TargetIdNotFound extends ChangeTravelException {
      public TargetIdNotFound(String message) {
        super(message);
      }
    }
    
    public static class TargetVersionNotIncluded extends ChangeTravelException {
      public TargetVersionNotIncluded(String message) {
        super(message);
      }
    }
    
}

