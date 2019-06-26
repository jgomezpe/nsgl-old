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
package nsgl.keymap;

import java.util.Iterator;
import java.util.NoSuchElementException;

import nsgl.cast.Cast;
import nsgl.collection.Collection;
import nsgl.collection.Finite;
import nsgl.exception.NoSuchElement;
import nsgl.object.Pair;
import nsgl.object.Cloneable;

/**
 * <p>Title: HashMap</p>
 *
 * <p>Description: A hashmap collection</p>
 *
 */
public class HashMap<K,V> implements KeyMap<K,V>, Finite<V>, Cloneable{
	protected java.util.HashMap<K, V> table = new java.util.HashMap<K,V>();

	// KeyMap methods
	/**
	 * Sets an element with the given map into the HashMap
	 * @param key Key used for setting the object 
	 * @param value The element to be set
	 * @return <i>true</i> if the element was set, <i>false</i> otherwise
	 */
	@Override
	public boolean set( K key, V value ){
		table.put(key, value);
		return true;
	}

	/**
	 * Removes the next element returned by the iterator
	 * @param loc Location of the object to be deleted in the structure
	 * @return <i>true</i> if the next element returned by the iterator could be removed, <i>false</i> otherwise
	 */
	@Override
	public boolean remove( K key ){ return (table.remove(key)!=null); }

	/**
	 * Gets the object that has a given key or <i>null</i> otherwise
	 * @param key Key of the object
	 * @return the object that has the given key, <i>null</i> otherwise
	 */
	@Override
	public V get( K key ){ return table.get(key); }	
	
	/**
	 * Obtains the object that has the given key
	 * @param key Key of the object
	 * @return Object with the given key 
	 * @throws NoSuchElementException If there is not an object in the keymap with such key
	 */
	@Override
	public V obtain(K key) throws NoSuchElementException{
		if(valid(key)) return table.get(key);
		throw NoSuchElement.exception(NoSuchElement.INVALIDLOCATION, (String)Cast.apply(key, String.class));
	}
	
	public void merge(KeyMap<K, V> newKeyValues ){
		if(newKeyValues!=null) for( K key:newKeyValues.keys() ) table.put(key, newKeyValues.get(key));
	}

	@Override
	public Collection<K> keys(){ 
		return new Collection<K>(){
			@Override
			public Iterator<K> iterator(){ return table.keySet().iterator(); }

			@Override
			public boolean isEmpty(){ return table.isEmpty(); }
		}; 
	}
	
	// Collection methods
	@Override
	public boolean isEmpty(){ return table.isEmpty(); }

	@Override 
	public Iterator<V> iterator(){ return table.values().iterator(); }

	//Mutable collection
	@Override
	public void clear(){ table.clear(); }

	@Override
	public boolean valid(K key){ return table.containsKey(key); }

	@Override
	public int size() { return table.size(); }
	
	@SuppressWarnings("unchecked")
	@Override 
	public Object clone(){
		HashMap<K, V> c = new HashMap<K,V>();
		for( Pair<K,V> p : this.pairs() ) c.set((Pair<K,V>)p.clone());
		return c;
	}
}