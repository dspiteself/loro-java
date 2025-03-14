package com.loro;



public class UpdateTimeoutException extends Exception {
    private UpdateTimeoutException(String message) {
      super(message);
    }

    
    public static class Timeout extends UpdateTimeoutException {
      public Timeout(String message) {
        super(message);
      }
    }
    
}

