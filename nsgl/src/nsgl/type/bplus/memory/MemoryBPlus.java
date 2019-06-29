package nsgl.type.bplus.memory;

import nsgl.service.sort.Order;
import nsgl.type.bplus.BPlus;
import nsgl.type.bplus.InnerNode;
import nsgl.type.bplus.LeafNode;

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
