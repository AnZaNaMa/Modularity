package com.anzanama.modularity.common.part;

public enum EnumPlacement {
    NORTH(0),
    EAST(1),
    SOUTH(2),
    WEST(3),
    UP(4),
    DOWN(5),
    CENTER(6);
    private int value;
    private EnumPlacement(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }
}
