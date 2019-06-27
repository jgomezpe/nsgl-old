/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nsgl.type.real.sparse;

import java.util.Iterator;

import nsgl.type.array.SortedVector;
import nsgl.type.array.Vector;
import nsgl.type.integer.IntOrder;
import nsgl.type.object.Cloneable;
import nsgl.type.object.Pair;
import nsgl.type.object.PairAOrder;

/**
 *
 * @author jgomez
 */
public class RealSparceLinearSpace implements nsgl.math.algebra.LinearSpace<RealSparse> {
    @Override
    public RealSparse identity( RealSparse x ){
        return new RealSparse(x.size());
    }

    @Override
    public RealSparse fastInverse( RealSparse x ){
        Iterator<Pair<Integer,Double>> iter = x.pairs().iterator();
        while( iter.hasNext() ){
            Pair<Integer,Double> el = iter.next();
            el.setB(-el.b());
        }
        return x;
    }
    
    protected Pair<Integer,Double> element( Iterator<Pair<Integer,Double>> iter ){
        if( iter.hasNext() ) return iter.next();
        return null;
    }

    protected int indexCounter(RealSparse x, RealSparse y) {
        int counter = 0;
        Iterator<Pair<Integer,Double>> iter_x = x.pairs().iterator();
        Iterator<Pair<Integer,Double>> iter_y = y.pairs().iterator();
        Pair<Integer,Double> elem_x = element(iter_x);
        Pair<Integer,Double> elem_y = element(iter_y);
        while( elem_x != null && elem_y != null ){
            if(elem_x.a() < elem_y.a() ){
                counter++;
                elem_x = element(iter_x);                
            }else{
                if(elem_x.a() > elem_y.a() ){
                    counter++;
                    elem_y = element(iter_y);
                }else{
                    counter++;
                    elem_y = element(iter_y);
                    elem_x = element(iter_x);
                }
            }
        }
        while( elem_x != null ){
            counter++;
            elem_x = element(iter_x);
        }
        while( elem_y != null ){
            counter++;
            elem_y = element(iter_y);
        }
        return counter;
    }
       
    /**
     * Adds the second vector to the first vector.
     * The addition process is component by component.
     * The result of the operation is stored in the first vector.
     * @param x The first vector
     * @param y The second vector
     */
    @SuppressWarnings("unchecked")
	@Override
    public RealSparse fastPlus(RealSparse x, RealSparse y) {
    	int n = Math.max(x.size(), y.size());
        Vector<Pair<Integer,Double>> v = new Vector<Pair<Integer,Double>>();
        Iterator<Pair<Integer,Double>> iter_x = x.pairs().iterator();
        Iterator<Pair<Integer,Double>> iter_y = y.pairs().iterator();
        Pair<Integer,Double> elem_x = element(iter_x);
        Pair<Integer,Double> elem_y = element(iter_y);
        while( elem_x != null && elem_y != null ){
            if(elem_x.a() < elem_y.a() ){
                v.add(elem_x);
                elem_x = element(iter_x);
            }else{
                if(elem_x.a() > elem_y.a() ){
                    v.add((Pair<Integer,Double>)Cloneable.cast(elem_y).clone());
                    elem_y = element(iter_y);
                }else{
                    double d = elem_x.b()+elem_y.b();
                    if( d != 0.0 ){
                        elem_x.setB(d);
                        v.add(elem_x);
                    }
                    elem_y = element(iter_y);
                    elem_x = element(iter_x);
                }
            }
        }
        while( elem_x != null ){
            v.add(elem_x);
            elem_x = element(iter_x);
        }
        while( elem_y != null ){
            v.add((Pair<Integer,Double>)Cloneable.cast(elem_y).clone());
            elem_y = element(iter_y);
        }
        SortedVector<Pair<Integer,Double>> zv = new SortedVector<Pair<Integer,Double>>(v.buffer(), v.size(), new PairAOrder<Integer,Double>( new IntOrder()) );
        x.update(zv,n);
        return x;
    }
    

    /**
     * Subtracts the second vector from the first vector.
     * The subtraction process is component by component.
     * The result of the operation is stored in the first vector.
     * @param x The first vector
     * @param y The second vector
     */
    @SuppressWarnings("unchecked")
	@Override
    public RealSparse fastMinus(RealSparse x, RealSparse y) {
        int n = Math.max(x.size(),y.size()); //  indexCounter(x, y);
        Vector<Pair<Integer,Double>> v = new Vector<Pair<Integer,Double>>();
        Iterator<Pair<Integer,Double>> iter_x = x.pairs().iterator();
        Iterator<Pair<Integer,Double>> iter_y = y.pairs().iterator();
        Pair<Integer,Double> elem_x = element(iter_x);
        Pair<Integer,Double> elem_y = element(iter_y);
        while( elem_x != null && elem_y != null ){
            if(elem_x.a() < elem_y.a() ){
                v.add(elem_x);
                elem_x = element(iter_x);
            }else{
                if(elem_x.a() > elem_y.a() ){
                    elem_y = (Pair<Integer,Double>)Cloneable.cast(elem_y).clone();
                    elem_y.setB(-elem_y.b());
                    v.add(elem_y);                    
                    elem_y = element(iter_y);
                }else{
                    double d = elem_x.b()-elem_y.b();
                    if( d != 0.0 ){
                        elem_x.setB(d);
                        v.add(elem_x);
                    }
                    elem_y = element(iter_y);
                    elem_x = element(iter_x);
                }
            }
        }
        while( elem_x != null ){
            v.add(elem_x);
            elem_x = element(iter_x);
        }
        while( elem_y != null ){
            elem_y = (Pair<Integer,Double>)Cloneable.cast(elem_y).clone();
            elem_y.setB(-elem_y.b());
            v.add(elem_y);
            elem_y = element(iter_y);
        }
        SortedVector<Pair<Integer,Double>> zv = new SortedVector<Pair<Integer,Double>>(v.buffer(), v.size(), new PairAOrder<Integer,Double>( new IntOrder()) );
        x.update(zv,n);
        return x;
    }
    
	/**
	 * Multiplies a vector by an scalar.
	 * @param y The vector
	 * @param x The scalar
	 */
	@Override
	public RealSparse fastMultiply(RealSparse y, double x) {
		if( x==0.0 ) y.clear();
		else{  
			Iterator<Pair<Integer,Double>> iter = y.pairs().iterator();
			Pair<Integer,Double> elem;
			while( iter.hasNext() ){
				elem = iter.next();
				elem.setB(x * elem.b());
			}
		}            
		return y;
	}
	
	/**
	 * Divides a vector by an scalar.
	 * @param one The vector
	 * @param x The scalar
	 */
	@Override
	public RealSparse fastDivide(RealSparse y, double x){ return fastMultiply(y, 1.0/x); }
	
	protected RealSparse create(RealSparse x){ return (RealSparse)Cloneable.cast(x).clone(); }

    @Override
    public RealSparse inverse(RealSparse x) {
        return fastInverse(create(x));
    }

    @Override
    public RealSparse minus(RealSparse one, RealSparse two) {
        return fastMinus(create(one), two);
    }

    @Override
    public RealSparse plus(RealSparse one, RealSparse two) {
        return fastPlus(create(one), two);
    }

    @Override
    public RealSparse divide(RealSparse one, double x) {
        return fastDivide(create(one), x);
    }

    @Override
    public RealSparse multiply(RealSparse one, double x) {
        return fastMultiply(create(one), x);
    }
}