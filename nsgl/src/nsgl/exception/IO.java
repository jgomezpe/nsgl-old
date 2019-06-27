package nsgl.exception;

import java.io.IOException;

import nsgl.type.array.ArrayUtil;

public class IO {
	public static final String IOEXCEPTION = "IOException";
	public static final String EOI = "eoi";
	public static final String NOFOUND = "nofound";
	public static final String DIGIT = "digit";
	public static final String NOUNICODE = "nounicode";
	public static final String ESC_CHAR = "escchar";
	public static final String NOCHAR = "nochar";
	public static final String UNEXPECTED = "unexpected";
	public static final String NOSTRING = "nostring";
	public static final String NOESCAPE = "noescapechar";
	public static final String OTHER = "other";
	
	public static IOException exception( Object... args ){ return new IOException(ArrayUtil.store(args)); }
}
