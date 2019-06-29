package nsgl.search.solution;

import nsgl.search.Goal;
import nsgl.service.description.Descriptor;
import nsgl.type.object.Tagged;

public class SolutionDescriptor<T> implements Descriptor{
	protected Goal<T,Double> goal;

	public SolutionDescriptor( Goal<T,Double> goal ) { this.goal = goal; }

	public double[] descriptors( Tagged<T> sol ){ return new double[]{goal.apply(sol)}; }

	@SuppressWarnings("unchecked")
	@Override
	public double[] features(Object sol ){ return descriptors((Tagged<T>)sol); }
	
	@Override
	public String toString(){ return "SolutionDescriptor"; }	
}