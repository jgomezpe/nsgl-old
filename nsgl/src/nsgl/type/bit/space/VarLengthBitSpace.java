package nsgl.type.bit.space;

import nsgl.random.raw.RawGenerator;
import nsgl.search.space.Space;
import nsgl.type.bit.BitArray;

public class VarLengthBitSpace implements Space<BitArray> {
	protected int minLength;
	protected int maxVarGenes;
	protected int gene_size;
	
	public VarLengthBitSpace( int minLength, int maxLength ){ this(minLength, maxLength, 1); }

	public VarLengthBitSpace( int minLength, int maxLength, int gene_size ){
		this.minLength = minLength;
		this.gene_size = gene_size;
		this.maxVarGenes = (maxLength-minLength)/gene_size;
	}
	
	@Override
	public boolean feasible(BitArray x) {
		return minLength <= x.size() && x.size()<=minLength+maxVarGenes*gene_size;
	}

	@Override
	public double feasibility(BitArray x) {
		return feasible(x)?1:0;
	}

	@Override
	public BitArray repair(BitArray x) {
		int maxLength = minLength + maxVarGenes * gene_size;
		if( x.size() > maxLength ) return x.subBitArray(0,maxLength);
		if( x.size() < minLength ){
			BitArray x2 = new BitArray(minLength, true);
			try{ for( int i=0; i<minLength;i++)	x2.set(i,x.get(i)); }catch(Exception e){}
			return x2;
		}else return x;
	}
	
	
	@Override
	public BitArray pick() {
		return (maxVarGenes>0)?new BitArray(minLength+RawGenerator.cast(this).integer(maxVarGenes*gene_size), true):new BitArray(minLength, true);
	}
}