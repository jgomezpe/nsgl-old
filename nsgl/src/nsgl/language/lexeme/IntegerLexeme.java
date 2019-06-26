package nsgl.language.lexeme;

import nsgl.language.Lexeme;

public class IntegerLexeme extends Lexeme{
	public IntegerLexeme(char type, boolean signed){ super(type, (signed?"[-+]?":"")+"\\d+"); }
	public IntegerLexeme(boolean signed){ this('I', signed); }
	public IntegerLexeme(){ this(true); }

	@Override
	public int priority() { return 1; }

	@Override
	public Object instance(String input) throws Exception { return Integer.parseInt(input); }	
}