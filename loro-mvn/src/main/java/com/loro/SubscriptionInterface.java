package com.loro;


import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import com.sun.jna.*;
import com.sun.jna.ptr.*;
/**
 * A handle to a subscription created by GPUI. When dropped, the subscription
 * is cancelled and the callback will no longer be invoked.
 */
public interface SubscriptionInterface {
    
    /**
     * Detaches the subscription from this handle. The callback will
     * continue to be invoked until the views or models it has been
     * subscribed to are dropped
     */
    public void detach();
    
    /**
     * Unsubscribes the subscription.
     */
    public void unsubscribe();
    
}

