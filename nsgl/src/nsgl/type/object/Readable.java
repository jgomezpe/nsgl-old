package nsgl.type.object;

import java.io.IOException;

import nsgl.type.collection.iterator.Backable;
import nsgl.type.object.cast.Cast;

public interface Readable {
	/**
	 * Reads an object from the given reader
	 * @param reader The input stream from which the object will be read
	 * @return An object, of the type the read service is attending, that is read from the input stream
	 * @throws IOException IOException
	 */
	Object read(Backable<Integer> reader) throws IOException;
	
	static Readable cast( Object obj ){ 
		if( obj instanceof Readable ) return (Readable)obj;
    	@SuppressWarnings("unchecked")
		Cast<Readable> cast = (Cast<Readable>)Cast.service(obj,Readable.class);
		if( cast != null ) return cast.apply(obj); 
		return null; 
	}
}