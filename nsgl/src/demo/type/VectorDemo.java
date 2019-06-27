/**
 * <p>Copyright: Copyright (c) 2019</p>
 *
 * <h3>License</h3>
 *
 * Copyright (c) 2019 by Jonatan Gomez-Perdomo. <br>
 * All rights reserved. <br>
 *
 * <p>Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * <ul>
 * <li> Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * <li> Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 * <li> Neither the name of the copyright owners, their employers, nor the
 * names of its contributors may be used to endorse or promote products
 * derived from this software without specific prior written permission.
 * </ul>
 * <p>THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 *
 *
 * @author <A HREF="http://disi.unal.edu.co/profesores/jgomezpe"> Jonatan Gomez-Perdomo </A>
 * (E-mail: <A HREF="mailto:jgomezpe@unal.edu.co">jgomezpe@unal.edu.co</A> )
 * @version 1.0
 */
package demo.type;
import java.util.NoSuchElementException;

import nsgl.type.array.Vector;
import nsgl.type.object.Cloneable;
/**
 * <p>Title: VectorDemo</p>
 *
 * <p>Description: An example of using the Vector class.</p>
 *
 */
public class VectorDemo {

	public static void traversing( Vector<Integer> v ){
	      System.out.println("**************TRAVERSING A VECTOR****************");
	      System.out.println("**************Using a regular for loop****************");
	      for(int i=0; i<v.size(); i++ ){
	          System.out.print( " " + v.get(i) );
	      }	
	      System.out.println();
	      System.out.println("**************Using for each****************");
	      for( Integer k:v){
	    	  System.out.print( " " + k );  
	      }
	      System.out.println();
	}
	
	public static void print( Vector<Integer> v ){
	      for( Integer k:v) System.out.print( " " + k );
	      System.out.println();
	}
	
	public static void removing( Vector<Integer> v ){
	      System.out.println("**************REMOVING FROM A VECTOR****************");
	      print(v);
	      System.out.println("**************Using the position of the element****************");
	      for( int i=0; i<5; i++ ){
	    	  int k = (int)(Math.random() * v.size());
	    	  System.out.print("Removing element at position:" + k);
	          boolean removed = v.remove(k);
	          System.out.println( ":" + removed);
	      }
	      print(v);
	      System.out.println("**************Searching for the first apparition of an element and removing it****************");
	      for( int i=0; i<5; i++ ){
	    	  int k = (int)(Math.random() * 10);
	    	  System.out.print("Removing element:" + k);
	          boolean removed = v.del(k);
	          System.out.println( ":" + removed);
	      }
	      print(v);      
	}
	
	public static void adding( Vector<Integer> v ){
	      System.out.println("**************ADDING TO A VECTOR****************");
	      print(v);
	      System.out.println("**************Adding to the end of the vector****************");
	      for( int i=0; i<5; i++ ){
	    	  int k = (int)(Math.random() * 10);
	    	  System.out.print("Adding element:" + k);
	          boolean added = v.add(k);
	          System.out.println( ":" + added);
	      }
	      print(v);      
	      System.out.println("**************Adding to an specific position****************");
	      for( int i=0; i<5; i++ ){
	    	  int k = (int)(Math.random() * (v.size()+1));
	    	  int d = (int)(Math.random() * 10);
	    	  System.out.print("Adding element " + d + " at position:" + k);
	          boolean added = v.add(k, d);
	          System.out.println( ":" + added);
	      }
	      print(v);
	}
	
	public static void consulting( Vector<Integer> v ){
	      System.out.println("**************CONSULTING TO A VECTOR****************");
	      print(v);
	      System.out.println("**************Obtaining the position of the first apparition of an element in the vector****************");	      
	      for( int i=0; i<5; i++ ){
	    	  int d = (int)(Math.random() * 15);
	    	  System.out.print("Element " + d + " at position:");
	          Integer k = v.find(d);
	          System.out.println( ":" + k);
	      }
	      System.out.println("**************Getting the element at a given position****************");	      
	      for( int i=0; i<5; i++ ){
	    	  int k = (int)(Math.random() * (v.size()+5));
	    	  System.out.print("Element at position " + k);
	          Integer d = v.get( k );
	          System.out.println( ":" + d);
	      }
	      System.out.println("**************obtaining the element at a given position****************");	      
	      for( int i=0; i<5; i++ ){
	    	  int k = (int)(Math.random() * (v.size()+5));
	    	  System.out.print("Element at position " + k);
	    	  try{
	    		  Integer d = v.obtain( k );
		          System.out.println( ":" + d);
	    	  }catch( NoSuchElementException e ){ System.err.println(e.getMessage()); }  
	      }
	}
	
	public static Vector<Integer> creating( int mode ){
		System.out.println("**************CREATING TO A VECTOR****************");
		switch( mode ){
			case 0: 
				System.out.println("**************Default constructor****************");
				return new Vector<Integer>();
			case 1:	
				System.out.println("**************Using an Vector****************");
				Integer[] x = new Integer[10];
				for( int i=0; i<x.length; i++) x[i] = (int)(Math.random()*10);
				return new Vector<Integer>(x);
			case 2:	
				System.out.println("**************Using an Vector with a given size****************");
				x = new Integer[10];
				int n = (int)(Math.random()*10 + 1);
				System.out.println( "Size of the Vector:" + n);
				for( int i=0; i<n; i++) x[i] = (int)(Math.random()*10);
				return new Vector<Integer>(x,n);
		}
		return new Vector<Integer>();
	}
	
	@SuppressWarnings("unchecked")
	public static Vector<Integer> cloning(Vector<Integer> v){
		System.out.println("**************CLONING A VECTOR****************");
		Cloneable c = Cloneable.cast(v);
		Vector<Integer> clone = (Vector<Integer>)c.clone();
		print(clone);
		return clone;
	} 
	
	public static void main( String[] args ){
		Vector<Integer> v = creating( 1 );
		traversing(v);
		Vector<Integer> c = cloning(v);
		adding(v);
		removing(v);
		consulting(v);
		System.out.println("**************CLONE OF THE ORIGINAL VECTOR****************");
		print(c);
		System.out.println("**************FINAL VECTOR****************");
		print(v);
    }  


//	        System.out.println( 5 >> 2 );
//	        System.out.println( (5 & 4) == 4 );
//	        System.out.println( 10 << 2 );
        
//	      VectorList<Integer> v = new VectorList<>();
/*      long m, p, n;

	      n  = System.nanoTime();
	      for( int i=0; i<100000; i++ ){
	          v.add( i );
	      }
	      p = System.nanoTime();
	      m = p - n;
	      System.out.print( m + " " );
	      
	      n = System.nanoTime();
	      for(int i=0; i<100000; i++ ){
	          v.add(i,-i);
	      }
	      p = System.nanoTime();
	      m = p - n;
	      System.out.print( m  + " " );

	      n = System.nanoTime();
	      for(int i=0; i<100000; i++ ){
	          v.remove(i);
//	          v.remove(i);
	      }
	      p = System.nanoTime();
	      m = p - n;
	      System.out.print( m );

	      n = System.nanoTime();
	      for(int i=0; i<99000; i++ ){
	          v.remove(0);
//	          v.remove(i);
	      }
	      p = System.nanoTime();
	      m = p - n;
	      System.out.println( m );

	      for(int i=0; i<1000; i++ ){
	          System.out.println( v.get(i));
	      }
	*/      
    
}