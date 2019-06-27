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
package nsgl.type.object;

import nsgl.cast.Cast;
import nsgl.cast.toSortable;
import nsgl.type.object.Comparable;

/**
 * <p>Title: Sortable</p>
 *
 * <p>Description: An object that can be sorted (less than, equal to, greater than, etc) according to some criteria</p>
 *
 */
public interface Sortable extends Comparable{
    /**
     * Determines if the object is less, equal or greater than other.
     * @param two Second object to be compared
     * @return A value less than 0 indicates that it is less than two, a value equal to 0 indicates
     * that it is equal to two and a value greater than 0 indicates that it is greater than two
     */
	int compare(Object two);

	/**
	 * Determines if the object is less than (in some order) object two
	 * @param two The second object to compare
	 * @return (this &lt; two)
	 */
	default boolean lt(Object two){ return (compare(two) < 0); }

	/**
	 * Determines if the object is greater than (in some order) object two
	 * @param two The second object to compare
	 * @return (this &gt; two)
	 */
	default boolean gt(Object two){ return (compare(two) > 0); }

	/**
	 * Determines if the object is equal to the object two
	 * @param two The second object to compare
	 * @return (this == two)
	 */
	default boolean eq(Object two){ return (compare(two) == 0); }

	/**
	 * Determines if the object one is less than or equal to (in some order) object two
	 * @param two The second object to compare
	 * @return (this  &lt;= two)
	 */
	default boolean le(Object two){ return (compare(two) <= 0); }

	/**
	 * Determines if the object one is greater than or equal to (in some order) object two
	 * @param two The second object to compare
	 * @return (this &lt;= two)
	 */
	default boolean ge(Object two){ return (compare(two) >= 0); } 
	
	/**
	 * Cast an object to Sortable, if possible
	 * @param obj Object to be casted to Sortable
	 * @return A sortable version of the given object, <i>null</i> otherwise
	 */
	static Sortable cast( Object obj ){
		if( obj instanceof Sortable ) return (Sortable)obj;
    	@SuppressWarnings("unchecked")
		Cast<Sortable> cast = (Cast<Sortable>)Cast.service(obj,Sortable.class);
		if( cast == null ){
			cast = new toSortable();
			Cast.set(Object.class, Sortable.class, cast);
		}
    	return cast.apply(obj); 
	}
}