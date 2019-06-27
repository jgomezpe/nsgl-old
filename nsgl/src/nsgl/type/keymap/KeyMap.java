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
package nsgl.type.keymap;

import java.util.Iterator;

import nsgl.type.collection.Collection;
import nsgl.type.collection.Indexed;
import nsgl.type.collection.Searchable;
import nsgl.type.object.Cleanable;
import nsgl.type.object.Comparable;
import nsgl.type.object.Pair;
/**
 * <p>Title: Mutable</p>
 *
 * <p>Description: An abstract keymap collection</p>
 *
 * <p>Copyright: Copyright (c) 2019</p>
 *
 */
public interface KeyMap<K,V> extends Indexed<K,V>, Searchable<K,V>, Cleanable{
	// Search collection methods 
	/**
	 * Locates the given object in the structure
	 * @param value Data object to be located
	 * @return A location of the given object. If the element is not in the data structure the get method will return <i>null</i>
	 */
	@Override
	default K find( V value ){
		Comparable comp = Comparable.cast(value);
		Collection<Pair<K, V>> pairs = this.pairs();
		for( Pair<K,V> p:pairs ) if( comp.eq( p.b() ) ) return p.a();
		return null;
	}
	
	// KeyMap own methods
	Collection<K> keys();

	default Collection<Pair<K, V>> pairs(){
		return new Collection<Pair<K, V>>() {
			protected Collection<K> keys=keys();
			
			@Override
			public Iterator<Pair<K, V>> iterator() {
				return new Iterator<Pair<K,V>>() {
					protected Iterator<K> inner = keys.iterator();
					@Override
					public boolean hasNext(){ return inner.hasNext(); }

					@Override
					public Pair<K, V> next() {
						K key = inner.next();
						return new Pair<K,V>(key, get(key)); 	
					}
				};
			}

			@Override
			public boolean isEmpty(){ return keys.isEmpty(); }			
		};	
	}
	
	/**
	 * Adds an element with the given location into the Indexed Structure
	 * @param key Key associated to the object 
	 * @param value The element to be add
	 * @return <i>true</i> if the element was added, <i>false</i> otherwise
	 */
	default boolean add( K key, V value ){ return set(key,value); }
}