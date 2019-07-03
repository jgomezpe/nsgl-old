package nsgl.gui;

import nsgl.app.vc.View;

public interface EditorView extends View{
	public void setText( String text );
	public void highlight( int row );
	public void locate( int row, int col );
}