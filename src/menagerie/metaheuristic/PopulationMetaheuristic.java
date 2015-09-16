package menagerie.metaheuristic;

import java.time.Duration;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import menagerie.genome.Genome;
import menagerie.genome.GenomeSpec;

public interface PopulationMetaheuristic {
	public Set<Genome> optimizePopulation(GenomeSpec spec, Set<Genome> pool, Instant endTime);
	
	public default Set<Genome> optimizePopulation(GenomeSpec spec, Set<Genome> pool, Duration timeBudget){
		return optimizePopulation(spec, pool, Instant.now().plus(timeBudget));
	}
	
	public default Set<Genome> optimizePopulation(GenomeSpec spec, int poolSize, Duration timeBudget){
		return optimizePopulation(spec, getGenomes(spec, poolSize), timeBudget);
	}
	
	public default Set<Genome> optimizePopulation(GenomeSpec spec, int poolSize, Instant endTime){
		return optimizePopulation(spec, getGenomes(spec, poolSize), endTime);
	}
	
	public default Set<Genome> getGenomes(GenomeSpec spec, int count){
		Set<Genome> genomes = new HashSet<>(count);
		for(int i=0; i < count; i++){
			genomes.add(spec.makeRandomGenome());
		}
		return genomes;
	}
}
