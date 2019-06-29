package nsgl.exception;

import java.util.NoSuchElementException;

import nsgl.type.array.ArrayUtil;

public class NoSuch {
	public static final String NOSUCHELEMENT = "nosuchelement";
	public static final String NOSUCHMETHOD = "nosuchmethod";
	public static final String INDEXOUTBOUNDS = "indexoutbounds";
	
	public static NoSuchElementException exception( Object... args){ return new NoSuchElementException(ArrayUtil.store(args)); }
}
