package nsgl.type.array;

import java.util.Iterator;

import nsgl.math.algebra.ScalarProduct;
import nsgl.type.object.Instancer;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2007</p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class ArrayScalarProduct<T> implements ScalarProduct<Array<T>> {
    protected ScalarProduct<T> productT = null;

    public ArrayScalarProduct( ScalarProduct<T> productT ){
        this.productT = productT;
    }

    public Array<T> multiply( Array<T> a, double x ){
        @SuppressWarnings("unchecked")
		Array<T> v = (Array<T>)Instancer.cast(a).create();
        Iterator<T> iter = a.iterator();
        while( iter.hasNext() ) v.add(productT.multiply(iter.next(), x));
        return v;
    }
    
    public Array<T> fastMultiply( Array<T> a, double x ){
    	for( int i=0; i<a.size(); i++ ) a.set(i, productT.fastMultiply(a.get(i), x));
    	return a;
    }

    
    public Array<T> divide( Array<T> a, double x ){
        @SuppressWarnings("unchecked")
		Array<T> v = (Array<T>)Instancer.cast(a).create();
        Iterator<T> iter = a.iterator();
        while( iter.hasNext() ) v.add(productT.divide(iter.next(), x));
        return v;
    }
    
    public Array<T> fastDivide( Array<T> a, double x ){
    	for( int i=0; i<a.size(); i++ ) a.set(i, productT.fastDivide(a.get(i), x));
    	return a;
    }        
}