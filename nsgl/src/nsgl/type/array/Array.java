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
package nsgl.type.array;

import java.util.Iterator;
import java.util.NoSuchElementException;

import nsgl.type.collection.Finite;
import nsgl.type.collection.Indexed;
import nsgl.type.collection.Mutable;
import nsgl.type.collection.Searchable;
import nsgl.type.collection.iterator.BT;
import nsgl.type.collection.iterator.IteratorPosition;
import nsgl.type.object.Comparable;

/**
 * <p>Title: Mutable</p>
 *
 * <p>Description: An abstract array of objects of the same Type (parameterized type)</p>
 *
 */
public interface Array<T> extends Finite<T>, Indexed<Integer,T>, Searchable<Integer,T>, Mutable<T>{	
	@Override
	default Iterator<T> iterator(){ return iterator(0); } 

	default Iterator<T> iterator(int index){ 
		return new BT<T>(){
			
			protected int pos=index;
			
			@Override
			public boolean hasNext(){ return pos+1<size(); }

			@Override
			public T next() throws NoSuchElementException{
				try{
					pos++;
					return get(pos);
				}catch( Exception e ){ throw new NoSuchElementException( e.getMessage() ); }
			}

			@Override
			public IteratorPosition pos() { return new IteratorPosition(0,pos); }

			@Override
			public int maxBack() { return pos+1; }

			@Override
			public boolean back(int k) {
				if(0<k && k<=maxBack()){ pos -= k; }
				return false;
			}
		};
	}
	
	default Object[] toArray() {
		Object[] array = new Object[size()];
		try{ for( int i=0; i<array.length; i++ ) array[i] = get(i); }catch(Exception e){}
		return array;
	}
	
	@Override
	default boolean valid(Integer key){ return key!=null && 0<=key && key<size(); }	/**
	 * Sets the size of the array
	 * @param n The new size of the array
	 */
	void resize( int n );
	
	/**
	 * Obtains the first position where the element is located in the Vector
	 * <pre>
	 *	{@code
	 * // Suppose that v is a Vector<Integer> and v = [5, 1, 2, 8, 9, 0, 9, 5]  
	 * Integer pos = v.find(0); // pos=5 
	 * System.out.println( ":" + pos); // Prints 5
	 * pos = v.find(9); // pos=4 
	 * System.out.println( ":" + pos); // Prints 5
	 * pos = v.find(3); // pos=null 
	 * System.out.println( ":" + pos); // Prints null
	 * }
	 * </pre>
	 * @param value Element to be located
	 * @return The first position of the element in the Vector, <i>null</i> If the element is not in the Vector.
	 */
	@Override
	default Integer find( T value ){
		Comparable comp = Comparable.cast(value);
		int size = this.size();
		for( int i=0; i<size; i++ ) if( comp.eq( get(i) ) ) return i;
		return null;
	}
	
	/**
	 * Removes the first apparition in the Vector of the given element.
	 * <pre>
	 *	{@code
	 * // Suppose that v is a Vector<Integer> and v = [5, 1, 2, 8, 9, 0, 9, 5]  
	 * boolean removed = v.del(8); // removed=true and v = [5, 1, 2, 9, 0, 9, 5] 
	 * System.out.println( ":" + removed); // Prints true
	 * removed = v.del(9); // removed=true and v = [5, 1, 2, 0, 9, 5]
	 * System.out.println( ":" + removed);  // Prints true
	 * removed = v.del(8); // removed=false and v = [5, 1, 2, 0, 9, 5]
	 * System.out.println( ":" + removed);  // Prints false
	 * }
	 * </pre>
	 * @param data Data element to be deleted
	 * @return <i>true</i> if the element could be deleted, <i>false</i> otherwise
	 */
	default boolean del(T data) {
		try{
			Integer k = find( data );
			if( k!=null ) return remove(k);
		}catch( Exception e ){}
		return false; 
	}	
}