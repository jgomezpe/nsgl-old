/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nsgl.type.real.array;

/**
 *
 * @author jgomez
 */
public class Instance extends Uniform01 {
    protected RealVectorLinealScale scale;
    public Instance(  double[] min, double[] max  ){
        super();
        scale = new RealVectorLinealScale(min, max);
    }
    
    @Override
    public double[] create(int n){ return scale.inverse(super.create(n)); }
}