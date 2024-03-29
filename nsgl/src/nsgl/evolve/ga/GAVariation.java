package nsgl.evolve.ga;
import nsgl.random.Shuffle;
import nsgl.search.selection.Selection;
import nsgl.search.variation.Variation;
import nsgl.search.variation.Variation_1_1;
import nsgl.search.variation.Variation_2_2;
import nsgl.type.array.Vector;
import nsgl.type.bit.random.RandBit;
import nsgl.type.object.Tagged;

/**
 * <p>Title: ClassicStrategy</p>
 *
 * <p>Description: The classic genetic algorithm offspring generation strategy</p>
 *
 * <p>Copyright: Copyright (c) 2010</p>
 *
 * @author Jonatan Gomez
 * @version 1.0
 */
public class GAVariation<T> implements Variation<T>{
	protected Selection<T> selection;
    protected Variation_1_1<T> mutation;
    protected Variation_2_2<T> xover;
    protected RandBit generator;

    public GAVariation( Selection<T> selection, Variation_1_1<T> mutation,
    		Variation_2_2<T> xover, double probability) {
    	this.selection = selection;
        this.xover = xover;
        this.mutation = mutation;
        generator = new RandBit( 1.0 - probability );
    }

    
	@SuppressWarnings("unchecked")
	@Override
	public Tagged<T>[] apply(Tagged<T>... pop) {
		Shuffle<Tagged<T>> shuffle = new Shuffle<Tagged<T>>();
		shuffle.apply(pop);
		pop = selection.pick(pop.length, pop);
        Vector<Tagged<T>> buffer = new Vector<Tagged<T>>();
        int n = xover.arity();
        int m = pop.length / n;
        int k = 0;
        Tagged<T>[] parents = (Tagged<T>[])new Tagged[n];
        for (int j = 0; j < m; j++) {
            for( int i=0; i<n; i++ ){
                parents[i] = pop[k];
                k++;
            }
            Tagged<T>[] offspring;
            if (generator.next()) {
            	offspring = mutation.apply(xover.apply(parents));
            } else {
            	offspring = (Tagged<T>[])(new Tagged[n]);
            	for (int i = 0; i < n; i++) 
                    offspring[i] = parents[i];
            }
            for( int i=0; i<offspring.length; i++){
                buffer.add(offspring[i]);
            }
        }
        Object[] obj = buffer.toArray();
        Tagged<T>[] b = (Tagged<T>[])new Tagged[obj.length];
        for( int i=0; i<b.length; i++) b[i] = (Tagged<T>)obj[i];
        return b;
	}
}