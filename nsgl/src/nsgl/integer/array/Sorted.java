package nsgl.integer.array;

import nsgl.integer.IntArray;
import nsgl.integer.IntOrder;

public class Sorted extends IntArray {
	
    protected IntSearch search;
    protected IntOrder IntOrder;

    public Sorted( int[] buffer, int n, IntOrder IntOrder ){
        super( buffer, n );
        this.IntOrder = IntOrder;
        search = new IntSearch(this.buffer, IntOrder);
    }

    public Sorted( int[] buffer, IntOrder IntOrder ){
        super( buffer );
        this.IntOrder = IntOrder;
        search = new IntSearch(this.buffer, IntOrder);
    }
    
    public Sorted( IntOrder IntOrder ){
    	super();
        this.IntOrder = IntOrder;
        search = new IntSearch(this.buffer, IntOrder);
    }

    public int findIndex( int data ){ return search.find(data); }

    public boolean add( int data ){
        int index = search.findRight(data);
        if( index == this.size() ){
              return super.add(data);
        }else{
            rightShift(index);
            buffer[index] = data;
        }
        return true;
    }

    public boolean set( int index, int data ) throws IndexOutOfBoundsException{
        if( 0 <= index && index < size ){
        	boolean flag = 	(index==0 || IntOrder.compare(buffer[index-1], data)<=0) &&
        					(index==size-1 || IntOrder.compare(data, buffer[index+1])<=0);
            if( flag ) buffer[index] = data;
            return flag;
        }else{
            throw new ArrayIndexOutOfBoundsException( index );
        }
    }

    public boolean add( int index, int data ) throws IndexOutOfBoundsException{
        if( 0 <= index && index <= size ){
        	boolean flag = 	(index==0 || IntOrder.compare(buffer[index-1], data)<=0) &&
        					(index==size || IntOrder.compare(data, buffer[index])<=0);
            if( flag ) super.add(index, data);
            return flag;
        }else{
            throw new ArrayIndexOutOfBoundsException( index );
        }
    }    
}