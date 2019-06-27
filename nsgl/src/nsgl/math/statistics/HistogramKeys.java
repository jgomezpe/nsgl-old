package nsgl.math.statistics;

import java.util.Iterator;

import nsgl.type.object.Pair;


public class HistogramKeys<K> implements Iterator<K>{
	protected Iterator<Pair<K,Integer>> iterator;

	public HistogramKeys( Histogram<K> histogram) {
		this.iterator = histogram.vector.iterator();
	}
	
	@Override
	public boolean hasNext() {
		return iterator.hasNext();
	}

	@Override
	public K next() {
		return iterator.next().a();
	}
}