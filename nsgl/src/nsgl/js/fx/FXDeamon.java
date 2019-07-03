package nsgl.js.fx;

import java.io.IOException;

import nsgl.app.vc.FrontEnd;
import nsgl.java.reflect.Command;

public class FXDeamon extends Thread{
	protected FrontEnd view;
	protected boolean done=false;
	protected Command command;
	
	public FXDeamon( FrontEnd view, Command command ){
		this.view = view;
		this.command = command;
	}
	
	public void run(){
		try { view.execute(command); } catch (IOException e) { e.printStackTrace(); }
		done = true;
	}
	
	public boolean done(){ return done; }
}