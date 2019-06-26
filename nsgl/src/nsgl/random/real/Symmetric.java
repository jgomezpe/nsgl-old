/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nsgl.random.real;

import nsgl.random.bit.RandBit;

//
//Unified Random generation Pack 1.0 by Jonatan Gómez-Perdomo
//https://github.com/jgomezpe/unalcol/tree/master/random/
//
/**
 *
 * @author jgomez
 */
public class Symmetric implements RandReal {
	protected RandBit side;
	protected RandReal one_side;
	
	public Symmetric(){ this( new RandBit(), new StdUniform()); }
	
	public Symmetric( RandReal one_side ){ this( new RandBit(), one_side ); }
	
	public Symmetric( RandBit side, RandReal one_side ){
		this.side = side;
		this.one_side = one_side;
	}
	
   /**
     * Returns a random double number
     * @return A random double number
     */
    @Override
    public Double next(){ return side.next()?one_side.next():-one_side.next(); }  
}