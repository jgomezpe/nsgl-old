package nsgl.type.integer.variation;

import nsgl.search.variation.ParameterizedObject;
import nsgl.search.variation.Variation_1_1;
import nsgl.type.bit.random.RandBit;
import nsgl.type.integer.random.UniformInt;

/**
 * <p>Title: Mutation</p>
 * <p>Description: The simple bit mutation operator</p>
 * <p>Copyright: Copyright (c) 2010</p>
 * @author Jonatan Gomez
 * @version 1.0
 */

public class IntMutation implements Variation_1_1<int[]>, ParameterizedObject<Double> {
    
    protected UniformInt g;
  /**
   * Probability of mutating one single bit
   */
  protected double bit_mutation_rate = 0.0;

  /**
   * Constructor: Creates a mutation with a mutation probability depending on the size of the genome
   */
  public IntMutation( int MAX ) {
      g = new UniformInt(MAX);
  }

  /**
   * Constructor: Creates a mutation with the given mutation rate
   * @param bit_mutation_rate Probability of mutating each single bit
   */
  public IntMutation(double bit_mutation_rate, int MAX) {
	  this(MAX);
	  this.bit_mutation_rate = bit_mutation_rate;
  }

  /**
   * Flips a bit in the given genome
   * @param gen Genome to be modified
   * @return Number of mutated bits
   */
  @Override
  public int[] apply(int[] gen) {
      int[] genome = gen.clone();
      double rate = 1.0 - ((bit_mutation_rate == 0.0)?1.0/genome.length:bit_mutation_rate);
      RandBit gb = new RandBit(rate);
      for (int i = 0; i < genome.length; i++) if(gb.next()) genome[i] = g.next();
      return genome;
  }

  @Override
  public void setParameters(Double parameters){ bit_mutation_rate = parameters; }

  @Override
  public Double getParameters(){ return bit_mutation_rate; }
}
