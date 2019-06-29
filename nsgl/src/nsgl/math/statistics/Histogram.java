package nsgl.math.statistics;

import java.util.Iterator;

import nsgl.service.sort.Order;
import nsgl.type.array.SortedVector;
import nsgl.type.object.Pair;
import nsgl.type.object.PairAOrder;

public class Histogram<K> {
	protected SortedVector<Pair<K, Integer>> vector;
		
	public Histogram( Order order ){
		vector = new SortedVector<Pair<K, Integer>>(new PairAOrder<K,Integer>(order));
	}
	
	public void add( K key, int amount ){
		Pair<K, Integer> pair = new Pair<K, Integer>(key, amount);
		try{
			Integer index = vector.find(pair);
			pair = vector.get(index);
			pair.setB( pair.b()+amount );
		}catch(Exception e){ vector.add(pair); }
	}
	
	public void inc( K key ){ add( key, 1 ); }
	
	public K mode(){
		try{
			int m = 0;
			for( int i=1; i<vector.size(); i++ )
				if( vector.get(i).b() > vector.get(m).b() ) m=i;
			return vector.get(m).a();
		}catch(Exception e){ return null; }
	}
	
	public String toString(){
		return vector.toString();
	}
	
	public void clear(){ vector.clear(); }
	
	public Iterator<K> keys(){ return new HistogramKeys<K>(this); }	
}