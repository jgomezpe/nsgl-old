package nsgl.type.real.matrix;

import java.io.IOException;

import nsgl.service.io.Read;
import nsgl.type.collection.iterator.Backable;

public interface RealMatrixRead extends Read{
	public double[][] read( Backable<Integer> reader ) throws IOException;
	default Object read( Object obj, Backable<Integer> reader ) throws IOException{ return read(reader); }
}