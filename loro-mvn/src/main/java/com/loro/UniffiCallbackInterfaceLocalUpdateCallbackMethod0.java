package com.loro;


import com.sun.jna.*;
import com.sun.jna.ptr.*;

interface UniffiCallbackInterfaceLocalUpdateCallbackMethod0 extends Callback {
    public void callback(long uniffiHandle,RustBuffer.ByValue update,Pointer uniffiOutReturn,
        UniffiRustCallStatus uniffiCallStatus);
}
