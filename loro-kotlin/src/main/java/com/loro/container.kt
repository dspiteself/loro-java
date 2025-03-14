package com.loro


data class StringContainerIdLike (val x :String): ContainerIdLike  {
    override fun asContainerId(ty: ContainerType): ContainerId {
        return ContainerId.Root(x, ty);
    }

}