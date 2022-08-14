package com.turnitin.exercise;

import java.util.Map;
import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.turnitin.exercise.metrics.WordFrequencyCalculator;

@SpringBootApplication
public class TurnItInExerciseApplication {

	public static void main(String[] args) {
		SpringApplication.run(TurnItInExerciseApplication.class, args);
	}

	@Bean
	protected CommandLineRunner wordFrequencyCalculator(WordFrequencyCalculator calculator) {
		return args -> {
			String text = null;

			// No need to use try-with-resources block since it's not necessary to close
			// system streams explicitly
			@SuppressWarnings("resource")
			Scanner inputScanner = new Scanner(System.in);

			do {
				System.out.print("Enter a phrase followed by the return key to calculate the frequency of each ");
				System.out.println("word or enter a blank phrase to exit the program");
				System.out.println();

				if ((text = inputScanner.nextLine()).length() > 0) {
					// Calculate the frequency of each word
					Map<String, Integer> frequencies = calculator.apply(text);

					System.out.println();

					// Display the results in the same order determined by the calculator
					// implementation
					frequencies.forEach((word, frequency) -> System.out.printf("%s %s\n", frequency, word));

					System.out.println();
				}
			} while (text.length() > 0);
		};
	}
}
