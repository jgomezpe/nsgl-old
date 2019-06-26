package nsgl.real.array;

import java.io.IOException;

import nsgl.iterator.Backable;
import nsgl.service.Read;

public interface RealArrayRead extends Read{
	public double[] read( Backable<Integer> reader ) throws IOException;
	default Object read( Object obj, Backable<Integer> reader ) throws IOException{ return read(reader); }
}