package nsgl.gui;

import nsgl.app.vc.View;

public interface ConsoleView extends View{
    public void display( boolean output );	
	public void error( String message );
	public void out( String message );
}