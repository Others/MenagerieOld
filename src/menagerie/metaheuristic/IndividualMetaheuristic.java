package menagerie.metaheuristic;

import java.time.Duration;
import java.time.Instant;
import java.util.Set;
import java.util.stream.Collectors;

import menagerie.genome.Genome;
import menagerie.genome.GenomeSpec;

public abstract class IndividualMetaheuristic extends PopulationMetaheuristic{
	public abstract Genome optimizeIndividual(GenomeSpec spec, Genome genome, Instant endTime);
	
	public final Genome optimizeIndividual(GenomeSpec spec, Genome genome, Duration timeBudget){
		return optimizeIndividual(spec, genome, Instant.now().plus(timeBudget));
	}
	
	public Set<Genome> optimizePopulation(GenomeSpec spec, Set<Genome> pool, Instant endTime){
		Duration perGenome = Duration.between(Instant.now(), endTime).dividedBy(pool.size());
		return pool.parallelStream().map(g -> this.optimizeIndividual(spec, g, perGenome)).collect(Collectors.toSet());
	} 
}
