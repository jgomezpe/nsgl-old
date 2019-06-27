/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nsgl.type.real.variation;

import nsgl.type.integer.random.UniformInt;

/**
 *
 * @author jgomez
 */
public class PickOne implements PickComponents{
    protected UniformInt g;
    protected int d=1;
    
    public PickOne(){
        g = new UniformInt(d);
    }
    
    @Override
    public int[] get(int DIMENSION) {
        if(d != DIMENSION){
            d = DIMENSION;
            g = new UniformInt(d);
        }
        return new int[]{g.next()};
    }    
}