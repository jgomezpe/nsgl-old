package nsgl.math.function;

import java.util.Iterator;

import nsgl.math.Function;
import nsgl.type.collection.Collection;

public class ApplyToCollection<S,T> implements Collection<T>{
	protected Function<S,T> function;
	protected Collection<S> collection=null;
    
	protected class ApplyFunctionCollectionIterator implements Iterator<T>{
		protected Iterator<S> iterator;
		public ApplyFunctionCollectionIterator(){ this.iterator = collection.iterator(); }
		
		@Override
		public boolean hasNext(){ return iterator.hasNext(); }
		
		@Override
		public T next(){ return function.apply(iterator.next()); }
	}
    
	public ApplyToCollection(Function<S,T> function, Collection<S> collection) {
		this.function = function;
		this.collection = collection;
	} 
    
	@Override
	public Iterator<T> iterator(){ return new ApplyFunctionCollectionIterator(); }

	@Override
	public boolean isEmpty(){ return collection.isEmpty(); }
}