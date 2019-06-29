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
package nsgl.type.object.cast;

import java.lang.reflect.Array;

import nsgl.type.object.Describable;
import nsgl.type.object.cast.Cast;

/**
 * <p>Title: toComparable</p>
 *
 * <p>Description: Default operator for casting an object to Describable</p>
 *
 */
public class toDescribable extends Cast<Describable>{
	@Override
	public Describable apply(Object obj) {
		return new Describable() {
    		/**
    		 * Creates a clone (non shallow copy) of a Java primitive types array 
    		 * @param obj Array of a primitive type values to be cloned 
    		 * @return A clone of the array (non shallow copy)
    		 */
    		protected double[] array( double[] obj ){ return obj.clone(); }
    		
    		/**
    		 * Creates a clone (non shallow copy) of a Java primitive types array 
    		 * @param obj Array of a primitive type values to be cloned 
    		 * @return A clone of the array (non shallow copy)
    		 */
    		protected double[] array( int[] obj ){
    			double[] x = new double[obj.length];
    			for( int i=0; i<obj.length; i++ ) x[i] = obj[i];
    			return x;
    		}
    		
    		/**
    		 * Creates a clone (non shallow copy) of a Java primitive types array 
    		 * @param obj Array of a primitive type values to be cloned 
    		 * @return A clone of the array (non shallow copy)
    		 */
    		protected double[] array( long[] obj ){
    			double[] x = new double[obj.length];
    			for( int i=0; i<obj.length; i++ ) x[i] = obj[i];
    			return x;
    		}
    		
    		/**
    		 * Creates a clone (non shallow copy) of a Java primitive types array 
    		 * @param obj Array of a primitive type values to be cloned 
    		 * @return A clone of the array (non shallow copy)
    		 */
    		protected double[] array( byte[] obj ){
    			double[] x = new double[obj.length];
    			for( int i=0; i<obj.length; i++ ) x[i] = obj[i];
    			return x;
    		}
    		
    		/**
    		 * Creates a clone (non shallow copy) of a Java primitive types array 
    		 * @param obj Array of a primitive type values to be cloned 
    		 * @return A clone of the array (non shallow copy)
    		 */
    		protected double[] array( short[] obj ){
    			double[] x = new double[obj.length];
    			for( int i=0; i<obj.length; i++ ) x[i] = obj[i];
    			return x;
    		}
    		
    		/**
    		 * Creates a clone (non shallow copy) of a Java primitive types array 
    		 * @param obj Array of a primitive type values to be cloned 
    		 * @return A clone of the array (non shallow copy)
    		 */
    		protected double[] array( char[] obj ){
    			double[] x = new double[obj.length];
    			for( int i=0; i<obj.length; i++ ) x[i] = obj[i];
    			return x;
    		}
    		
    		/**
    		 * Creates a clone (non shallow copy) of a Java primitive types array 
    		 * @param obj Array of a primitive type values to be cloned 
    		 * @return A clone of the array (non shallow copy)
    		 */
    		protected double[] array( boolean[] obj ){
    			double[] x = new double[obj.length];
    			for( int i=0; i<obj.length; i++ ) x[i] = obj[i]?1:0;
    			return x;
    		}
    		
    		/**
    		 * Creates a clone (non shallow copy) of a Java primitive types array 
    		 * @param obj Array of a primitive type values to be cloned 
    		 * @return A clone of the array (non shallow copy)
    		 */
    		protected double[] array( float[] obj ){
    			double[] x = new double[obj.length];
    			for( int i=0; i<obj.length; i++ ) x[i] = obj[i];
    			return x;
    		}
    		
    		
    		/**
    		 * Creates a clone (non shallow copy) of a Java primitive types array 
    		 * @param obj Array of a primitive type values to be cloned 
    		 * @return A clone of the array (non shallow copy)
    		 */
    		protected double[] primitiveArray( Object obj ){
    			if( obj instanceof int[] ) return array((int[])obj);
    			if( obj instanceof double[] ) return array((double[])obj);
    			if( obj instanceof char[] ) return array((char[])obj);
    			if( obj instanceof byte[] ) return array((byte[])obj); 
    			if( obj instanceof long[] ) return array((long[])obj);
    			if( obj instanceof short[] ) return array((short[])obj);
    			if( obj instanceof float[] ) return array((float[])obj); 
    			return array((boolean[])obj);
    		}

    		/**
    		 * Creates a clone of an array. 
    		 * It uses the clone service defined for each object in the array.
    		 * @param obj Array to be cloned 
    		 * @return A clone of the array
    		 */
			protected double[] describeArray(Object obj){
    			Class<?> cl = obj.getClass().getComponentType();
    			if( cl.isPrimitive() ) return primitiveArray(obj);
    			int n = Array.getLength(obj);
    			double[][] m = new double[n][];
    			int k = 0;
    			for( int i=0; i<n; i++ ){
    				m[i] = new double[0];
    				Object the_obj = Array.get(obj, i); 
    				if(the_obj!=null){
						Describable d = Describable.cast(the_obj); 
    					if( d != null ) m[i] = d.features(); 
    				}
    				k += m[i].length;
    			}
    			double[] d = new double[k];
    			k=0;
    			for( int i=0; i<m.length; i++ ){
    				System.arraycopy(m[i], 0, d, k, m[i].length);
    				k += m[i].length;
    			}
    			return d;	
    		}
    		
			@Override
			public double[] features() {
				if( obj instanceof Double || obj instanceof Integer ||  obj instanceof Character || obj instanceof Long ) return new double[]{(Double)obj};
				if( obj.getClass().isArray() ) return describeArray(obj);
				return null;
			}
		};
	}
}