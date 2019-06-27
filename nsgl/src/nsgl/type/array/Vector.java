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

import nsgl.exception.NoSuchElement;
import nsgl.type.object.Cloneable;
/**
 * <p>Title: Vector</p>
 *
 * <p>Description: A dynamic Vector of objects of the same Type (parameterized type)</p>
 *
 */
public class Vector<T> extends Fibo implements Array<T>, Cloneable{
	protected T[] buffer;
	
	/**
	 * Creates a Vector with the given Vector of values
	 * @param buffer Vector of values used for initializing the Vector 
	 */
	public Vector( T[] buffer ){ this(buffer, buffer.length); }

	/**
	 * Creates a Vector using the first <i>s</i> values of Vector <i>buffer</i>
	 * @param buffer Vector with the initial elements of the Vector
	 * @param s Number of elements taken from the given Vector (the first <i>s</i> elements are taken)
	 */
	public Vector( T[] buffer, int s ){
		this.buffer = buffer;
		size = s;
		find_fib(buffer.length);
	}

	@SuppressWarnings("unchecked")
	public Vector(){ this( (T[])new Object[DEFAULT_C], 0 ); }

	/**
	 * Inserts a data element at the end of the Vector
	 * <pre>
	 *	{@code
	 * // Suppose that v is a Vector<Integer> and v = [5, 1, 2, 8, 9 ]  
	 * boolean added = v.add(0); // added=true and v = [5, 1, 2, 8, 9, 0] 
	 * System.out.println( ":" + added); // Prints true
	 * added = v.add(9); // added=true and v = [5, 1, 2, 8, 9, 0, 9] 
	 * System.out.println( ":" + added); // Prints true
	 * added = v.add(5); // added=true and v = [5, 1, 2, 8, 9, 0, 9, 5] 
	 * System.out.println( ":" + added); // Prints true
	 * }
	 * </pre>
	 * @param data Data element to be inserted
	 * @return <i>true</i> if the element could be added, <i>false</i> otherwise
	 */
	@Override
	public boolean add(T data){
		if( buffer.length == size ) grow();
		buffer[size]=data;
		size++;
		return true;
	}

	/**
	 * Adds an element at the given position. Elements at positions <i>index+1...size()-1</i> are moved one position ahead. 
	 * <pre>
	 *	{@code
	 * // Suppose that v is a Vector<Integer> and v = [5, 1, 2, 8, 9, 0, 9, 5]  
	 * boolean added = v.add(2, 8); // added=true and v = [5, 1, 8, 2, 8, 9, 0, 9, 5] 
	 * System.out.println( ":" + added); // Prints true
	 * added = v.add(0, 4); // added=true and v = [4, 5, 1, 8, 2, 8, 9, 0, 9, 5] 
	 * System.out.println( ":" + added); // Prints true
	 * added = v.add(10, 7); // added=true and v = [4, 5, 1, 8, 2, 8, 9, 0, 9, 5, 7] 
	 * System.out.println( ":" + added); // Prints true
	 * added = v.add(-1, 6); // added=false and v = [4, 5, 1, 8, 2, 8, 9, 0, 9, 5, 7] 
	 * System.out.println( ":" + added); // Prints true
	 * added = v.add(15, 6); // added=false and v = [4, 5, 1, 8, 2, 8, 9, 0, 9, 5, 7, null, null, null, null, 6] 
	 * System.out.println( ":" + added); // Prints true
	 * }
	 * </pre>
	 * @param index Position to be occupied by the new element
	 * @param data Element that will be added into the Vector
	 * @return <i>true</i> If the element could be added at the given position, <i>false</i> otherwise
	 */
	public boolean add( Integer index, T data ){
		if( index < 0 ) return false;
		rightShift(index);
		buffer[index]=data;
		return true;
	}

	/**
	 * Sets an element to a given position. The position must be a non negative and less than the Vector's size. 
	 * <pre>
	 *	{@code
	 * // Suppose that v is a Vector<Integer> and v = [5, 1, 2, 8, 9, 0, 9, 5]  
	 * boolean set = v.set(2, 8); // set=true and v = [5, 1, 8, 8, 9, 0, 9, 5] 
	 * System.out.println( ":" + set); // Prints true
	 * set = v.set(6, 7); // set=true and v = [5, 1, 8, 8, 9, 0, 7, 5]
	 * System.out.println( ":" + set); // Prints true
	 * set = v.set(-1, 3); // set=false and v = [5, 1, 8, 8, 9, 0, 7, 5]
	 * System.out.println( ":" + set); // Prints false
	 * set = v.set(8, 4); // set=false and v = [5, 1, 8, 8, 9, 0, 7, 5]
	 * System.out.println( ":" + set); // Prints false
	 * }
	 * </pre>
	 * @param index Position where the element will be located
	 * @param data Element to set in the Vector
	 * @return <i>true</i> if the element could be set at the given position, <i>false</i> otherwise
	 */
	@Override
	public boolean set( Integer index, T data ){
		try{
			buffer[index]=data;
			return index<size;
		}catch(ArrayIndexOutOfBoundsException e){}
		return false;
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
	@Override
	public boolean del(T data) {
		try{
			Integer k = find( data );
			if( k!=null ){
				leftShift( k );
				return true;
			}
		}catch( Exception e ){}
		return false; 
	}	

	/**
	 * Removes the element at the given position
	 * <pre>
	 *	{@code
	 * // Suppose that v is a Vector<Integer> and v = [5, 1, 2, 8, 9, 0, 9, 5]  
	 * boolean removed = v.remove(3); // removed=true and v = [5, 1, 2, 9, 0, 9, 5] 
	 * System.out.println( ":" + removed); // Prints true
	 * removed = v.remove(0); // removed=true and v = [1, 2, 9, 0, 9, 5]
	 * System.out.println( ":" + removed);  // Prints true
	 * removed = v.del(8); // removed=false and v = [1, 2, 9, 0, 9, 5]
	 * System.out.println( ":" + removed);  // Prints false
	 * }
	 * </pre>
	 * @param index The position of the object to be deleted
	 * @return <i>true</i> if the element could be removed, <i>false</i> otherwise
	 */
	public boolean remove( Integer index ){
		if(0<=index && index<size()){
			leftShift( index );
			return true;
		}	
		return false;
	}

	/**
	 * Gets the element that is located at the given position.
	 * <pre>
	 *	{@code
	 * // Suppose that v is a Vector<Integer> and v = [5, 1, 2, 8, 9, 0, 9, 5]  
	 * Integer k = v.obtain(3);  
	 * System.out.println( ":" + k); // Prints 8
	 * k = v.obtain(0);
	 * System.out.println( ":" + k);  // Prints 5
	 * k = v.obtain(-1); // Throws a NooSuchElementException 
	 * k = v.obtain(8); // Throws a NooSuchElementException 
	 * }
	 * </pre>
	 * @param index Position of the element to obtain
	 * @return The element that is located at the given position
	 * @throws NoSuchElementException If the index is a non valid position
	 */
	@Override
	public T get(Integer index){ return buffer[index]; }

	/**
	 * Obtains the element that is located at the given position.
	 * <pre>
	 *	{@code
	 * // Suppose that v is a Vector<Integer> and v = [5, 1, 2, 8, 9, 0, 9, 5]  
	 * Integer k = v.obtain(3);  
	 * System.out.println( ":" + k); // Prints 8
	 * k = v.obtain(0);
	 * System.out.println( ":" + k);  // Prints 5
	 * k = v.obtain(-1); // Throws a NooSuchElementException 
	 * k = v.obtain(8); // Throws a NooSuchElementException 
	 * }
	 * </pre>
	 * @param index Position of the element to obtain
	 * @return The element that is located at the given position
	 * @throws NoSuchElementException If the index is a non valid position
	 */
	@Override
	public T obtain(Integer index) throws NoSuchElementException{
		try{ return buffer[index]; }
		catch(IndexOutOfBoundsException e ){ throw NoSuchElement.exception(NoSuchElement.INDEXOUTBOUNDS, index);	} 
	}

	/**
	 * Creates an iterator for the Vector. The Vector can be traversed using a for each approach.
	 * <pre>
	 *	{@code
	 * // Suppose that v is a Vector<Integer> 
	 * // The next for each loop will print every element in v  
	 * for( Integer k : v )   
	 *   System.out.print( " " + k);
	 * }
	 * </pre>
	 * @return An iterator for the Vector.
	 */
	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return new Iterator<T>() {
			protected int pos=0;
			@Override
			public boolean hasNext(){ return pos<size; }

			@Override
			public T next(){ return buffer[pos++]; }
		};
	}

	/**
	 * Determines if the Vector is empty or not.
	 * @return <i>true</i> if the Vector is empty <i>false</i> otherwise.
	 */
	@Override
	public boolean isEmpty(){ return size==0; }
	
	/**
	 * Creates a shallow object Vector version of the Vector (elements in the Vector are references to objects in the Vector).
	 * @return A shallow object Vector version of the Vector (elements in the Vector are references to objects in the Vector).
	 */
	public Object[] toVector() {
		@SuppressWarnings({ "unchecked" })
		T[] new_buffer = (T[])new Object[size];
		System.arraycopy(buffer, 0, new_buffer, 0, size);
		return new_buffer;		
	}

	public T[] buffer(){ return buffer; }

	/**
	 * Creates a clone of the Vector by cloning every object in it
	 * @return A clone of the Vector
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Object clone(){
		Cloneable c = Cloneable.cast(buffer);
		return new Vector<T>((T[])c.clone(), size());
	}

	/**
	 * Resizes the Vector according to the Fibonacci numbers
	 */
	public void resize(){
		@SuppressWarnings({ "unchecked" })
		T[] new_buffer = (T[])new Object[c];
		System.arraycopy(buffer, 0, new_buffer, 0, Math.min(size,c));
		buffer = new_buffer;		
	}  

	protected void leftShift( int index ) throws IndexOutOfBoundsException{
		size--;
		if( index < size ) System.arraycopy(buffer, index+1, buffer, index, size-index);
		if( size < a ) shrink();
	}

	protected void rightShift( int index ) throws IndexOutOfBoundsException{
		if( buffer.length == size ) grow();
		System.arraycopy(buffer, index, buffer, index+1, buffer.length-index-1);
		size++;
	}
}