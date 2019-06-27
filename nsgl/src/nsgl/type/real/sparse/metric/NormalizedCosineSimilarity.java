package nsgl.type.real.sparse.metric;

import nsgl.math.metric.Simmilarity;
import nsgl.type.real.sparse.RealSparse;
import nsgl.type.real.sparse.RealSparseDotProduct;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jgomez
 */
public class NormalizedCosineSimilarity implements Simmilarity<RealSparse> {
    protected RealSparseDotProduct prod = new RealSparseDotProduct();
    @Override
    public double apply(RealSparse x, RealSparse y) {
        return prod.apply(x, y);
    }
    
    public double max(RealSparse x){
        return 1.0;
    }
}
