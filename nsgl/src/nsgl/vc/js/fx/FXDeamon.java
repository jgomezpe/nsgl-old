package nsgl.vc.js.fx;

import nsgl.vc.js.JSFrontEnd;

public class FXDeamon extends Thread{
	protected JSFrontEnd view;
	protected boolean done=false;
	protected String command;
	
	public FXDeamon( JSFrontEnd view, String command ){
		this.view = view;
		this.command = command;
	}
	
	public void run(){
		view.execute(command);
		done = true;
	}
	
	public boolean done(){ return done; }
}
