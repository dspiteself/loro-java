package com.loro;


import java.nio.ByteBuffer;

public enum FfiConverterTypeLoroError implements FfiConverterRustBuffer<LoroException> {
    INSTANCE;

    @Override
    public LoroException read(ByteBuffer buf) {
        return switch(buf.getInt()) {
            case 1 -> new LoroException.UnmatchedContext(FfiConverterString.INSTANCE.read(buf));
            case 2 -> new LoroException.DecodeVersionVectorException(FfiConverterString.INSTANCE.read(buf));
            case 3 -> new LoroException.DecodeException(FfiConverterString.INSTANCE.read(buf));
            case 4 -> new LoroException.DecodeDataCorruptionException(FfiConverterString.INSTANCE.read(buf));
            case 5 -> new LoroException.DecodeChecksumMismatchException(FfiConverterString.INSTANCE.read(buf));
            case 6 -> new LoroException.IncompatibleFutureEncodingException(FfiConverterString.INSTANCE.read(buf));
            case 7 -> new LoroException.JsException(FfiConverterString.INSTANCE.read(buf));
            case 8 -> new LoroException.LockException(FfiConverterString.INSTANCE.read(buf));
            case 9 -> new LoroException.DuplicatedTransactionException(FfiConverterString.INSTANCE.read(buf));
            case 10 -> new LoroException.NotFoundException(FfiConverterString.INSTANCE.read(buf));
            case 11 -> new LoroException.TransactionException(FfiConverterString.INSTANCE.read(buf));
            case 12 -> new LoroException.OutOfBound(FfiConverterString.INSTANCE.read(buf));
            case 13 -> new LoroException.UsedOpId(FfiConverterString.INSTANCE.read(buf));
            case 14 -> new LoroException.TreeException(FfiConverterString.INSTANCE.read(buf));
            case 15 -> new LoroException.ArgErr(FfiConverterString.INSTANCE.read(buf));
            case 16 -> new LoroException.AutoCommitNotStarted(FfiConverterString.INSTANCE.read(buf));
            case 17 -> new LoroException.StyleConfigMissing(FfiConverterString.INSTANCE.read(buf));
            case 18 -> new LoroException.Unknown(FfiConverterString.INSTANCE.read(buf));
            case 19 -> new LoroException.FrontiersNotFound(FfiConverterString.INSTANCE.read(buf));
            case 20 -> new LoroException.ImportWhenInTxn(FfiConverterString.INSTANCE.read(buf));
            case 21 -> new LoroException.MisuseDetachedContainer(FfiConverterString.INSTANCE.read(buf));
            case 22 -> new LoroException.NotImplemented(FfiConverterString.INSTANCE.read(buf));
            case 23 -> new LoroException.ReattachAttachedContainer(FfiConverterString.INSTANCE.read(buf));
            case 24 -> new LoroException.EditWhenDetached(FfiConverterString.INSTANCE.read(buf));
            case 25 -> new LoroException.UndoInvalidIdSpan(FfiConverterString.INSTANCE.read(buf));
            case 26 -> new LoroException.UndoWithDifferentPeerId(FfiConverterString.INSTANCE.read(buf));
            case 27 -> new LoroException.InvalidJsonSchema(FfiConverterString.INSTANCE.read(buf));
            case 28 -> new LoroException.Utf8InUnicodeCodePoint(FfiConverterString.INSTANCE.read(buf));
            case 29 -> new LoroException.Utf16InUnicodeCodePoint(FfiConverterString.INSTANCE.read(buf));
            case 30 -> new LoroException.EndIndexLessThanStartIndex(FfiConverterString.INSTANCE.read(buf));
            case 31 -> new LoroException.InvalidRootContainerName(FfiConverterString.INSTANCE.read(buf));
            case 32 -> new LoroException.ImportUpdatesThatDependsOnOutdatedVersion(FfiConverterString.INSTANCE.read(buf));
            case 33 -> new LoroException.SwitchToVersionBeforeShallowRoot(FfiConverterString.INSTANCE.read(buf));
            case 34 -> new LoroException.ContainerDeleted(FfiConverterString.INSTANCE.read(buf));
            case 35 -> new LoroException.ConcurrentOpsWithSamePeerId(FfiConverterString.INSTANCE.read(buf));
            case 36 -> new LoroException.InvalidPeerId(FfiConverterString.INSTANCE.read(buf));
            case 37 -> new LoroException.ContainersNotFound(FfiConverterString.INSTANCE.read(buf));
            default -> throw new RuntimeException("invalid error enum value, something is very wrong!!");
        };
    }

    @Override
    public long allocationSize(LoroException value) {
        return 4L;
    }

    @Override
    public void write(LoroException value, ByteBuffer buf) {
        switch(value) {
            case LoroException.UnmatchedContext x -> {
                buf.putInt(1);
            }
            case LoroException.DecodeVersionVectorException x -> {
                buf.putInt(2);
            }
            case LoroException.DecodeException x -> {
                buf.putInt(3);
            }
            case LoroException.DecodeDataCorruptionException x -> {
                buf.putInt(4);
            }
            case LoroException.DecodeChecksumMismatchException x -> {
                buf.putInt(5);
            }
            case LoroException.IncompatibleFutureEncodingException x -> {
                buf.putInt(6);
            }
            case LoroException.JsException x -> {
                buf.putInt(7);
            }
            case LoroException.LockException x -> {
                buf.putInt(8);
            }
            case LoroException.DuplicatedTransactionException x -> {
                buf.putInt(9);
            }
            case LoroException.NotFoundException x -> {
                buf.putInt(10);
            }
            case LoroException.TransactionException x -> {
                buf.putInt(11);
            }
            case LoroException.OutOfBound x -> {
                buf.putInt(12);
            }
            case LoroException.UsedOpId x -> {
                buf.putInt(13);
            }
            case LoroException.TreeException x -> {
                buf.putInt(14);
            }
            case LoroException.ArgErr x -> {
                buf.putInt(15);
            }
            case LoroException.AutoCommitNotStarted x -> {
                buf.putInt(16);
            }
            case LoroException.StyleConfigMissing x -> {
                buf.putInt(17);
            }
            case LoroException.Unknown x -> {
                buf.putInt(18);
            }
            case LoroException.FrontiersNotFound x -> {
                buf.putInt(19);
            }
            case LoroException.ImportWhenInTxn x -> {
                buf.putInt(20);
            }
            case LoroException.MisuseDetachedContainer x -> {
                buf.putInt(21);
            }
            case LoroException.NotImplemented x -> {
                buf.putInt(22);
            }
            case LoroException.ReattachAttachedContainer x -> {
                buf.putInt(23);
            }
            case LoroException.EditWhenDetached x -> {
                buf.putInt(24);
            }
            case LoroException.UndoInvalidIdSpan x -> {
                buf.putInt(25);
            }
            case LoroException.UndoWithDifferentPeerId x -> {
                buf.putInt(26);
            }
            case LoroException.InvalidJsonSchema x -> {
                buf.putInt(27);
            }
            case LoroException.Utf8InUnicodeCodePoint x -> {
                buf.putInt(28);
            }
            case LoroException.Utf16InUnicodeCodePoint x -> {
                buf.putInt(29);
            }
            case LoroException.EndIndexLessThanStartIndex x -> {
                buf.putInt(30);
            }
            case LoroException.InvalidRootContainerName x -> {
                buf.putInt(31);
            }
            case LoroException.ImportUpdatesThatDependsOnOutdatedVersion x -> {
                buf.putInt(32);
            }
            case LoroException.SwitchToVersionBeforeShallowRoot x -> {
                buf.putInt(33);
            }
            case LoroException.ContainerDeleted x -> {
                buf.putInt(34);
            }
            case LoroException.ConcurrentOpsWithSamePeerId x -> {
                buf.putInt(35);
            }
            case LoroException.InvalidPeerId x -> {
                buf.putInt(36);
            }
            case LoroException.ContainersNotFound x -> {
                buf.putInt(37);
            }
            default -> throw new RuntimeException("invalid error enum value, something is very wrong!!");
        };
    }
}




