package nsgl.type.real.array;

import java.io.IOException;

import nsgl.service.Read;
import nsgl.type.collection.iterator.Backable;

public interface RealArrayRead extends Read{
	public double[] read( Backable<Integer> reader ) throws IOException;
	default Object read( Object obj, Backable<Integer> reader ) throws IOException{ return read(reader); }
}