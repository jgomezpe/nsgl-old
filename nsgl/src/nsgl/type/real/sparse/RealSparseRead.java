package nsgl.type.real.sparse;

import java.io.IOException;

import nsgl.service.Read;
import nsgl.type.collection.iterator.Backable;

public interface RealSparseRead extends Read{
	public RealSparse read(Backable<Integer> reader) throws IOException;

	@Override
	default Object read(Object obj, Backable<Integer> reader) throws IOException{ return read(reader); }
}