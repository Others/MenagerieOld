package menagerie.metaheuristic;

import java.time.Duration;
import java.time.Instant;

import menagerie.genome.Genome;
import menagerie.genome.GenomeSpec;

public interface IndividualMetaheuristic{
	public Genome optimizeIndividual(GenomeSpec spec, Genome genome, Instant endTime);
	
	public default Genome optimizeIndividual(GenomeSpec spec, Genome genome, Duration timeBudget){
		return optimizeIndividual(spec, genome, Instant.now().plus(timeBudget));
	}
	
	public default Genome optimizeIndividual(GenomeSpec spec, Instant endTime){
		return optimizeIndividual(spec, spec.makeRandomGenome(), endTime);
	}
	
	public default Genome optimizeIndividual(GenomeSpec spec, Duration timeBudget){
		return optimizeIndividual(spec, Instant.now().plus(timeBudget));
	}
}
