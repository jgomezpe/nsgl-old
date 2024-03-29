package nsgl.evolve;

import nsgl.evolve.es.ESReplacement;
import nsgl.evolve.es.ESStep;
import nsgl.math.function.iterative.ForLoopCondition;
import nsgl.math.logic.Predicate;
import nsgl.search.population.IterativePopulationSearch;
import nsgl.search.population.PopulationSearch;
import nsgl.search.space.Space;
import nsgl.search.variation.Variation_1_1;
import nsgl.search.variation.Variation_n_1;
import nsgl.type.object.Tagged;

public class ESFactory<T,P> {
	//Evolutionary Strategy factory
	public PopulationSearch<T,Double> evolutionarystrategy( 
			ESStep<T,P> step, Predicate<Tagged<T>[]> tC ){
		return new IterativePopulationSearch<T,Double>( step, tC );
	}

	public PopulationSearch<T,Double> evolutionarystrategy( 
			ESStep<T,P> step, int MAXITERS ){
		return evolutionarystrategy( step, new ForLoopCondition<>(MAXITERS) );
	}
	
	public PopulationSearch<T,Double> evolutionarystrategy(
			int mu, int lambda, int ro, 
			Variation_n_1<T> y_recombination, Variation_1_1<T> mutation, 
			Variation_n_1<P> s_recombination, Variation_1_1<P> s_mutation, Space<P> s_space,
			ESReplacement<T> replacement, int MAXITERS ){
		return evolutionarystrategy(new ESStep<T,P>(mu, lambda, ro, y_recombination, mutation, s_recombination, s_mutation, s_space, replacement), MAXITERS);
	}
	
	public PopulationSearch<T,Double> evolutionarystrategy( 
			int mu, int lambda, int ro, 
			Variation_n_1<T> y_recombination, Variation_1_1<T> mutation, 
			Variation_n_1<P> s_recombination, Variation_1_1<P> s_mutation, Space<P> s_space,
			boolean plus_replacement, int MAXITERS ){
		return evolutionarystrategy(new ESStep<T,P>(mu, lambda, ro, y_recombination, mutation, s_recombination, s_mutation, s_space, plus_replacement), MAXITERS);
	}	
}