package nsgl.evolve.haea;

import nsgl.service.description.Descriptor;
import nsgl.type.object.Describable;

public class HaeaStepDescriptor<T> implements Descriptor {
	public double[] features( HaeaStep<T> step ){ return (double[])Describable.cast(step.operators()).features(); }

	@SuppressWarnings("unchecked")
	@Override
	public double[] features(Object obj) { return features((HaeaStep<T>)obj); }
}