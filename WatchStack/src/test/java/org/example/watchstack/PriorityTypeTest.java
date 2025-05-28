package org.example.watchstack;

import org.example.watchstack.entity.PriorityType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PriorityTypeTest {

    @Test
    @DisplayName("Enum values should match names")
    void testEnumNames() {
        assertEquals("LOW", PriorityType.LOW.name());
        assertEquals("MEDIUM", PriorityType.MEDIUM.name());
        assertEquals("HIGH", PriorityType.HIGH.name());
    }

    @Test
    @DisplayName("getValue() returns correct numeric value")
    void testGetValue() {
        assertEquals(1, PriorityType.LOW.getValue());
        assertEquals(5, PriorityType.MEDIUM.getValue());
        assertEquals(10, PriorityType.HIGH.getValue());
    }

    @Test
    @DisplayName("valueOf() throws on invalid name")
    void testInvalidValueOf() {
        assertThrows(IllegalArgumentException.class, () -> PriorityType.valueOf("INVALID"));
    }
}
