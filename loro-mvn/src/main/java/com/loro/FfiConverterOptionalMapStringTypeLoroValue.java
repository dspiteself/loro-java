package com.loro;


import java.nio.ByteBuffer;
import java.util.Map;

// public class TestForOptionals {}
public enum FfiConverterOptionalMapStringTypeLoroValue implements FfiConverterRustBuffer<Map<String, LoroValue>> {
  INSTANCE;

  @Override
  public Map<String, LoroValue> read(ByteBuffer buf) {
    if (buf.get() == (byte)0) {
      return null;
    }
    return FfiConverterMapStringTypeLoroValue.INSTANCE.read(buf);
  }

  @Override
  public long allocationSize(Map<String, LoroValue> value) {
    if (value == null) {
      return 1L;
    } else {
      return 1L + FfiConverterMapStringTypeLoroValue.INSTANCE.allocationSize(value);
    }
  }

  @Override
  public void write(Map<String, LoroValue> value, ByteBuffer buf) {
    if (value == null) {
      buf.put((byte)0);
    } else {
      buf.put((byte)1);
      FfiConverterMapStringTypeLoroValue.INSTANCE.write(value, buf);
    }
  }
}



/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
