package nsgl.agents.search.util;
import nsgl.agents.*;
import nsgl.agents.search.*;
/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2007</p>
 *
 * <p>Company: Universidad Nacional de Colombia</p>
 *
 * @author Jonatan Gómez
 * @version 1.0
 */
public class ConstantCost<T> implements ActionCost<T>{
  protected double c = 1.0;
  public ConstantCost() {
  }
  public ConstantCost( double _c ){
    c = _c;
  }

  public double evaluate( T state, Action action ){
    return c;
  }
}
