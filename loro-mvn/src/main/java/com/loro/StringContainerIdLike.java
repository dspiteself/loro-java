package com.loro;

import java.util.Objects;

public record StringContainerIdLike(String x) implements ContainerIdLike {
    public StringContainerIdLike {
        Objects.requireNonNull(x);
    }
    public ContainerId asContainerId( ContainerType ty) {
        return new ContainerId.Root(x, ty);
    }
}