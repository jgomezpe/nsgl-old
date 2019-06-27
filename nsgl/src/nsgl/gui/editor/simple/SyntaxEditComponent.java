package nsgl.gui.editor.simple;

import nsgl.io.Tokenizer;
import nsgl.type.keymap.KeyMap;

public interface SyntaxEditComponent {
	public void setTokenizer(Tokenizer tokenizer, KeyMap<Integer,String> token_style);
	public void setStyle( KeyMap<String, SyntaxStyle> styles );
	public String getText();
	public void setText( String txt );
	public void update();
}
