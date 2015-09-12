package menagerie.test;

import java.time.Duration;
import java.util.Set;

import menagerie.genome.Genome;
import menagerie.metaheuristic.impl.RandomClimber;
import menagerie.problem.Quadratic;

public final class Test {
	public static void main(String[] args) {
		Quadratic q = new Quadratic(1, -10, 3, 100);
		Set<Genome> genomes = new RandomClimber().optimizePopulation(q, 100, Duration.ofSeconds(10));
		System.out.println(genomes);
	}
}