package nsgl.type.real.array;

import nsgl.type.real.RealArray;
import nsgl.type.real.RealOrder;

public class SortedRealArray extends RealArray {
	
    protected RealArraySearch search;
    protected RealOrder RealOrder;

    public SortedRealArray( double[] buffer, int n, RealOrder RealOrder ){
        super( buffer, n );
        this.RealOrder = RealOrder;
        search = new RealArraySearch(this.buffer, RealOrder);
    }

    public SortedRealArray( double[] buffer, RealOrder RealOrder ){
        super( buffer );
        this.RealOrder = RealOrder;
        search = new RealArraySearch(this.buffer, RealOrder);
    }

    public int findIndex( double data ){ return search.find(data); }

    public boolean add( double data ){
        int index = search.findRight(data);
        if( index == this.size() ){
              return super.add(data);
        }else{
            rightShift(index);
            buffer[index] = data;
        }
        return true;
    }

    public boolean set( int index, double data ) throws IndexOutOfBoundsException{
        if( 0 <= index && index < size ){
        	boolean flag = 	(index==0 || RealOrder.compare(buffer[index-1], data)<=0) &&
        					(index==size-1 || RealOrder.compare(data, buffer[index+1])<=0);
            if( flag ) buffer[index] = data;
            return flag;
        }else{
            throw new ArrayIndexOutOfBoundsException( index );
        }
    }

    public boolean add( int index, double data ) throws IndexOutOfBoundsException{
        if( 0 <= index && index <= size ){
        	boolean flag = 	(index==0 || RealOrder.compare(buffer[index-1], data)<=0) &&
        					(index==size || RealOrder.compare(data, buffer[index])<=0);
            if( flag ) super.add(index, data);
            return flag;
        }else{
            throw new ArrayIndexOutOfBoundsException( index );
        }
    }    
}