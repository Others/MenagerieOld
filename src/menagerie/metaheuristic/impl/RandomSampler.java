package menagerie.metaheuristic.impl;

import java.time.Instant;

import menagerie.genome.Genome;
import menagerie.genome.GenomeSpec;
import menagerie.metaheuristic.IndividualMetaheuristic;;

public final class RandomSampler implements IndividualMetaheuristic{
	@Override
	public Genome optimizeIndividual(GenomeSpec spec, Genome individual, Instant endTime) {
		while(endTime.isAfter(Instant.now())){
			Genome randomGenome = spec.makeRandomGenome();
			if(randomGenome.score() < individual.score()){
				individual = randomGenome;
			}
		}
		return individual;
	}
}
