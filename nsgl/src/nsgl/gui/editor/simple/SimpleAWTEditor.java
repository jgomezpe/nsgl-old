package nsgl.gui.editor.simple;

import javax.swing.JScrollPane;
import javax.swing.text.JTextComponent;

import nsgl.gui.editor.AWTEditor;
import nsgl.io.Tokenizer;
import nsgl.type.keymap.KeyMap;

public class SimpleAWTEditor extends AWTEditor{
	public SimpleAWTEditor(String id){ super(id); }
	
	public void setStyle(KeyMap<String, SyntaxStyle> styles ){ ((SyntaxEditPanel)editArea).setStyle(styles); }
	
	public JTextComponent editArea(){
		if( editArea==null ) editArea = new SyntaxEditPanel();
		return editArea;	
	}
	
	public JScrollPane scroll(){
		if( scroll==null ) scroll = new JScrollPane();
		return scroll; 
	}
	
	@SuppressWarnings("unchecked")
	public void setTokenizer( Tokenizer tokenizer, KeyMap<Integer, ?> converter ){ ((SyntaxEditPanel)editArea).setTokenizer(tokenizer, (KeyMap<Integer, String>)converter); }
	
}