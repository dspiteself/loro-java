package com.loro;


import com.sun.jna.*;
import com.sun.jna.ptr.*;

interface UniffiCallbackInterfaceSubscriberMethod0 extends Callback {
    public void callback(long uniffiHandle,RustBuffer.ByValue diff,Pointer uniffiOutReturn,
        UniffiRustCallStatus uniffiCallStatus);
}
