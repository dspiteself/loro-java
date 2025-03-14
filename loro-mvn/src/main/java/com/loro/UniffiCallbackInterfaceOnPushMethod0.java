package com.loro;


import com.sun.jna.*;
import com.sun.jna.ptr.*;

interface UniffiCallbackInterfaceOnPushMethod0 extends Callback {
    public void callback(long uniffiHandle,RustBuffer.ByValue undoOrRedo,RustBuffer.ByValue span,RustBuffer.ByValue diffEvent,RustBuffer uniffiOutReturn,
        UniffiRustCallStatus uniffiCallStatus);
}
