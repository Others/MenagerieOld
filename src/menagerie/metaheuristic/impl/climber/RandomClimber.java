package menagerie.metaheuristic.impl.climber;

import java.time.Instant;
import java.util.Optional;

import menagerie.genome.Genome;
import menagerie.genome.GenomeSpec;
import menagerie.metaheuristic.IndividualMetaheuristic;

public final class RandomClimber implements IndividualMetaheuristic{
	@Override
	public Genome optimizeIndividual(GenomeSpec spec, Genome individual, Instant endTime) {
		while(endTime.isAfter(Instant.now())){
			int gene = spec.rgen.nextInt(spec.size());
			long geneVal = Math.abs(spec.rgen.nextLong()) % spec.getLimit(gene);
			Optional<Genome> candidateOption = individual.update(gene, geneVal);
			if(candidateOption.isPresent()){
				Genome newCandidate=candidateOption.get();
				if(newCandidate.score() < individual.score()){
					individual = newCandidate;
				}
			}
		}
		return individual;
	}
}
