package menagerie.problem;

import menagerie.genome.Genome;
import menagerie.genome.GenomeSpec;

public final class Quadratic extends GenomeSpec{
	public final long a;
	public final long b;
	public final long c;
	
	public Quadratic(long a, long b, long c, long limit) {
		super(new long[]{limit}, new String[]{"x"});
		this.a = a;
		this.b = b;
		this.c = c;
	}

	@Override
	protected long score(Genome g) {
		long x = g.get(0);
		return (a * x * x) + (b * x) + c;
	}

}
