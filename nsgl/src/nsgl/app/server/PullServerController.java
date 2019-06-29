package nsgl.app.server;

import nsgl.app.BackEnd;
import nsgl.app.Controller;
import nsgl.app.DefaultComponent;
import nsgl.app.side.Side;

public class PullServerController extends DefaultComponent implements Controller{
	protected BackEnd backend; 
	public static final String SERVER = "servlet";

	public PullServerController( BackEnd backend ){
		super( SERVER ); 
		this.backend = backend; 
	}

	public String pull(){ return ((VCManager)front()).queue(); }
	
	@Override
	public Side side(){ return backend; }
}