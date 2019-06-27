package nsgl.type.real.variation;

import nsgl.type.integer.random.UniformInt;
import nsgl.type.real.variation.RealArityTwo;

/**
 * <p>Title: SimpleXOver</p>
 * <p>Description:Exchanges the last components of the first individual with
 * the last components of the second individual</p>
 * <p>Copyright: Copyright (c) 2010</p>
 * @author Jonatan Gomez
 * @version 1.0
 */

public class SimpleXOver extends RealArityTwo{
    protected UniformInt g;
    protected int d = 2;
    /**
     * Default constructor
     */
    public SimpleXOver() {
        g = new UniformInt(d-1);
    }

    public int pos( int DIM1, int DIM2 ){
        int min = Math.min(DIM1, DIM2);
        if( d != min ){
            d = min;
            g = new UniformInt(d-1);
        }
        return g.next() + 1;
    }
    
    /**
     * Apply the 2-ary genetic operator over the individual genomes
     * @param c1 First Individuals genome to be modified by the genetic operator
     * @param c2 Second Individuals genome to be modified by the genetic operator
     * @return Extra information of the genetic operator
     */
    @Override
    public double[][] apply(double[] c1, double[] c2) {
        try {
          double[] x = c1.clone();
          double[] y = c2.clone();
          int pos = pos(x.length, y.length);
          double t;
          for (int i = 0; i < pos; i++) {
              t = x[i];
              x[i] = y[i];
              y[i] = t;
          }
          return new double[][]{x, y}; 
        } catch (Exception e) {}
        return null;
    }
}
