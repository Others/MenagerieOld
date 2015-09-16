package menagerie.test;

import java.time.Duration;

import menagerie.genome.Genome;
import menagerie.metaheuristic.impl.climber.RandomClimber;
import menagerie.problem.Quadratic;

public final class Test {
	public static void main(String[] args) {
		Quadratic q = new Quadratic(1, -10, 3, 100);
		Genome genome = new RandomClimber().optimizeIndividual(q, Duration.ofSeconds(10));
		System.out.println(genome);
	}
}