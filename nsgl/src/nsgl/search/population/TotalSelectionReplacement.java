package nsgl.search.population;

import nsgl.search.BasicGoalBased;
import nsgl.search.selection.Elitism;
import nsgl.search.selection.Selection;
import nsgl.type.object.Tagged;

public class TotalSelectionReplacement<T> extends BasicGoalBased<T,Double> implements PopulationReplacement<T> {
	protected Selection<T> selection=null;
	
	public TotalSelectionReplacement(){}
	
	public TotalSelectionReplacement( Selection<T> selection ){
		this.selection = selection;
	}
	
	@Override
	public Tagged<T>[] apply(Tagged<T>[] current,
			Tagged<T>[] next) {
		if( selection == null )	selection = new Elitism<T,Double>(goal(), 1.0, 0.0);

		int n = current.length;
		int m = next.length;
		@SuppressWarnings("unchecked")
		Tagged<T>[] parent = (Tagged<T>[])new Tagged[n+m];
		System.arraycopy(current, 0, parent, 0, n);
		System.arraycopy(next, 0, parent, n, m);
		return selection.pick(n, parent);
	}	
}