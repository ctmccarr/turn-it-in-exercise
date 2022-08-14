package com.turnitin.exercise.metrics;

import java.util.Map;
import java.util.function.Function;

/**
 * A functional interface for accepting a line of text and returning a map of
 * each word in the text to the frequency that the word is found in the text.
 */
@FunctionalInterface
public interface WordFrequencyCalculator extends Function<String, Map<String, Integer>> {
}
