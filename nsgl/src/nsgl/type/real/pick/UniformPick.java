package nsgl.type.real.pick;

import nsgl.type.integer.random.UniformInt;

public class UniformPick implements Pick{
	  /**
	   * Default constructor
	   */
	  public UniformPick(){ }

	  /**
	   * Selects a candidate solution from a set of candidate solutions
	   * @param g Uniform integer number generator used for picking the candidate solution
	   * @param q Quality associated to each candidate solution
	   * @return Index of the selected candidate solution
	   */
	  protected int choose_one( UniformInt g, double[] q ){
	    return g.next();
	  }

	  /**
	   * Selects a candidate solution from a set of candidate solutions
	   * @param q Quality associated to each candidate solution
	   * @return Index of the selected candidate solution
	   */
	  @Override
	  public int choose_one( double[] q ){
	    return choose_one(new UniformInt(q.length), q);
	  }

	  /**
	   * Selects a subset of candidate solutions from a set of candidates
	   * @param n Number of candidate solutions to be selected
	   * @param q Quality associated to each candidate solution
	   * @return Indices of the selected candidate solutions
	   */
	  @Override
	  public int[] apply( int n, double[] q){
		UniformInt g =  new UniformInt(q.length);
	    return g.generate(n);
	  }
	  
}