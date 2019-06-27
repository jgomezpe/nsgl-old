package nsgl.type.real.sparse.metric;


import nsgl.type.real.sparse.RealSparse;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jgomez
 */
public class CosineSimilarity extends NormalizedCosineSimilarity{
    @Override
    public double apply( RealSparse x , RealSparse y){
        return super.apply(x, y)/(prod.norm(x)*prod.norm(y));
    }
}
