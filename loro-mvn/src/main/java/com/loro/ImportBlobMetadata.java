package com.loro;


import java.util.List;
import java.util.Map;
import java.nio.ByteBuffer;
import java.util.Objects;
public class ImportBlobMetadata implements AutoCloseable {
    /**
     * The partial start version vector.
     *
     * Import blob includes all the ops from `partial_start_vv` to `partial_end_vv`.
     * However, it does not constitute a complete version vector, as it only contains counters
     * from peers included within the import blob.
     */
    private VersionVector partialStartVv;
    /**
     * The partial end version vector.
     *
     * Import blob includes all the ops from `partial_start_vv` to `partial_end_vv`.
     * However, it does not constitute a complete version vector, as it only contains counters
     * from peers included within the import blob.
     */
    private VersionVector partialEndVv;
    private Long startTimestamp;
    private Frontiers startFrontiers;
    private Long endTimestamp;
    private Integer changeNum;
    private String mode;

    public ImportBlobMetadata(
        VersionVector partialStartVv, 
        VersionVector partialEndVv, 
        Long startTimestamp, 
        Frontiers startFrontiers, 
        Long endTimestamp, 
        Integer changeNum, 
        String mode
    ) {
        
        this.partialStartVv = partialStartVv;
        
        this.partialEndVv = partialEndVv;
        
        this.startTimestamp = startTimestamp;
        
        this.startFrontiers = startFrontiers;
        
        this.endTimestamp = endTimestamp;
        
        this.changeNum = changeNum;
        
        this.mode = mode;
    }
    
    public VersionVector partialStartVv() {
        return this.partialStartVv;
    }
    
    public VersionVector partialEndVv() {
        return this.partialEndVv;
    }
    
    public Long startTimestamp() {
        return this.startTimestamp;
    }
    
    public Frontiers startFrontiers() {
        return this.startFrontiers;
    }
    
    public Long endTimestamp() {
        return this.endTimestamp;
    }
    
    public Integer changeNum() {
        return this.changeNum;
    }
    
    public String mode() {
        return this.mode;
    }
    public void setPartialStartVv(VersionVector partialStartVv) {
        this.partialStartVv = partialStartVv;
    }
    public void setPartialEndVv(VersionVector partialEndVv) {
        this.partialEndVv = partialEndVv;
    }
    public void setStartTimestamp(Long startTimestamp) {
        this.startTimestamp = startTimestamp;
    }
    public void setStartFrontiers(Frontiers startFrontiers) {
        this.startFrontiers = startFrontiers;
    }
    public void setEndTimestamp(Long endTimestamp) {
        this.endTimestamp = endTimestamp;
    }
    public void setChangeNum(Integer changeNum) {
        this.changeNum = changeNum;
    }
    public void setMode(String mode) {
        this.mode = mode;
    }

    
    @Override
    public void close() {
        
    AutoCloseableHelper.close(
        this.partialStartVv, 
        this.partialEndVv, 
        this.startTimestamp, 
        this.startFrontiers, 
        this.endTimestamp, 
        this.changeNum, 
        this.mode);
    }
    
    
    @Override
    public boolean equals(Object other) {
        if (other instanceof ImportBlobMetadata) {
            ImportBlobMetadata t = (ImportBlobMetadata) other;
            return (
              Objects.equals(partialStartVv, t.partialStartVv) && 
              
              Objects.equals(partialEndVv, t.partialEndVv) && 
              
              Objects.equals(startTimestamp, t.startTimestamp) && 
              
              Objects.equals(startFrontiers, t.startFrontiers) && 
              
              Objects.equals(endTimestamp, t.endTimestamp) && 
              
              Objects.equals(changeNum, t.changeNum) && 
              
              Objects.equals(mode, t.mode)
              
            );
        };
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(partialStartVv, partialEndVv, startTimestamp, startFrontiers, endTimestamp, changeNum, mode);
    }
}


