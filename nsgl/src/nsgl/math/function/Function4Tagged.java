package nsgl.math.function;

import java.lang.reflect.ParameterizedType;
import java.util.Iterator;

import nsgl.collection.Collection;
import nsgl.collection.Mutable;
import nsgl.math.Function;
import nsgl.object.Tagged;

public class Function4Tagged<S,T> extends Function<S, T>{
	protected Function<S, T> f;
	
	public Function4Tagged( Function<S, T> f ){ this.f = f; }
	
	/**
	 * Computes the function for a given collection of objects
	 * @param Objects to be computed
	 * @return Values of the function, associated to the given collection of objects
	 */	
	public Collection<T> set_apply( Collection<?> set ){
		ParameterizedType parameterizedType = (ParameterizedType)set.getClass().getGenericSuperclass();
    	Class<?> cl = (Class<?>)parameterizedType.getActualTypeArguments()[0];	
    	if( Tagged.class.isAssignableFrom(cl) ){
    		return new Collection<T>() {
				@Override
				public Iterator<T> iterator() {
					@SuppressWarnings("unchecked")
					Iterator<Tagged<S>> iter = (Iterator<Tagged<S>>)set.iterator(); 
					return new Iterator<T>(){
						@Override
						public boolean hasNext(){ return iter.hasNext(); }

						@Override
						public T next() { return apply(iter.next()); }
					};
				}

				@Override
				public boolean isEmpty(){ return set.isEmpty(); }
			};
    	}else return super.set_apply(set);
	}

	/**
	 * Gets the function values for a given collection of objects
	 * @param Objects to be evaluated
	 * @return Function values associated to the given collection of objects
	 */
	@SuppressWarnings("unchecked")
	public void set_apply( Collection<?> set, Mutable<T> target ){
		ParameterizedType parameterizedType = (ParameterizedType)set.getClass().getGenericSuperclass();
    	Class<?> cl = (Class<?>)parameterizedType.getActualTypeArguments()[0];	
    	if( Tagged.class.isAssignableFrom(cl) )	for( Object x : set ) target.add(apply((Tagged<S>)x)); 
    	else super.set_apply(set, target);;
	}

	// Tagged methods
	
	/**
	 * Computes the function
	 * @param x Parameter of the function
	 * @return Computed value of the function
	 */
	@SuppressWarnings("unchecked")
	public T apply(Tagged<S> x){
		S xo = x.unwrap();
		if( deterministic() ){
			T y = (T)x.getTag(this);
			if( y!=null ) return y;
			y = apply(xo);
			x.setTag(this, y);
			return y;
		}else return apply(xo);
	}

	public T[] set_apply( Tagged<S>[] x ){
		@SuppressWarnings("unchecked")
		T[] r = (T[])new Object[x.length];
		for( int i=0; i<x.length; i++) r[i] = apply(x[i]);
		return r;
	}

	@Override
	public T apply(S x) { return f.apply(x); }	
}
