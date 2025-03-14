package com.loro;


import com.sun.jna.*;
import com.sun.jna.ptr.*;

interface UniffiCallbackInterfaceValueOrContainerMethod1 extends Callback {
    public void callback(long uniffiHandle,RustBuffer uniffiOutReturn,
        UniffiRustCallStatus uniffiCallStatus);
}
