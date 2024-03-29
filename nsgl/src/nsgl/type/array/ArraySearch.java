package nsgl.type.array;

import nsgl.service.sort.Order;

/**
 * <p>Searching algorithm for sorted arrays of objects</p>
 * 
 * <p>Copyright: Copyright (c) 2010</p>
 * 
 * @author Jonatan Gomez Perdomo
 * @version 1.0
 */
public class ArraySearch<T> {
	protected Order order=null;
	protected Array<T> sorted=null;
	
    /**
     * Creates a search operation for the given sorted array
     * @param sorted Array of elements (should be sorted)
     * @param order Order used for locating the object
     */
	public ArraySearch(){}

    /**
     * Creates a search operation for the given sorted array
     * @param sorted Array of elements (should be sorted)
     * @param order Order used for locating the object
     */
	public ArraySearch(Array<T> sorted, Order order){
		this.order = order;
		this.sorted = sorted;
	}
	
	public void set(Array<T> sorted){ this.sorted=sorted; }

	public void set(Order order){ this.order=order; }

    /**
     * Searches for the position of the given element. The vector should be sorted
     * @param x Element to be located
     * @return The position of the given object, -1 if the given object is not in the array
     */
    public int find(T x) { return find( 0, sorted.size(), x ); }
    
    /**
     * Searches for the position of the given element. The vector should be sorted
     * @param x Element to be located
     * @return The position of the given object, -1 if the given object is not in the array
     */
    public int find(int start, int end, T x) {
    	try{
    		int pos = findRight(start, end, x);
    		if (pos > start && order.compare(x, sorted.get(pos-1)) == 0) return pos--;
    	}catch(Exception e){}	
		return -1;
    }

    /**
     * Determines if the sorted array contains the given element (according to the associated order)
     * @param x Element to be located
     * @return <i>true</i> if the element belongs to the sorted array, <i>false</i> otherwise
     */
    public boolean contains(int start, int end, T x){ return (find(start, end, x) != -1); }

    /**
     * Determines if the sorted array contains the given element (according to the associated order)
     * @param x Element to be located
     * @return <i>true</i> if the element belongs to the sorted array, <i>false</i> otherwise
     */
    public boolean contains(T x) { return (find(x) != -1); }

    /**
     * Searches for the position of the first element in the array that is bigger
     * than the element given. The array should be sorted
     * @param x Element to be located
     * @return Position of the object that is bigger than the given element
     */
    public int findRight(T x) { return findRight( 0, sorted.size(), x ); }

    /**
     * Searches for the position of the first element in the array that is bigger
     * than the given element. The array should be sorted
     * @param x Element to be located
     * @return Position of the object that is bigger than the given element
     */
    public int findRight(int start, int end, T x) {
        if (end > start) {
            int a = start;
            int b = end - 1;
            try{
            	if (order.compare(x, sorted.get(a)) < 0) return start;
            	if (order.compare(x, sorted.get(b)) >= 0) return end;
            	while (a + 1 < b) {
            		int m = (a + b) / 2;
            		if (order.compare(x, sorted.get(m)) < 0) b = m;
            		else a = m;
            	}
            }catch(Exception e ){}
            return b;
        }else return start;
    }

    /**
     * Searches for the position of the last element in the array that is smaller
     * than the element given. The array should be sorted
     * @param x Element to be located
     * @return Position of the object that is smaller than the given element
     */
    public int findLeft(T x){ return findLeft( 0, sorted.size(), x ); }

    /**
     * Searches for the position of the last element in the array that is smaller
     * than the element given. The array should be sorted
     * @param x Element to be located
     * @return Position of the object that is smaller than the given element
     */
    public int findLeft(int start, int end, T x) {
        if (end > start) {
            int a = start;
            int b = end - 1;
            try{
            	if (order.compare(x, sorted.get(a)) <= 0)  return start-1;
            	if (order.compare(x, sorted.get(b)) > 0) return b;
            	while (a + 1 < b) {
            		int m = (a + b) / 2;
            		if (order.compare(x, sorted.get(m)) <= 0) b = m;
            		else a = m;
            	}
            }catch(Exception e ){}
            return a;
        }else return start;
    }
}