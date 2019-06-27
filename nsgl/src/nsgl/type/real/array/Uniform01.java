/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nsgl.type.real.array;

import nsgl.random.raw.RawGenerator;

/**
 *
 * @author jgomez
 */
public class Uniform01{
	
    public double[] create( int n ){ return RawGenerator.cast(this).raw(n); }
    
    public double[] create( double[] x ) { return create(x.length); }
}