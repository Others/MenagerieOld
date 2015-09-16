package menagerie.metaheuristic.impl.climber;

import java.time.Instant;
import java.util.Optional;

import menagerie.genome.Genome;
import menagerie.genome.GenomeSpec;
import menagerie.metaheuristic.IndividualMetaheuristic;

public final class SystematicClimber implements IndividualMetaheuristic{
	@Override
	public Genome optimizeIndividual(GenomeSpec spec, Genome individual, Instant endTime) {
		while(endTime.isAfter(Instant.now())){
			Genome start = individual;
			Genome improvment = start;
			for(int gene=0; gene < spec.size(); gene++){
				Optional<Genome> neighborOption = individual.neighbor(gene);
				if(neighborOption.isPresent()){
					Genome neighbor = neighborOption.get();
					if(neighbor.score() < individual.score()){
						individual = neighbor;
					}
				}
			}
			if(start == improvment){
				return start;
			}
			start = improvment;
		}
		return individual;
	}
}
