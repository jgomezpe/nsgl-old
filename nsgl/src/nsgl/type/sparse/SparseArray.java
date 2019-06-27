package nsgl.type.sparse;

import java.util.NoSuchElementException;

import nsgl.type.array.Array;
import nsgl.type.array.SortedVector;
import nsgl.type.collection.Collection;
import nsgl.type.integer.IntOrder;
import nsgl.type.object.Cloneable;
import nsgl.type.object.Pair;
import nsgl.type.object.PairAOrder;
import nsgl.exception.NoSuchElement;

public class SparseArray<T> implements Array<T>, Cloneable {
    public SparseArray(){ vector = new SortedVector<Pair<Integer,T>>(new PairAOrder<Integer,T>(new IntOrder())); }

    public SparseArray( SortedVector<Pair<Integer,T>> vector ){ this.vector = vector; }

	protected SortedVector<Pair<Integer,T>> vector;
    protected Pair<Integer,T> loc = new Pair<Integer,T>(0, null);

    protected Pair<Integer,T> pair( int index ){
    	try{ return vector.get(index); }catch( Exception e ){ return null; } 
    }
    
    protected int index( int index ){ return pair(index).a(); } 
    
	@Override
	public int size(){ return vector.size()>0?index(vector.size()-1)+1:0; }

	@Override
	public T get(Integer index) throws NoSuchElementException{
		loc.setA(index);
		Integer pos = vector.find(loc);
		if( pos==null ) return null;
		return vector.get(pos).b();
	}
	
	@Override
	public T obtain(Integer index) throws NoSuchElementException{
		T v = get(index);
		if( v==null ) throw NoSuchElement.exception(NoSuchElement.INDEXOUTBOUNDS, index);
		return v;
	}
	
	public int low(){ return size()>=0?index(0):0; }

	public Collection<Pair<Integer,T>> pairs(){ return vector; } 
	
	@Override
	public Object[] toArray() {
		return null;
	}
    
	@Override
	public boolean add(T data){ return false; }

	@Override
	public void clear() { vector.clear(); }

	@Override
	public boolean add(Integer index, T data){ return set(index,data); }

	@Override
	public boolean remove(Integer index){
		loc.setA(index);
		index = vector.find(loc);
		if( index != null ) return vector.remove(index);
		return false;
	}

	@Override
	public void resize(int size) {
		int i=vector.size()-1;
		while(i>=0 && index(i)>=size){
			vector.remove(i);
			i--;
		}
	}

	@Override
	public boolean set(Integer index, T data){
		loc.setA(index);
		try{
			Integer i = vector.find(loc);
			vector.get(i).setB(data);
			return true;
		}catch(Exception e){ return vector.add(new Pair<Integer,T>(index,data)); }
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Object clone(){ return new SparseArray<T>( (SortedVector<Pair<Integer,T>>)vector.clone() ); }
}