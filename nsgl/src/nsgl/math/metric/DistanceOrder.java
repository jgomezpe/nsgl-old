package nsgl.math.metric;

import nsgl.service.Order;
import nsgl.type.real.RealOrder;

public class DistanceOrder<T> implements Order{
	protected Distance<T> distance;
	protected T point;
	protected RealOrder order;
	
	public DistanceOrder( Distance<T> distance, T point ){
		this.distance = distance;
		this.point = point;
		this.order = new RealOrder();
	}

	@SuppressWarnings("unchecked")
	@Override
	public int compare(Object x, Object y){ return ((Double)distance.apply((T)x,point)).compareTo(distance.apply((T)y,point)); }
}