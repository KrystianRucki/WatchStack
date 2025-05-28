package org.example.watchstack;

import org.example.watchstack.priority.*;
import org.example.watchstack.entity.PriorityType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PriorityLevelImplTest {

    @Test
    @DisplayName("LowPriority returns LOW value")
    void testLowPriority() {
        PriorityLevel low = new LowPriority();
        assertEquals(PriorityType.LOW.getValue(), low.getPriorityValue());
    }

    @Test
    @DisplayName("MediumPriority returns MEDIUM value")
    void testMediumPriority() {
        PriorityLevel medium = new MediumPriority();
        assertEquals(PriorityType.MEDIUM.getValue(), medium.getPriorityValue());
    }

    @Test
    @DisplayName("HighPriority returns HIGH value")
    void testHighPriority() {
        PriorityLevel high = new HighPriority();
        assertEquals(PriorityType.HIGH.getValue(), high.getPriorityValue());
    }
}
