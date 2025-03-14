package com.loro;


import java.util.List;
import java.nio.ByteBuffer;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public enum FfiConverterSequenceTypeIndex implements FfiConverterRustBuffer<List<Index>> {
  INSTANCE;

  @Override
  public List<Index> read(ByteBuffer buf) {
    int len = buf.getInt();
    return IntStream.range(0, len).mapToObj(_i -> FfiConverterTypeIndex.INSTANCE.read(buf)).toList();
  }

  @Override
  public long allocationSize(List<Index> value) {
    long sizeForLength = 4L;
    long sizeForItems = value.stream().mapToLong(inner -> FfiConverterTypeIndex.INSTANCE.allocationSize(inner)).sum();
    return sizeForLength + sizeForItems;
  }

  @Override
  public void write(List<Index> value, ByteBuffer buf) {
    buf.putInt(value.size());
    value.forEach(inner -> FfiConverterTypeIndex.INSTANCE.write(inner, buf));
  }
}



/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
