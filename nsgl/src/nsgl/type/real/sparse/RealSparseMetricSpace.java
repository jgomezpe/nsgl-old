/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nsgl.type.real.sparse;

import nsgl.math.metric.QuasiMetric;

/**
 *
 * @author jgomez
 */
public class RealSparseMetricSpace  extends RealSparceLinearSpace 
        implements nsgl.math.metric.MetricLinearSpace<RealSparse> {
    
    protected QuasiMetric<RealSparse> metric;
    
    public RealSparseMetricSpace( QuasiMetric<RealSparse> metric ){
        this.metric = metric;
    }
    
    @Override
    public double apply(RealSparse x, RealSparse y) {
        return metric.apply(x, y);
    } 
    
}
