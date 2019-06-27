/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nsgl.evolve.es;

import nsgl.search.selection.Selection;
import nsgl.type.object.Tagged;

/**
 *
 * @author jgomez
 */
public class CommaReplacement<T> extends ESReplacement<T> {
    public CommaReplacement( int mu ) {
        super( mu );
    }
    
    public CommaReplacement( int mu, Selection<T> sel ) {
        super(mu, sel);
    }
    
    @Override
    public Tagged<T>[] pool(Tagged<T>[] current, Tagged<T>[] next){
        return next;
    }
}    