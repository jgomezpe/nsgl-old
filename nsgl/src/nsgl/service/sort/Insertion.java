package nsgl.service.sort;

/**
 * <p>InsertionSort algorithm</p>
 *
 * <p>Copyright: Copyright (c) 2010</p>
 * 
 * @author Jonatan Gomez Perdomo
 * @version 1.0
 */
public class Insertion<T> extends Sort<T> {
  /**
   * Default constructor
   */
  public Insertion(){}

  /**
   * Crates a Insertion sort algorithm with the given order
   * @param order Order used for sorting the objects
   */
  public Insertion( Order order ){
      super( order );
  }

  /**
   * Creates a Insertion sort algorithm using the given order and overwriting array flag
   * @param order Order used for sorting the objects
   * @param overwrite If the array should be overwritten or not
   */
  public Insertion( Order order, boolean overwrite ){
      super( order, overwrite );
  }

  /**
   * Sorts a vector of objects using Insertion sort
   * @param a array to be sorted
   */
	public boolean apply(T[] a, int start, int end) {
		for (int i = start; i < end && continueFlag; i++) {
			int j = i - 1;
			T value = a[i];
			while(j >= start && compare(value, a[j])<0 && continueFlag) {
				a[j+1] = a[j];
				j--;
			}
			a[j+1] = value;
		}
		return true;
	}
}
