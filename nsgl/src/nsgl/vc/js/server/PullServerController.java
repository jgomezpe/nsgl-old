package nsgl.vc.js.server;

import nsgl.vc.BackEnd;
import nsgl.vc.Controller;
import nsgl.vc.DefaultComponent;
import nsgl.vc.Side;

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