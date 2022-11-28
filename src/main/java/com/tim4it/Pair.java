package com.tim4it;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

/**
 * A convenience class to represent name-value pairs - holds two objects
 */
@Value
@Builder(toBuilder = true)
@AllArgsConstructor
public class Pair<K, V> {

    K first;
    V second;
}
