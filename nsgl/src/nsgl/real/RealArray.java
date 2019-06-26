package nsgl.real;

import java.util.NoSuchElementException;

import nsgl.array.Array;
import nsgl.array.Fibo;
import nsgl.exception.NoSuchElement;

public class RealArray extends Fibo implements Array<Double>{
	protected double[] buffer;
	
	public RealArray( double[] buffer ){ this(buffer, buffer.length); }

	public RealArray( double[] buffer, int s ){
		this.buffer = buffer;
		size = s;
		find_fib(buffer.length);
	}

	public RealArray(){ this( new double[DEFAULT_C], 0 ); }

	/**
	 * Inserts a data element in the structure
	 * @param data Data element to be inserted
	 * @return <i>true</i> if the element could be added, <i>false</i> otherwise
	 */
	public boolean add(double data){
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

	public boolean set( int index, double data ) throws IndexOutOfBoundsException{
		if( 0 <= index && index < size ){
			buffer[index]=data;
			return true;
		}else{ throw new ArrayIndexOutOfBoundsException( index ); }
	}

	public boolean add( int index, double data ) throws IndexOutOfBoundsException{
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
		double[] new_buffer = new double[c];
		System.arraycopy(buffer, 0, new_buffer, 0, Math.min(size,c));
		buffer = new_buffer;		
	}  

	public double get( int index ){ return buffer[index]; }
	
	public double[] toPrimitiveArray(){
		double[] new_buffer = new double[size];
		System.arraycopy(buffer, 0, new_buffer, 0, size);
		return new_buffer;		
	}
	
	@Override
	public boolean add(Integer index, Double value){ return add((int)index, (double)value); }

	@Override
	public Double obtain(Integer index) throws NoSuchElementException{ 
		  if( index<0 || index>=size() ) throw NoSuchElement.exception( NoSuchElement.INDEXOUTBOUNDS, index );
		  return get((int)index);
	}

	@Override
	public boolean remove(Integer index) { return remove((int)index); }

	@Override
	public boolean set(Integer index, Double value) {
		set((int)index, (double)value);
		return true;
	}

	@Override
	public boolean add(Double value) { return add((double)value); }

	@Override
	public boolean del(Double value) { return del((double)value); }    
}