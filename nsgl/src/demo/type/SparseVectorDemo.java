package demo.type;

import nsgl.type.array.Vector;
import nsgl.type.collection.Collection;
import nsgl.type.object.Pair;
import nsgl.type.sparse.SparseArray;

public class SparseVectorDemo {
    public static void main( String[] args ){
		Vector<Integer> indices = new Vector<Integer>();
		// Creating a SparseVector 
		SparseArray<Integer> vector = new SparseArray<Integer>();
		// Adding data to randomly selected positions
		for( Integer i=0; i<100; i++ ){
		    int k = (int)(1000*Math.random());
		    indices.add( k );
		    vector.set(k,i);
		    System.out.println( k + ":" + i );
		}
		
		// Printing the collection of indices as it was generated
		for( int i : indices ){
		    System.out.print(" "+ i );
		}
		System.out.println();
		
		// Getting the final set of indices and its stored value
		Collection<Pair<Integer,Integer>> the_indices = vector.pairs();
		for( Pair<Integer,Integer> p : the_indices ){
			System.out.println( p.a() + ":" + p.b());
		}
		
		// Checking for some random positions
		// Handling the exception for checking index existence 		
		for( int i=0; i<100; i++ ){
			int k = (int)(1000*Math.random());
			try{
				System.out.println( k + "->" + vector.get(k) );
			}catch( ArrayIndexOutOfBoundsException e ){
				System.out.println( "It is not there... " + k );
			}
		}

		// Deleting positions between 0 and 1000 
		// Handling the exception for checking index existence 		
		for( int i=0; i<1000; i++ ){
			try{
				System.out.println( i + "->" + vector.del(i) );
			}catch( ArrayIndexOutOfBoundsException e ){
				System.out.println( "It is not there... " + i );
			}
		}
		
		// Deleting some elements 
    }
}