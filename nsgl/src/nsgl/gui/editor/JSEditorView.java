package nsgl.gui.editor;

import nsgl.app.DefaultComponent;
import nsgl.app.js.JSView;

public class JSEditorView extends DefaultComponent implements JSView, EditorView{

	public JSEditorView(String id){ super(id); }

	@Override
	public void highlight(int row){ execute("editor.highlight",row); }

	@Override
	public void locate(int row, int column){ execute("editor.locateCursor",row,column); }

	@Override
	public void setText(String txt){ execute("editor.setText",txt); }
}