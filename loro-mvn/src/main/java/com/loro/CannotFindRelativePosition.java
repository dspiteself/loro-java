package com.loro;



public class CannotFindRelativePosition extends Exception {
    private CannotFindRelativePosition(String message) {
      super(message);
    }

    
    public static class ContainerDeleted extends CannotFindRelativePosition {
      public ContainerDeleted(String message) {
        super(message);
      }
    }
    
    public static class HistoryCleared extends CannotFindRelativePosition {
      public HistoryCleared(String message) {
        super(message);
      }
    }
    
    public static class IdNotFound extends CannotFindRelativePosition {
      public IdNotFound(String message) {
        super(message);
      }
    }
    
}

