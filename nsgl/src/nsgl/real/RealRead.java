package nsgl.real;

import java.io.IOException;

import nsgl.iterator.Backable;
import nsgl.service.Read;

public interface RealRead extends Read{
	public Double read(Backable<Integer> reader) throws IOException;

	@Override
	default Object read(Object obj, Backable<Integer> reader) throws IOException{ return read(reader); }
}