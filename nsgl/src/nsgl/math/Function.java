package nsgl.math;

import java.util.Iterator;

import nsgl.cast.Cast;
import nsgl.type.collection.Collection;
import nsgl.type.collection.Mutable;
import nsgl.type.object.Tagged;
import nsgl.type.object.Traceable;

/**
 * <p>Abstract definition of a function</p>
 *
 * <p>Copyright: Copyright (c) 2010</p>
 *
 * @author Jonatan Gomez Perdomo
 * @version 1.0
 * @param <S> Codomain of the function
 * @param <T> Domain of the function
 */
public abstract class Function<S, T> implements Runnable{
	
	/**
	 * Determines if the fitness function is deterministic or not, i.e.,
	 * if the value of the function for a given value can change in time (non-stationary) 
	 * or not (stationary)
	 * @return <i>true</i> if the fitness function does not changes with time, <i>false</i> otherwise
	 */
	public boolean deterministic(){ return true; }

	/**
	 * Computes the function
	 * @param x Parameter of the function
	 * @return Computed value of the function
	 */
	public abstract T apply(S x);	    
 
	public T[] set_apply( S[] x ){
		@SuppressWarnings("unchecked")
		T[] r = (T[])new Object[x.length];
		for( int i=0; i<x.length; i++) r[i] = apply(x[i]);
		return r;
	}
	
	/**
	 * Computes the function for a given collection of objects
	 * @param Objects to be computed
	 * @return Values of the function, associated to the given collection of objects
	 */	
	public Collection<T> set_apply( Collection<?> set ){
		return new Collection<T>() {
			@Override
			public Iterator<T> iterator() {
				@SuppressWarnings("unchecked")
				Iterator<S> iter = (Iterator<S>)set.iterator(); 
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
	}

	/**
	 * Gets the function values for a given collection of objects
	 * @param Objects to be evaluated
	 * @return Function values associated to the given collection of objects
	 */
	@SuppressWarnings("unchecked")
	public void set_apply( Collection<?> set, Mutable<T> target ){ for( Object x : set ) target.add(apply((S)x)); }

	// Tagged objects
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

	
	// As a thread
	
	protected S input;
	protected T output;
	/**
	 * Executes the algorithm on the given input
	 */
	public void run(){
		start();
		setOutput(this.apply(input()));
	}
	
    /**
     * Flag used for determining if the function was stopped or not
     */
    protected boolean continueFlag = true;

    /**
     * Stars the possibility of computing the function
     */
    public void start(){ continueFlag = true; };

    
    /**
     * Stops the function computation
     */
    public void stop(){ continueFlag = false; }

    /**
     * Determines if the function was stopped or not
     * @return true if the function was stopped, false otherwise
     */
    public boolean running(){ return continueFlag; }

	public void setInput(S in) { input = in; }

	public void setOutput(T out) { output = out; }

	public S input() { return input; }

	public T output() { return output; }    
	
	/**
     * Adds the output of the algorithm to the tracers
     */
    public void addToTrace(){ 
    	Traceable t = (Traceable)Cast.apply(this, Traceable.class);
    	t.trace(output());
    }
}