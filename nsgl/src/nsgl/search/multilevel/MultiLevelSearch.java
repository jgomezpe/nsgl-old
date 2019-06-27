package nsgl.search.multilevel;

import nsgl.search.Goal;
import nsgl.search.Search;
import nsgl.search.space.Space;
import nsgl.type.object.Tagged;
import nsgl.type.object.tagged.TaggedManager;

public class MultiLevelSearch<G,P,R> extends Search<P,R> {
	protected Goal<P,R> goal;
	@Override
	public Goal<P, R> goal() { return goal; }
	
	@Override
	public void setGoal(Goal<P, R> goal) { this.goal = goal; }
	
	protected Search<G,R> lowLevelSearch;
	protected CodeDecodeMap<G, P> map;
	protected TaggedManager<P> manager;
	
	public MultiLevelSearch( Search<G,R> lowLevelSearch, CodeDecodeMap<G, P> map ) {
		this.lowLevelSearch = lowLevelSearch;
		this.map = map;
		this.manager = new TaggedManager<P>() {};
	}
	
	@Override
	public Tagged<P> solve(Space<P> space) {
		MultiLevelGoal<G, P, R> lowLevelGoal = new MultiLevelGoal<G,P, R>(goal(), map);
		MultiLevelSpace<G, P> lowLevelSpace = new MultiLevelSpace<G, P>(space, map);
		lowLevelSearch.setGoal(lowLevelGoal);
		Tagged<G> sol = lowLevelSearch.solve(lowLevelSpace);
		Tagged<P> h_sol = manager.wrap(map.decode(sol.unwrap()));
		return h_sol;
	}
}