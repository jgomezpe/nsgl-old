package nsgl.type.real.space;

import nsgl.search.multilevel.CodeDecodeMap;
import nsgl.type.real.Real;
import nsgl.type.real.array.RealVectorLinealScale;

public class ScaledHyperCube extends CodeDecodeMap<double[], double[]> {
    protected RealVectorLinealScale scale = null;
	
	public ScaledHyperCube( double[] min, double[] max ){
		this.scale = new RealVectorLinealScale(min, max);
	}
	
	public ScaledHyperCube( HyperCube space ){
		this.scale = new RealVectorLinealScale(space.min, space.max);
	}
	
	/**
	  * Generates a thing from the given genome
	  * @param genome Genome of the thing to be expressed
	  * @return A thing expressed from the genome
	  */
	public double[] decode(double[] genome) {
		  return scale.inverse(genome); 
	}

	  /**
	   * Generates a genome from the given thing
	   * @param thing A thing expressed from the genome
	   * @return Genome of the thing
	   */
	public double[] code(double[] thing) {
		return scale.apply(thing); 
	}

	
	public static void main( String[] args){
		ScaledHyperCube scale = new ScaledHyperCube(Real.create(5, -10.0), Real.create(5, 10.0));
		for( int i=-10; i<=10; i++){
			double[] x = scale.code(Real.create(5, (double)i));
			for( int k=0; k<x.length; k++ ){ 
				System.out.print(" "+x[k]);
			}
			System.out.println();
		}	
	}
}
