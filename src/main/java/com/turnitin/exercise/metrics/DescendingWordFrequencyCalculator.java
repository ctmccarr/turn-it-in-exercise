package com.turnitin.exercise.metrics;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/**
 * This is a WordFrequencyCalculator implementation which calculates the
 * frequency of each word in the text and returns a map of the results in
 * descending frequency order. This bean is the default (primary) implementation
 * provided by this exercise.
 */
@Component
@Primary
public class DescendingWordFrequencyCalculator implements WordFrequencyCalculator {

	@Override
	public Map<String, Integer> apply(String text) {
		// Sanitize the text to remove non-alphanumeric, space, or hyphens. This also
		// replaces all series of whitespace characters with a single space and strip
		// any leading and trailing whitespace
		String sanitizedText = text.replaceAll("[^A-Za-z0-9\\-\\s]", " ").replaceAll("\\s{2,}", " ").strip();

		// Split the text into a list of words
		List<String> words = Stream.of(sanitizedText.split("\\s")).collect(Collectors.toList());

		// Calculate the frequency of each word
		Map<String, Integer> frequencies = words.stream()
				.collect(Collectors.toMap(word -> word, word -> 1, Integer::sum));

		// Sort the map by descending frequency and ascending word and collect in a
		// LinkedHashMap to maintain sort order
		Map<String, Integer> orderedFrequencies = frequencies.entrySet().stream()
				.sorted(Map.Entry.<String, Integer>comparingByValue().reversed()
						.thenComparing(Map.Entry.<String, Integer>comparingByKey()))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (v1, v2) -> v1, LinkedHashMap::new));

		return orderedFrequencies;
	}

}
