package nsgl.search.population;

import nsgl.search.BasicGoalBased;
import nsgl.search.Goal;
import nsgl.service.description.Descriptor;
import nsgl.type.object.Tagged;
import nsgl.type.real.Real;

public class PopulationDescriptors<T> extends BasicGoalBased<T,Double> implements Descriptor{
	public double[] descriptors( T[] pop ) {
		Goal<T,Double> goal = goal();
		double[] quality = new double[pop.length];
		for(int i=0; i<quality.length; i++ ) quality[i] = goal.apply(pop[i]);
		return Real.statistics_with_median(quality).get();		
	}

	public double[] descriptors(Tagged<T>[] pop) {
		Goal<T,Double> goal = goal();
		double[] quality = new double[pop.length];
		for(int i=0; i<quality.length; i++ ) quality[i] = goal.apply(pop[i]);
		return Real.statistics_with_median(quality).get();		
	}

	@SuppressWarnings("unchecked")
	@Override
	public double[] features(Object obj) {
		Class<?> cl = obj.getClass().getComponentType();
		if( Tagged.class.isAssignableFrom(cl)) return descriptors((Tagged<T>[])obj); 
		return descriptors((T[])obj); 
	}
}