package com.tim4it;

import lombok.NonNull;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class ConsecutiveNumbers {

    private final String numberString;
    private final int numberStringLength;
    private final int numberStringHalfLength;
    private final String preDefinedAscendingConsecutiveNumbers;
    private final String preDefinedDescendingConsecutiveNumbers;

    public ConsecutiveNumbers(String numberString) {
        this.numberString = numberString;
        this.numberStringLength = numberString.length();
        this.numberStringHalfLength = this.numberStringLength / 2;
        this.preDefinedAscendingConsecutiveNumbers = IntStream.range(0, 150001)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining());
        this.preDefinedDescendingConsecutiveNumbers = IntStream.range(0, 150001)
                .boxed()
                .sorted(Comparator.reverseOrder())
                .map(String::valueOf)
                .collect(Collectors.joining());
    }

    /**
     * First version - simplified
     *
     * @return true if we have ascending, descending consecutive numbers
     */
    public boolean apply() {
        for (int i = 0; i < this.numberStringHalfLength; i++) {
            // from units, tens, hundreds, thousands, ten thousands,...
            var newSubstring = this.numberString.substring(0, (i + 1));
            // parse string to number
            var parsedNumber = parseLong(newSubstring);
            // builder for ascending number
            var ascendingBuilder = new StringBuilder(newSubstring);
            // builder for descending number
            var descendingBuilder = new StringBuilder(newSubstring);
            // ascending counter - from main number and up
            var ascending = parsedNumber;
            // descending counter - from main number and down
            var descending = parsedNumber;
            while (Math.min(ascendingBuilder.length(), descendingBuilder.length()) < this.numberStringLength) {
                // append next ascending number
                ascendingBuilder.append(++ascending);
                // append next descending number
                descendingBuilder.append(--descending);
                // check if ascending and descending string is equals to original string
                if (ascendingBuilder.toString().equals(this.numberString) ||
                        descendingBuilder.toString().equals(this.numberString)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Second version - using streams, more complex (you need to see complex solutions to find simpler ones), compiler
     * optimized
     *
     * @return true if we have ascending, descending consecutive numbers
     */
    public boolean applyStream() {
        return IntStream.range(0, this.numberStringHalfLength)
                .mapToObj(this::getAscendingDescendingListPair)
                .map(this::getAscendingDescendingConsecutivePair)
                .anyMatch(pair ->
                        pair.getFirst().equals(this.numberString) ||
                                pair.getSecond().equals(this.numberString));
    }

    /**
     * Third version - using streams, more readable, compiler optimized
     *
     * @return true if we have ascending, descending consecutive numbers
     */
    public boolean applyStreamSimple() {
        return IntStream.range(0, this.numberStringHalfLength)
                .mapToObj(this::getAscendingDescendingConsecutivePair)
                .anyMatch(pair ->
                        pair.getFirst().equals(this.numberString) ||
                                pair.getSecond().equals(this.numberString));
    }

    /**
     * Working correct for consecutive numbers, but not correct for numbers that are not consecutive
     *
     * @return true if we have ascending, descending consecutive numbers
     */
    public boolean applyFastLimited() {
        return this.preDefinedAscendingConsecutiveNumbers.contains(this.numberString) ||
                this.preDefinedDescendingConsecutiveNumbers.contains(this.numberString);
    }

    /**
     * Create a pair of ascending and descending list
     *
     * @param idx current index from main loop
     * @return pair of ascending and descending string builders
     */
    private Pair<List<Long>, List<Long>> getAscendingDescendingListPair(int idx) {
        var newSubstring = this.numberString.substring(0, (idx + 1));
        var forAscending = new ArrayList<Long>();
        var forDescending = new ArrayList<Long>();

        var parsedNumber = parseLong(newSubstring);
        forAscending.add(parsedNumber);
        forDescending.add(parsedNumber);

        var ascending = parsedNumber;
        var descending = parsedNumber;
        var ascendingBuilder = new StringBuilder(newSubstring);
        var descendingBuilder = new StringBuilder(newSubstring);
        while (Math.min(ascendingBuilder.length(), descendingBuilder.length()) < this.numberStringLength) {
            ascending++;
            descending--;
            ascendingBuilder.append(ascending);
            descendingBuilder.append(descending);
            forAscending.add(ascending);
            forDescending.add(descending);
            if (ascendingBuilder.toString().equals(this.numberString) ||
                    descendingBuilder.toString().equals(this.numberString)) {
                return new Pair<>(List.copyOf(forAscending), List.copyOf(forDescending));
            }
        }
        return new Pair<>(List.copyOf(forAscending), List.copyOf(forDescending));
    }

    /**
     * Flatten out list of longs to String - for ascending and descending long lists
     *
     * @param pair pair of ascending and descending consecutive cumbers
     * @return flatten list of longs to string - pair of ascending and descending numbers
     */
    private Pair<String, String> getAscendingDescendingConsecutivePair(@NonNull Pair<List<Long>, List<Long>> pair) {
        var ascendingConsecutiveNumbers = pair.getFirst()
                .stream()
                .map(Object::toString)
                .collect(Collectors.joining());
        var descendingConsecutiveNumbers = pair.getSecond()
                .stream()
                .map(Object::toString)
                .collect(Collectors.joining());
        return new Pair<>(ascendingConsecutiveNumbers, descendingConsecutiveNumbers);
    }

    private Pair<String, String> getAscendingDescendingConsecutivePair(int idx) {
        var newSubstring = this.numberString.substring(0, (idx + 1));
        var parsedNumber = parseLong(newSubstring);
        var ascendingBuilder = new StringBuilder(newSubstring);
        var descendingBuilder = new StringBuilder(newSubstring);
        var ascending = parsedNumber;
        var descending = parsedNumber;
        while (Math.min(ascendingBuilder.length(), descendingBuilder.length()) < this.numberStringLength) {
            ascendingBuilder.append(++ascending);
            descendingBuilder.append(--descending);
            if (ascendingBuilder.toString().equals(this.numberString) ||
                    descendingBuilder.toString().equals(this.numberString)) {
                return new Pair<>(ascendingBuilder.toString(), descendingBuilder.toString());
            }
        }
        return new Pair<>(ascendingBuilder.toString(), descendingBuilder.toString());
    }

    /**
     * Parse long number from string
     *
     * @param numberString number string
     * @return parsed long number
     */
    private long parseLong(@NonNull String numberString) {
        try {
            return Long.parseUnsignedLong(numberString);
        } catch (Exception e) {
            throw new IllegalStateException("Error parsing number '" + numberString + "' to long!", e);
        }
    }
}
