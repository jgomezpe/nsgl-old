/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nsgl.type.real.sparse;

import nsgl.math.algebra.Scale;

/**
 *
 * @author jgomez
 */
public class RealSparseSphereNormalization implements Scale<RealSparse>{
    protected RealSparseDotProduct dot = new RealSparseDotProduct();
    protected RealSparceLinearSpace scale = new RealSparceLinearSpace();
  /**
   * Applies the transformation on the data record
   * @param x Data record to be transformed
   */
  public RealSparse fastApply(RealSparse x) { return scale.fastDivide(x, dot.norm(x)); }
}