/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nsgl.type.real.sparse.metric;

import nsgl.math.metric.Distance;
import nsgl.type.real.sparse.RealSparceLinearSpace;
import nsgl.type.real.sparse.RealSparse;
import nsgl.type.real.sparse.RealSparseDotProduct;

/**
 *
 * @author jgomez
 */
public class SqrEuclidean implements Distance<RealSparse> {
    protected RealSparseDotProduct prod = new RealSparseDotProduct();
    protected RealSparceLinearSpace add = new RealSparceLinearSpace();
  /**
   * Calculates the Minkowski distance from one real vector to another.
   * This object does not calculate the p-root
   * @param x The first real vector
   * @param y The second real vector
   * @return Minkowski distance (without calculating the p-root from object x to object y
   */
    @Override
  public double apply(RealSparse x, RealSparse y) {
      RealSparse z = add.minus(x, y);
      return prod.sqr_norm(z);
  }

}
