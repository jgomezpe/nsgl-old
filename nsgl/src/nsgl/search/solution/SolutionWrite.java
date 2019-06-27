package nsgl.search.solution;

import java.io.IOException;
import java.io.Writer;

import nsgl.exception.IO;
import nsgl.search.Goal;
import nsgl.service.Write;
import nsgl.type.object.Tagged;
import nsgl.type.object.Writable;

public class SolutionWrite<T> implements Write {
	protected Goal<T,Double> goal;
	protected boolean write_object;
	
	public SolutionWrite( Goal<T,Double> goal, boolean write_object ) {
		this.goal = goal;
		this.write_object = write_object;
	}
	
	public void write(Tagged<T> sol, Writer out) throws IOException {
		try{
			out.write(""+goal.apply(sol));
			if( write_object ){
				out.write(' ');
				Writable w = Writable.cast(sol.unwrap());
				if( w != null ) w.write(out);
			}
		}catch(IOException e){ IO.exception(IO.OTHER, e); }
	}

	@SuppressWarnings("unchecked")
	@Override
	public void write(Object sol, Writer out) throws IOException{ write( (Tagged<T>)sol, out );	}
	
	@Override
	public String toString(){ return "SolutionWrite"; }
}