package nsgl.search;

import nsgl.math.metric.Distance;
import nsgl.math.metric.DistanceOrder;
import nsgl.service.sort.Order;

public abstract class DistanceToGoal<T,R> extends Goal<T,R>{
    protected Order order;
    
    public DistanceToGoal( R goal_value, Distance<R> distance ){
    	order = new DistanceOrder<R>(distance, goal_value);
    }   
    
    @Override
    public Order order(){ return order; }    
}
