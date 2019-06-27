package demo.random;

import nsgl.type.bit.random.RandBit;

public class RandBitDemo {
	public static void main( String[] args ){
		// false is generated with probability 0.7
		// true is generated with probability 0.3
		RandBit g = new RandBit(0.7);
		int n = 10;
		// Generating an array of ten random values
		boolean[] x = g.generate(n);
		for( int i=0; i<n; i++ ){
			System.out.println( x[i] );
		}
		System.out.println("****************");
		// Generating ten random values
		for( int i=0; i<n; i++ ){
			System.out.println(g.next());
		}		
	}
}