package nsgl.service.sort;

/**
 * <p>BubbleSort algorithm</p>
 *
 * <p>Copyright: Copyright (c) 2010</p>
 * 
 * @author Jonatan Gomez Perdomo
 * @version 1.0
 */
public class Bubble<T> extends Sort<T> {

  /**
   * Default constructor
   */
  public Bubble(){}

  /**
   * Crates a bubble sort algorithm with the given order
   * @param order Order used for sorting the objects
   */
  public Bubble( Order order ){
      super( order );
  }

  /**
   * Creates a bubble sort algorithm using the given order and overwriting array flag
   * @param order Order used for sorting the objects
   * @param overwrite If the array should be overwrited or not
   */
  public Bubble( Order order, boolean overwrite ){
      super( order, overwrite );
  }

    /**
     * Sorts a portion of the array of objects according to the given order (it does not creates a new array)
     * @param a Array of objects to be sorted
     * @param start Initial position in the array to be sorted
     * @param end Final position in the array to be sorte
     * @return <i>true</i> If the sorting process was done without fails, <i>false</i> otherwise
     */
	public boolean apply(T[] a, int start, int end) {
		for(int i = start; i < end - 1 && continueFlag; i++)
			for(int j = i + 1; j < end && continueFlag; j++)
				if(compare(a[j], a[i])<0) {
					T x = a[i];
					a[i] = a[j];
					a[j] = x;
				}
        return true;
	}
}