package nsgl.type.array;

import nsgl.exception.IO;
import nsgl.language.Language;
import nsgl.service.Order;
import nsgl.type.object.parser.ObjectLexer;
import nsgl.type.object.parser.ObjectMeaner;
import nsgl.type.object.parser.ObjectParser;
import nsgl.type.string.StringUtil;

/**
 *
 * @author jgomez
 */
public class ArrayUtil {
    public static <T> void rightRotation( int start, int end, T[] a ){
    	System.arraycopy(a, start, a, start+1, end-start);
    }

    public static <T> void leftRotation( int start, int end, T[] a ){
    	System.arraycopy(a, start+1, a, start, end-start);
    }
    
    public static <T> void insert( int n, T[] a, T x, int i ){
        rightRotation( i, n, a );
        a[i] = x;
    }
    
    public static <T> void del( int n, T[] a, int i ){
        leftRotation(i, n-1, a);
    }

	public static <T> int indexForAddToSortArray( int n, T[] a, T x, Order order ){
        BinarySearch<T> search = new BinarySearch<T>(a,order);
    	if( a.length <= n ) return -1;
    	if( n==0 ){    		
    		return 0;
    	}
		int k = search.findLeft(0, n, x);
    	if( k<n-1 ){
    		if( order.compare(x, a[k+1]) != 0 ){
    			return k+1;
    		}
    		return -1;
    	}
    	return n;
    }
    
	public static <T> boolean addToSortArray( int n, T[] a, T x, Order order ){
    	int k = indexForAddToSortArray(n, a, x, order);
    	if( k>=0 ){
			insert(n, a, x, k);
			return true;
    	}
    	return false;
    }

	public static <T> boolean removeFromSortArray( int n, T[] a, T x, Order order ){
    	if( a.length > 0 && n>0 ){
            BinarySearch<T> search = new BinarySearch<T>(a,order);
			int k = search.find(0, n, x);
	    	if( k>=0 ){
	    		del(n, a, k);
	    		return true;
	    	}
    	}
    	return false;
    }
    
	public static <T> int findInSortArray( int n, T[] a, T x,  Order order ){
        BinarySearch<T> search = new BinarySearch<T>(a,order);
    	return search.find(0,  n, x);
    }    
	
	public static Object[] cast( Object... objects ){ return objects; }
	
	protected static String store1( Object obj ){
		if( obj.getClass().isArray() ) return store((Object[])obj);
		if( obj instanceof String ) return StringUtil.store((String)obj);
		if( obj instanceof Character ) return StringUtil.store(""+obj);
		return obj.toString();
	}
	
	protected static Language<Object> arrayLang = null;
	
	public static String store( Object... args ){
		StringBuilder sb = new StringBuilder();
		String comma="";
		sb.append('[');
		for( int i=0; i<args.length; i++ ){
			sb.append(comma);
			sb.append(store1(args[i]));
			comma =",";
		}
		sb.append(']');
		return sb.toString();	
	}	
	
	public static Object[] parse(String input) throws Exception{
		if( arrayLang==null ) {
			ObjectLexer lexer = new ObjectLexer();
			ObjectParser parser = new ObjectParser();
			ObjectMeaner meaner = new ObjectMeaner(lexer);
			arrayLang = new Language<Object>(lexer, parser, meaner);
		}
		Object obj =  arrayLang.process(input);
		if( obj instanceof Object[] ) return (Object[])obj;
		throw IO.exception(IO.NOFOUND,'[',0);
	}
}