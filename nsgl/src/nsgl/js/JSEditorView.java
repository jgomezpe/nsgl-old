package nsgl.js;

import nsgl.gui.EditorView;

public class JSEditorView extends JSView implements EditorView{
	public JSEditorView(String id){ super("editor",id); }

	@Override
	public void highlight(int row){ execute("highlight",row); }

	@Override
	public void locate(int row, int column){ execute("locateCursor",row,column); }

	@Override
	public void setText(String txt){ execute("setText",txt); }
}