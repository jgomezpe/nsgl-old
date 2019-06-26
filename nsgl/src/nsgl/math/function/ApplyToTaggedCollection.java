package nsgl.math.function;

import java.util.Iterator;

import nsgl.collection.Collection;
import nsgl.math.Function;
import nsgl.object.Tagged;

public class ApplyToTaggedCollection<S,T>  implements Collection<T>{
	protected Function4Tagged<S,T> function;
	protected Collection<Tagged<S>> collection=null;
    
	protected class ApplyFunctionTaggedCollectionIterator implements Iterator<T>{
		protected Iterator<Tagged<S>> iterator;
		public ApplyFunctionTaggedCollectionIterator(){ this.iterator = collection.iterator(); }
		
		@Override
		public boolean hasNext(){ return iterator.hasNext(); }
		
		@Override
		public T next(){ return function.apply(iterator.next()); }
	}
    
	public ApplyToTaggedCollection(Function<S,T> function, Collection<Tagged<S>> collection) {
		if( function instanceof Function4Tagged) this.function = (Function4Tagged<S,T>)function;
		else this.function = new Function4Tagged<S,T>(function);
		this.collection = collection;
	} 
    
	@Override
	public Iterator<T> iterator(){ return new ApplyFunctionTaggedCollectionIterator(); }

	@Override
	public boolean isEmpty(){ return collection.isEmpty(); }
}