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
package nsgl.type.collection;

import java.util.NoSuchElementException;

import nsgl.type.object.Pair;

/**
 * <p>Title: Indexed</p>
 *
 * <p>Description: A collection that is indexed by some criterium</p>
 *
 */
public interface Indexed<L,T> extends Collection<T>, Checkable<T> {
	/**
	 * Determines if there is an object in the collection at the given location
	 * @param loc Location of the object
	 * @return <i>true</i>If there is an object at the given location in the collection, <i>false</i> otherwise
	 */
	default boolean valid( L loc ){ 
		try{
			obtain(loc);
			return true;
		}catch(NoSuchElementException e){ return false; }
	}
	
	/**
	 * Obtains the object that is at the given location
	 * @param loc Location of the object
	 * @return Object at the given location 
	 * @throws NoSuchElementException If there is not an object at such location
	 */
	T obtain( L loc ) throws NoSuchElementException;
	
	/**
	 * Gets the object that is at a given location or <i>null</i> if there is not object at such location
	 * @param loc Location of the object
	 * @return the object that is at the given location or <i>null</i> if there is not object in such location
	 */
	default T get( L loc ){ try{ return obtain(loc); }catch(NoSuchElementException e){ return null; } }
	
	/**
	 * Sets an element with the given location into the Indexed Structure
	 * @param loc Location for setting the object 
	 * @param value The element to be set
	 * @return <i>true</i> if the element was set, <i>false</i> otherwise
	 */
	boolean set( L loc, T value );

	/**
	 * Adds an element with the given location into the Indexed Structure
	 * @param loc Location for setting the object 
	 * @param value The element to be set
	 * @return <i>true</i> if the element was set, <i>false</i> otherwise
	 */
	boolean add( L loc, T value );

	/**
	 * Sets an element into the KeyMap using the given key
	 * @param pair Location,Value pair for the setting process
	 * @return <i>true</i> if the element was set using the given key, <i>false</i> otherwise
	 */
	default boolean set( Pair<L,T> pair ){ return set(pair.a(), pair.b()); }
	
	/**
	 * Removes the next element returned by the iterator
	 * @param loc Location of the object to be deleted in the structure
	 * @return <i>true</i> if the next element returned by the iterator could be removed, <i>false</i> otherwise
	 */
	boolean remove( L loc );		
}