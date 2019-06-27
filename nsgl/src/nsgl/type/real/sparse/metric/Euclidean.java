/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nsgl.type.real.sparse.metric;

import nsgl.type.real.sparse.RealSparse;

/**
 *
 * @author jgomez
 */
public class Euclidean  extends SqrEuclidean {
  /**
   * Calculates the Minkowski distance from one real vector to another.
   * This object does not calculate the p-root
   * @param x The first real vector
   * @param y The second real vector
   * @return Minkowski distance (without calculating the p-root from object x to object y
   */
    @Override
  public double apply(RealSparse x, RealSparse y) {
      return Math.sqrt(super.apply(x, y));
  }
}