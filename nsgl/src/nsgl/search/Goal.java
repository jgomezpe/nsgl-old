/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nsgl.search;

import nsgl.math.Function;
import nsgl.service.Order;
import nsgl.type.object.Tagged;
import nsgl.type.object.Traceable;

/**
 *
 * @author jgomez
 */
public abstract class Goal<T, R> extends Function<T,R>{
	public final static String name ="goal"; 
	
	public abstract Order order();
    public abstract R compute( T x );

    public int compare(T x, T y){ return order().compare(apply(x),apply(y)); }
    public int compare(Tagged<T> x, Tagged<T> y){ return order().compare(apply(x),apply(y)); }
    
    @Override
    public R apply( T x ){
    	R y = compute(x);
        trace(x, y);
    	return y;
    }
}