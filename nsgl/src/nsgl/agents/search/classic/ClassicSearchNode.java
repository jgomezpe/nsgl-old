package nsgl.agents.search.classic;
import nsgl.agents.*;
import nsgl.agents.search.GraphSpace;
import nsgl.agents.search.PathUtil;
import nsgl.type.array.Vector;

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
public class ClassicSearchNode<T> {
  protected Vector<Action> path;
  protected double cost;
  public ClassicSearchNode( Vector<Action> _path, double _cost ) {
    path = _path;
    cost = _cost;
  }
  
  public Vector<Action> path(){
	  return path;
  }
  
  public T state( GraphSpace<T> space, T initial_state ){
	  PathUtil<T> util = new PathUtil<T>();
	  return util.final_state(initial_state, space, path);
  }
}
