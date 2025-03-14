package com.loro;



public class JsonPathException extends Exception {
    private JsonPathException(String message) {
      super(message);
    }

    
    public static class InvalidJsonPath extends JsonPathException {
      public InvalidJsonPath(String message) {
        super(message);
      }
    }
    
    public static class EvaluationException extends JsonPathException {
      public EvaluationException(String message) {
        super(message);
      }
    }
    
}

