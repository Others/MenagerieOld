package menagerie.genome;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public final class Genome {
	public final GenomeSpec spec;
	
	private final long[] genes;
	private long score = -1;
	
	protected Genome(GenomeSpec spec, long[] genes){
		this.spec = spec;
		this.genes = genes;
	}
	
	public int size(){
		return genes.length;
	}
	
	public long get(int gene){
		return genes[gene];
	}
	
	public long[] getGenes(){
		return Arrays.copyOf(genes, genes.length);
	}
	
	public long score(){
		if(score == -1){
			score = spec.score(this);
		}
		return score;
	}
	
	public Optional<Genome> update(int i, long v){
		long[] newGenes = getGenes();
		newGenes[i] = v;
		return spec.makeGenome(newGenes);
	}
	
	public Optional<Genome> bitNeighbor(int gene){
		int highestFlipableBit = 64 - Long.numberOfLeadingZeros(spec.getLimit(gene));
		int bitToFlip = spec.rgen.nextInt(highestFlipableBit);
		long neighborVal = get(gene) ^ (1L << bitToFlip);
		return update(gene, neighborVal);
	}
	
	public Optional<Genome> neighbor(int gene){
		long neighborVal = (get(gene) + 1) % spec.getLimit(gene);
		return update(gene, neighborVal);
	}
	
	@Override
	public boolean equals(Object o){
		if(o instanceof Genome){
			return spec.equals(((Genome) o).spec) && Arrays.equals(genes, ((Genome) o).genes);
		}
		return false;
	}
	
	@Override
	public int hashCode(){
		return spec.hashCode() ^ Arrays.hashCode(genes);
	}
	
	@Override
	public String toString(){
		List<String> parts = new ArrayList<>(size());
		for(int i = 0; i < size(); i++){
			parts.add(String.format("%s : %s", spec.getLabel(i), get(i)));
		}
		return "{" + String.join(",", parts) + "}";
	}
}
