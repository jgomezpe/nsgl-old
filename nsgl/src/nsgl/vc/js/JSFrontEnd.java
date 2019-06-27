package nsgl.vc.js;

import nsgl.vc.Controller;
import nsgl.vc.FrontEnd;
import nsgl.vc.KeyMapSide;

public class JSFrontEnd extends KeyMapSide implements FrontEnd{
	protected String url;
	
	public JSFrontEnd(){ super(FrontEnd.FRONTEND); }
	
	public void execute( String command ){}
	
	public String addTimer( Controller c, int delay ){ return null;}
	
	public String delTimer( Controller c, int delay ){ return null; }	
}