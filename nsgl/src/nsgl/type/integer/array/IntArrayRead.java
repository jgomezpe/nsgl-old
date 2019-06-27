package nsgl.type.integer.array;

import java.io.IOException;

import nsgl.service.Read;
import nsgl.type.collection.iterator.Backable;

public interface IntArrayRead extends Read{

	/**
	 * Reads an array from the input stream (the first value is the array's size and the following values are the values in the array)
	 * @param reader The reader object
	 * @throws ParamsException ParamsException
	 */
    public int[] read(Backable<Integer> reader) throws IOException;

    @Override
    default Object read(Object obj, Backable<Integer> reader) throws IOException{ return read(reader); }
}