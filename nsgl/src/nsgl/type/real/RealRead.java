package nsgl.type.real;

import java.io.IOException;

import nsgl.service.Read;
import nsgl.type.collection.iterator.Backable;

public interface RealRead extends Read{
	public Double read(Backable<Integer> reader) throws IOException;

	@Override
	default Object read(Object obj, Backable<Integer> reader) throws IOException{ return read(reader); }
}