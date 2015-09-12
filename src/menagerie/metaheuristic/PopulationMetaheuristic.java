package menagerie.metaheuristic;

import java.time.Duration;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import menagerie.genome.Genome;
import menagerie.genome.GenomeSpec;

public abstract class PopulationMetaheuristic {
	
	public abstract Set<Genome> optimizePopulation(GenomeSpec spec, Set<Genome> pool, Instant endTime);
	
	public final Set<Genome> optimizePopulation(GenomeSpec spec, Set<Genome> pool, Duration timeBudget){
		return optimizePopulation(spec, pool, Instant.now().plus(timeBudget));
	}
	
	public final Set<Genome> optimizePopulation(GenomeSpec spec, int poolSize, Duration timeBudget){
		return optimizePopulation(spec, getGenomes(spec, poolSize), timeBudget);
	}
	
	public final Set<Genome> optimizePopulation(GenomeSpec spec, int poolSize, Instant endTime){
		return optimizePopulation(spec, getGenomes(spec, poolSize), endTime);
	}
	
	public Set<Genome> getGenomes(GenomeSpec spec, int count){
		Set<Genome> genomes = new HashSet<>(count);
		for(int i=0; i < count; i++){
			genomes.add(spec.makeRandomGenome());
		}
		return genomes;
	}
}
