package nsgl.type.real.array;

import nsgl.type.real.RealOrder;

public class RealArraySearch {
	protected RealOrder RealOrder;
	protected double[] sorted;
	
    /**
     * Creates a search operation for the given sorted array
     * @param sorted Array of elements (should be sorted)
     */
	public RealArraySearch(double[] sorted){ this( sorted, new RealOrder() ); }

    /**
     * Creates a search operation for the given sorted array
     * @param sorted Array of elements (should be sorted)
     * @param RealOrder RealOrder used for locating the object
     */
	public RealArraySearch(double[] sorted, RealOrder RealOrder){
		this.RealOrder = RealOrder;
		this.sorted = sorted;
	}
	
	public void set( double[] sorted ){ this.sorted = sorted; } 

    /**
     * Searches for the position of the given element. The vector should be sorted
     * @param x Element to be located
     * @return The position of the given object, -1 if the given object is not in the array
     */
    public int find(double x) { return find( 0, sorted.length, x ); }

    /**
     * Searches for the position of the given element. The vector should be sorted
     * @param x Element to be located
     * @return The position of the given object, -1 if the given object is not in the array
     */
    public int find(int start, int end, double x) {
        int pos = findRight(start, end, x);
        if (pos > start && RealOrder.compare(x, sorted[pos - 1]) == 0) pos--;
        else pos = -1;
        return pos;
    }

    /**
     * Determines if the sorted array contains the given element (according to the associated RealOrder)
     * @param x Element to be located
     * @return <i>true</i> if the element belongs to the sorted array, <i>false</i> otherwise
     */
    public boolean contains(int start, int end, double x){ return (find(start, end, x) != -1); }

    /**
     * Determines if the sorted array contains the given element (according to the associated RealOrder)
     * @param x Element to be located
     * @return <i>true</i> if the element belongs to the sorted array, <i>false</i> otherwise
     */
    public boolean contains(double x) { return (find(x) != -1); }

    /**
     * Searches for the position of the first element in the array that is bigger
     * than the element given. The array should be sorted
     * @param x Element to be located
     * @return Position of the object that is bigger than the given element
     */
    public int findRight(double x) { return findRight( 0, sorted.length, x ); }

    /**
     * Searches for the position of the first element in the array that is bigger
     * than the element given. The array should be sorted
     * @param x Element to be located
     * @return Position of the object that is bigger than the given element
     */
    public int findRight(int start, int end, double x) {
        if (end > start) {
            int a = start;
            int b = end - 1;
            if (RealOrder.compare(x, sorted[a]) < 0) return start;
            if (RealOrder.compare(x, sorted[b]) >= 0) return end;
            while (a + 1 < b) {
                int m = (a + b) / 2;
                if (RealOrder.compare(x, sorted[m]) < 0) b = m;
                else a = m;
            }
            return b;
        }else return start;
    }

    /**
     * Searches for the position of the last element in the array that is smaller
     * than the element given. The array should be sorted
     * @param x Element to be located
     * @return Position of the object that is smaller than the given element
     */
    public int findLeft(double x){ return findLeft( 0, sorted.length, x ); }

    /**
     * Searches for the position of the last element in the array that is smaller
     * than the element given. The array should be sorted
     * @param x Element to be located
     * @return Position of the object that is smaller than the given element
     */
    public int findLeft(int start, int end, double x) {
        if (end > start) {
            int a = start;
            int b = end - 1;
            if (RealOrder.compare(x, sorted[a]) <= 0)  return start-1;
            if (RealOrder.compare(x, sorted[b]) > 0) return b;
            while (a + 1 < b) {
                int m = (a + b) / 2;
                if (RealOrder.compare(x, sorted[m]) <= 0) b = m;
                else a = m;
            }
            return a;
        }else return start;
    }
}
