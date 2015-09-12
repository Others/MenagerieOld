package menagerie.genome;

public final class ApproxGenome {
	public final Genome equivelent;
	public final GenomeSpec spec;
	
	
	private final double[] genes;
	
	
	protected ApproxGenome(GenomeSpec spec, double[] genes, Genome equivelent){
		this.spec = spec;
		this.genes = genes;
		this.equivelent = equivelent;
	}	
	
	public double get(int gene){
		return genes[gene];
	}
	
	public int size(){
		return genes.length;
	}
}
