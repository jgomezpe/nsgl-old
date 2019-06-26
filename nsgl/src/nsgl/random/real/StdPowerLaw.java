/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nsgl.random.real;

import nsgl.random.InverseGenerator;

//
//Unified Random generation Pack 1.0 by Jonatan Gómez-Perdomo
//https://github.com/jgomezpe/unalcol/tree/master/random/
//
/**
 *
 * @author jgomez
 */
public class StdPowerLaw  extends InverseGenerator<Double> implements RandReal{
    double coarse_alpha = -1.0;
    
    public StdPowerLaw(){ super(); }
    
    public StdPowerLaw( double alpha ){ coarse_alpha = 1.0/(1.0-alpha); }
        
    @Override
    public Double next(double x){
        return Math.pow(1.0-x, coarse_alpha);
    }    
}
