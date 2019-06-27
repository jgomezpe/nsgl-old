package nsgl.type.bit.variation;

import nsgl.random.Shuffle;
import nsgl.search.variation.Variation_1_1;
import nsgl.type.bit.BitArray;
import nsgl.type.integer.random.RandInt;

public class IntensityMutation implements Variation_1_1<BitArray>{

	protected RandInt ri;
	
	public IntensityMutation(RandInt ri) { this.ri = ri; }
	
	@Override
	public BitArray apply(BitArray gen) {
		gen = new BitArray(gen);
		int k = ri.next() % gen.size();
		Shuffle<Object> s = new Shuffle<Object>();
		int[] idx = s.apply(gen.size());
		
		for( int i=0; i<k; i++ ){ gen.not(idx[i]); }
		return gen;
	}
	
}
