package nsgl.search.variation;

import nsgl.type.object.Tagged;

public class RefinedVariation<T> implements Variation<T> {
	protected Variation_1_1<T> refining;
	protected Variation<T> refined;
	
	public RefinedVariation( Variation<T> refined, Variation_1_1<T> refining ) {
		this.refined = refined;
		this.refining = refining;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public T[] apply(T... pop) {
		return refining.apply(refined.apply(pop));
	}

	@SuppressWarnings("unchecked")
	@Override
	public Tagged<T>[] apply(Tagged<T>... pop) {
		return refining.apply(refined.apply(pop));
	}

	public int arity(){ return refined.arity(); }		
}