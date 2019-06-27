package nsgl.exception;

import java.util.NoSuchElementException;

import nsgl.type.array.ArrayUtil;

public class NoSuchElement {
	public static final String NOSUCHELEMENT = "NoSuchElementException";
	public static final String INDEXOUTBOUNDS = "indexoutbounds";
	public static final String INVALIDLOCATION = "invalidlocation";
	
	public static NoSuchElementException exception( Object... args){ return new NoSuchElementException(ArrayUtil.store(args)); }
}
