package nsgl.type.bit.variation;
import nsgl.search.variation.Variation_2_2;
import nsgl.type.bit.BitArray;
import nsgl.type.integer.random.UniformInt;

/**
 * <p>Title: XOver</p>
 * <p>Description: The simple point crossover operator (variable length)</p>
 * <p>Copyright: Copyright (c) 2010</p>
 * @author Jonatan Gomez
 * @version 1.0
 */

public class XOver implements Variation_2_2<BitArray>{

	protected UniformInt g = new UniformInt(0);
    
	public XOver(){}

  /**
   * The crossover point of the last xover execution
   */
  protected int cross_over_point;

  /**
   * Apply the simple point crossover operation over the given genomes at the given
   * cross point
   * @param child1 The first parent
   * @param child2 The second parent
   * @param xoverPoint crossover point
   * @return The crossover point
   */
  protected BitArray[] apply(BitArray child1, BitArray child2, int xoverPoint) {
      try{
          BitArray child1_1 = new BitArray(child1);
          BitArray child2_1 = new BitArray(child2);
          BitArray child1_2 = new BitArray(child1);
          BitArray child2_2 = new BitArray(child2);

          cross_over_point = xoverPoint;

          child1_2.leftSetToZero(cross_over_point);
          child2_2.leftSetToZero(cross_over_point);
          child1_1.rightSetToZero(cross_over_point);
          child2_1.rightSetToZero(cross_over_point);
          child2_1.or(child1_2);
          child1_1.or(child2_2);
          return new BitArray[]{child1_1, child2_1};
      }catch( Exception e ){}
      return null;
  }

  /**
   * Apply the simple point crossover operation over the given genomes
   * @param child1 The first parent
   * @param child2 The second parent
   * @return The crossover point
   */
    @Override
  public BitArray[] apply( BitArray child1, BitArray child2 ){
    g.set(Math.min(child1.size(), child2.size())); 
    return apply(child1, child2, g.next());
  }    
}