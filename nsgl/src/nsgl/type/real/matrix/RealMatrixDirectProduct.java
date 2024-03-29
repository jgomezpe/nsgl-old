package nsgl.type.real.matrix;
import nsgl.type.object.Cloneable;
import nsgl.type.real.matrix.RealMatrix;
import nsgl.math.algebra.*;
/**
 * <p>Title: MatrixDirectProduct</p>
 * <p>Description: Multiplies or divides two matrices,
 * this process is component by component.</p>
 * <p>Copyright:    Copyright (c) 2006</p>
 * <p>Company: Universidad Nacional de Colombia</p>
 * @author Jonatan Gomez Reviewed by (Aurelio Benitez, Giovanni Cantor, Nestor Bohorquez)
 * @version 1.0
 *
 */
public class RealMatrixDirectProduct implements Group<double[][]> {
    public double[][] identity( double[][] x ){
        int n = RealMatrix.rows(x);
        int m = RealMatrix.columns(x);
        double[][] y = new double[n][m];
        for( int i=0; i<n; i++ ){
            for( int j=0; j<n; j++ ){
                y[i][j] = 1.0;
            }
        }
        return y;
    }

    public double[][] fastInverse( double[][] x ){
        int n = RealMatrix.rows(x);
        for( int i=0; i<n; i++ ){
            for( int j=0; j<n; j++ ){
                x[i][j] = 1.0/x[i][j];
            }
        }
        return x;
    }

    public double[][] inverse( double[][] x ){
        int n = RealMatrix.rows(x);
        int m = RealMatrix.columns(x);
        double[][] y = new double[n][m];
        for( int i=0; i<n; i++ ){
            for( int j=0; j<n; j++ ){
                y[i][j] = 1.0/x[i][j];
            }
        }
        return y;
    }

    /**
     * Multiplies two matrices, this process is component by component.
     * The result of the operation is stored in the first matrix.
     * @param x The first matrix
     * @param y The second matrix
     */
    public double[][] fastPlus(double[][] x, double[][] y) {
        int n = RealMatrix.rows(y);
        int m = RealMatrix.columns(y);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                x[i][j] *= y[i][j];
            }
        }
        return x;
    }

    /**
     * Divides two matrices, this process is component by component.
     * The result of the operation is stored in the first matrix.
     * @param x The first matrix
     * @param y The second matrix
     */
    public double[][] fastMinus(double[][] x, double[][] y) {
        int n = RealMatrix.rows(y);
        int m = RealMatrix.columns(y);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                x[i][j] /= y[i][j];
            }
        }
        return x;
    }
    
    protected double[][] create(double[][] x){ return (double[][])Cloneable.cast(x).clone(); }
    
    @Override
    public double[][] minus(double[][] one, double[][] two) {
        return fastMinus(create(one), two);
    }

    @Override
    public double[][] plus(double[][] one, double[][] two) {
        return fastPlus(create(one), two);
    }   
}