/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nsgl.type.real.sparse;

import java.util.Iterator;

import nsgl.type.object.Pair;

/**
 *
 * @author jgomez
 */
public class RealSparseDotProduct {
    protected Pair<Integer,Double> element( Iterator<Pair<Integer,Double>> iter ){
        if( iter.hasNext() ){
            return iter.next();
        }
        return null;
    }

    public double apply(RealSparse x, RealSparse y){
        double prod = 0.0;
        Iterator<Pair<Integer,Double>> iter_x = x.pairs().iterator();
        Iterator<Pair<Integer,Double>> iter_y = y.pairs().iterator();
        Pair<Integer,Double> elem_x = element(iter_x);
        Pair<Integer,Double> elem_y = element(iter_y);
        while( elem_x != null && elem_y != null ){
            if(elem_x.a() < elem_y.a() ){
                elem_x = element(iter_x);                
            }else{
                if(elem_x.a() > elem_y.a() ){
                    elem_y = element(iter_y);
                }else{
                    prod += elem_y.b() * elem_x.b();
                    elem_y = element(iter_y);
                    elem_x = element(iter_x);
                }
            }
        }
        return prod;
    }
    
    public double sqr_norm(RealSparse x){
        double prod = 0.0;
        for( Double y:x) prod += y*y;
        return prod;
    }
    
    public double norm(RealSparse x){
        return Math.sqrt(sqr_norm(x));
    }
}
