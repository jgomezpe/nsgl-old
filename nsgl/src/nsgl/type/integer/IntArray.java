package nsgl.type.integer;

import java.util.NoSuchElementException;

import nsgl.exception.NoSuch;
import nsgl.type.array.Array;
import nsgl.type.array.Fibo;

public class IntArray extends Fibo implements Array<Integer>{
	protected int[] buffer;
	
	public IntArray( int[] buffer ){ this(buffer, buffer.length); }

	public IntArray( int[] buffer, int s ){
		this.buffer = buffer;
		size = s;
		find_fib(buffer.length);
	}

	public IntArray(){ this( new int[DEFAULT_C], 0 ); }

	/**
	 * Inserts a data element in the structure
	 * @param data Data element to be inserted
	 * @return <i>true</i> if the element could be added, <i>false</i> otherwise
	 */
	public boolean add(int data){
		if( buffer.length == size ) grow();
		buffer[size]=data;
		size++;
		return true;
	}

	protected void leftShift( int index ) throws IndexOutOfBoundsException{
		size--;
		if( index < size ) System.arraycopy(buffer, index+1, buffer, index, size-index);
		if( size < a ) shrink();
	}

	protected void rightShift( int index ) throws IndexOutOfBoundsException{
		if( buffer.length == size ) grow();
		System.arraycopy(buffer, index, buffer, index+1, buffer.length-index-1);
		size++;
	}

	public boolean set( int index, int data ) throws IndexOutOfBoundsException{
		if( 0 <= index && index < size ){
			buffer[index]=data;
			return true;
		}else{ throw new ArrayIndexOutOfBoundsException( index ); }
	}

	public boolean add( int index, int data ) throws IndexOutOfBoundsException{
		rightShift(index);
		buffer[index]=data;
		return true;
	}

	public boolean remove( int index ) throws IndexOutOfBoundsException{
		leftShift(index);
		return true;
	}

	/**
	 * Sets the size of the array
	 * @param n The new size of the array
	 */
	public void resize(){
		int[] new_buffer = new int[c];
		System.arraycopy(buffer, 0, new_buffer, 0, Math.min(size,c));
		buffer = new_buffer;		
	}  

	public int get( int index ){ return buffer[index]; }
	
	public int[] toPrimitiveArray(){
		int[] new_buffer = new int[size];
		System.arraycopy(buffer, 0, new_buffer, 0, size);
		return new_buffer;		
	}
	
    /**
     * Transforms a Vector into int array
     * @param v vector of integers to be converted
     * @return integer array
     */
    /*public static int[] get(ImmutableArray<Integer> v) {
        int n = v.size();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = v.get(i);
        }
        return a;
    }*/

	@Override
	public boolean add(Integer index, Integer value){ return add((int)index, (int)value); }

	@Override
	public Integer obtain(Integer index) throws NoSuchElementException{ 
		  if( index<0 || index>=size() ) throw NoSuch.exception( NoSuch.INDEXOUTBOUNDS, index );
		  return get((int)index);
	}

	@Override
	public boolean remove(Integer index) { return remove((int)index); }

	@Override
	public boolean set(Integer index, Integer value) {
		set((int)index, (int)value);
		return true;
	}

	@Override
	public boolean add(Integer value) { return add((int)value); }

	@Override
	public boolean del(Integer value) { return del((int)value); }    
}