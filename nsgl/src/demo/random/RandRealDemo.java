/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.random;

import nsgl.type.real.random.RandReal;
import nsgl.type.real.random.StdGaussian;
import nsgl.type.real.random.StdPowerLaw;
import nsgl.type.real.random.Symmetric;
import nsgl.type.real.random.UniformReal;

/**
 *
 * @author jgomez
 */
public class RandRealDemo{
	public static RandReal uniform(){
		System.out.println( "********************Uniform********************" );
		return  new UniformReal(0.0,2.0);
	}  
  
	public static RandReal gaussian(){
		System.out.println( "********************Gaussian********************" );
		return  new StdGaussian();
	}  
  
	public static RandReal power_law(){
		System.out.println( "********************Power Law********************" );
		return new StdPowerLaw();
	}
    
	public static RandReal symmetric_power_law(){
	    System.out.println( "**********************Symmetric Power Law********************" );
	    Symmetric g = new Symmetric(new StdPowerLaw());
	    return g;
	}
	
	public static void test( RandReal g ){
		int n = 10;
		// Generating an array of ten random values
		double[] x = g.generate(n);
		for( int i=0; i<n; i++ ){
			System.out.println( x[i] );
		}
		System.out.println("****************");
		// Generating ten random values
		for( int i=0; i<n; i++ ){
			System.out.println(g.next());
		}
	}
      
	public static void main( String[] args ){
		test( uniform() );
		test( gaussian() );
		test( power_law() );
		test( symmetric_power_law() );
	}    
}