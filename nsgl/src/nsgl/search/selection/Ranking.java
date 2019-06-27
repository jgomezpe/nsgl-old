package nsgl.search.selection;

import nsgl.search.Goal;
import nsgl.service.Order;
import nsgl.service.ReversedOrder;
import nsgl.type.array.SortedVector;
import nsgl.type.integer.random.RouletteInt;
import nsgl.type.object.Pair;
import nsgl.type.object.PairBOrder;

public class Ranking<T,R> extends GoalBasedSelection<T,R> {
	public Ranking( Goal<T,R> goal ){ super(goal); }
	
	@Override
	public int[] apply(int n, R[] x) {
		Order order = goal().order();
		int s = x.length;
		SortedVector<Pair<Integer,R>> indexq = new SortedVector<Pair<Integer,R>>( 
				new ReversedOrder( new PairBOrder<Integer,R>(order) ) );
		for( int i=0; i<s; i++ ) indexq.add(new Pair<Integer,R>(i, x[i] ) );
		RouletteInt roulette = new RouletteInt(n);
		int[] sel = roulette.generate(n);
		try{ for( int i=0; i<sel.length; i++ ) sel[i] = indexq.get(i).a(); }catch(Exception e){}
		return sel;
	}

	@Override
	public int choose_one(R[] x) {
		return apply(1,x)[0];
	}
}