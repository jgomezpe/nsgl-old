/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nsgl.math.algebra;

import java.util.Iterator;

import nsgl.type.array.Array;
import nsgl.type.array.Vector;
import nsgl.type.object.Cloneable;
/**
 *
 * @author jgomez
 */
public interface InvertableScale<T> extends Scale<T>{
    public T fastInverse( T x );
    
    @SuppressWarnings("unchecked")
	default T inverse( T x ){ return fastInverse((T)Cloneable.cast(x).clone()); }
    
    default Array<T> inverse( Array<T> a ){
        Vector<T> v = new Vector<T>();
        Iterator<T> iter = a.iterator();
        while( iter.hasNext() ) v.add(inverse(iter.next()));
        return v;
    } 
    
    default Array<T> fastInverse( Array<T> a ){
        try{ for( int i=0; i<a.size(); i++ ) a.set(i, fastInverse(a.get(i))); }catch(Exception e){}
        return a;
    }    
}