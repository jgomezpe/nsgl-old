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
package nsgl.cast;
import nsgl.keymap.HashMap;
/**
 * <p>Title: Cast</p>
 *
 * <p>Description: A casting operation from some object to a a given class (parameterized type)</p>
 *
 */
public abstract class Cast<T>{
	/**
	 * Collection of casting operations  
	 */
	@SuppressWarnings("rawtypes")
	protected static HashMap<Class<?>, HashMap<Object,Cast>> pool = new HashMap<Class<?>, HashMap<Object,Cast>>(); 
	
	/**
	 * Gets the casting operation from class <i>src</i> to class <i>target</i>  
	 * @param src Source class of the casting process
	 * @param target target class of the casting process
	 * @return The casting operation from class <i>src</i> to class <i>target</i>, <i>null</i> if there is not casting operation
	 */
	@SuppressWarnings("rawtypes") 
	protected static Cast service( Class<?> src, HashMap<Object, Cast> target ){
		Cast cast = target.get(src);
		if( cast != null ) return cast;
		cast = service( src.getSuperclass(), target);
		if( cast != null ) return cast;
		Class<?>[] superTypes = src.getInterfaces();
		for( Class<?> c:superTypes ){
			cast = service(c, target );
			if( cast != null ) return cast;
		}
		return null;
	}
	
	/**
	 * Gets the casting operation from object <i>obj</i> to class <i>target</i>  
	 * @param obj Object to be casted to an object of class <i>target</i>
	 * @param target  Class to which the object will be casted
	 * @return The casting operation for object <i>obj</i> to class <i>target</i>, <i>null</i> if there is not casting operation
	 */
	@SuppressWarnings("rawtypes")
	public static Cast service( Object obj, Class<?> target ){
		HashMap<Object, Cast> set = pool.get(target);
		if( set != null ){
			Cast cast = set.get(obj); 
			if( cast != null ) return cast; 
			else return service( obj.getClass(), set );
		}
		return null;
	}

	/**
	 * Casts the object to the given class
	 * @param obj Object to be casted
	 * @param target target class of the casting process
	 * @return An object of class <i>target</i> that is the result of casting object <i>obj</i>. <i>null</i> if the casting process could not be carried on
	 */
	public static Object apply( Object obj, Class<?> target ){
		if( target.isInstance(obj) ) return obj;
		@SuppressWarnings("rawtypes")
		Cast cast = service(obj,target);
		if( cast != null ) return cast.apply(obj); 
		try{ return target.cast(obj); }catch(ClassCastException e){ return null; }
	}
	
	/**
	 * Associates the casting process when trying to cast the given object to the given class 
	 * @param caller Object that will use the casting process (may be a Class)
	 * @param target Target class of the casting process
	 * @param cast Casting process
	 */
	@SuppressWarnings("rawtypes")
	public static void set( Object caller, Class<?> target, Cast cast ){
		HashMap<Object, Cast> map = pool.get(target);
		if( map == null ){
			map = new HashMap<Object, Cast>();
			pool.set(target, map);
		}
		map.set(caller, cast);
	}
	
	/**
	 * Casts the object to the class of this object (Parameterized type)
	 * @param obj  Object to be casted to the class of this object (Parameterized type <i>T</i>)
	 * @return An instance of class <i>T</i> (parameterized type), that represents the casted version of object <i>obj</i> to class <i>target</i>
	 */
	public abstract T apply( Object obj );
	
	static{
		Cast.set(Object.class, String.class, new toString());
	}
}