package org.example.watchstack.priority;

import org.example.watchstack.entity.PriorityType;
import org.springframework.stereotype.Component;

@Component
public class LowPriority implements PriorityLevel {
    @Override
    public int getPriorityValue() {
        return PriorityType.LOW.getValue();
    }
}
