package nsgl.type.integer;

import java.io.IOException;

import nsgl.service.Read;
import nsgl.type.collection.iterator.Backable;

public interface IntRead extends Read{
	public Integer read( Backable<Integer> reader ) throws IOException;
	
	@Override
	default Object read(Object one, Backable<Integer> reader) throws IOException { return read(reader); }	
}