/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nsgl.search.population;

import nsgl.type.object.Tagged;

/**
 *
 * @author Jonatan
 */
public interface PopulationReplacement<T>{
    public Tagged<T>[] apply( Tagged<T>[] current, Tagged<T>[] next );
    public default void init(){};	
}