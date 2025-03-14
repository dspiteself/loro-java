package com.loro;


import java.util.List;
import java.util.Map;
public sealed interface TreeExternalDiff {
  
  record Create(
    TreeParentId parent, 
    Integer index, 
    String fractionalIndex) implements TreeExternalDiff {
    
  }
  
  record Move(
    TreeParentId parent, 
    Integer index, 
    String fractionalIndex, 
    TreeParentId oldParent, 
    Integer oldIndex) implements TreeExternalDiff {
    
  }
  
  record Delete(
    TreeParentId oldParent, 
    Integer oldIndex) implements TreeExternalDiff {
    
  }
  
}

