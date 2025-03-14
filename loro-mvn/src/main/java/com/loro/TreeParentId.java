package com.loro;


import java.util.List;
import java.util.Map;
public sealed interface TreeParentId {
  
  record Node(
    TreeId id) implements TreeParentId {
    
  }
  
  record Root() implements TreeParentId {
    
  }
  
  
  record Deleted() implements TreeParentId {
    
  }
  
  
  record Unexist() implements TreeParentId {
    
  }
  
  
}

