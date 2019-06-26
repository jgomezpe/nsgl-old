package nsgl.bplus.memory;

import nsgl.bplus.InnerNode;
import nsgl.bplus.LeafNode;
import nsgl.bplus.BPlus;
import nsgl.service.Order;

public class MemoryBPlus<T> extends BPlus<T> {

	public MemoryBPlus(Order order) {
		super(order);
	}

	public MemoryBPlus(int BRANCHING, Order order) {
		super(BRANCHING, order);
	}

	@Override
	public InnerNode<T> innerNode() {
		return new MemoryInnerNode<T>(BRANCHING);
	}

	@Override
	public LeafNode<T> leafNode() {
		return new MemoryLeafNode<T>(BRANCHING);
	}
}
