package menagerie.genome;

import java.util.Optional;
import java.util.Random;

public abstract class GenomeSpec {
	public final Random rgen = new Random();
	
	private final long[] geneLimits;
	private final String[] geneLabels;
	
	public GenomeSpec(long[] limits){
		this(limits, null);
	}
	
	public GenomeSpec(long[] limits, String[] labels){
		for(long spec:limits){
			if(spec <= 0){
				throw new IllegalArgumentException("Gene limits must be greater than 0");
			}
		}
		if(labels == null){
			labels = new String[limits.length];
			for(int i=1; i <= limits.length; i++){
				labels[i] = i + "";
			}
		}
		geneLimits = limits;
		geneLabels = labels;
	}
	
	public final String getLabel(int i){
		return geneLabels[i];
	}
	
	public final long getLimit(int i){
		return geneLimits[i];
	}
	
	public final int size(){
		return geneLabels.length;
	}
	
	public final Optional<Genome> makeGenome(long[] genes){
		Genome g = new Genome(this, genes);
		if(!validate(g)){
			return Optional.empty();
		}
		return Optional.of(g);
	}
	
	public final Optional<ApproxGenome> makeGenome(double[] genes){
		long[] longGenes = new long[genes.length];
		for(int i = 0; i < genes.length; i++){
			long geneVal = Math.round(genes[i]);
			if(geneVal >= getLimit(i)){
				geneVal = getLimit(i) - 1;
			}
			longGenes[i] = geneVal;
		}
		return makeGenome(longGenes).map(equivelent -> new ApproxGenome(this, genes, equivelent));
	}
	
	public final Genome makeRandomGenome(){
		Optional<Genome> random = Optional.empty();
		do{
			long[] randomLongs = new long[size()];
			for(int i = 0; i < size(); i++){
				randomLongs[i] = Math.abs(rgen.nextLong()) % getLimit(i);
			}
			random = makeGenome(randomLongs);
		}while(!random.isPresent());
		return random.get();
	}
	
	public final boolean validate(Genome g){
		if(g.size() != size()){
			return false;
		}
		for(int gene=0; gene < size(); gene++){
			if(g.get(gene) >= getLimit(gene)){
				return false;
			}
		}
		return verify(g);
	}
	
	protected abstract long score(Genome g);
	
	protected boolean verify(Genome g){
		return true;
	}
}
