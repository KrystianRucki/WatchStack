package org.example.watchstack.entity;

public enum PriorityType {
    LOW(1),
    MEDIUM(5),
    HIGH(10);

    private final int value;
    PriorityType(int value) { this.value = value; }
    public int getValue() { return value; }
}
