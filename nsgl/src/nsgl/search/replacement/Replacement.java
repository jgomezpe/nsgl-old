/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nsgl.search.replacement;

import nsgl.type.object.Tagged;

/**
 *
 * @author jgomez
 */
public interface Replacement<T> {
    public Tagged<T> apply( Tagged<T> current, Tagged<T> next );
    public default void init(){};
}
