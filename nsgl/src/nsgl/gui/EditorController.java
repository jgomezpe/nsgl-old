package nsgl.gui;

import nsgl.app.vc.Controller;

public interface EditorController extends Controller{
// Methods called by the View	
	public void text( String text );
	public void position( int row, int col );
}