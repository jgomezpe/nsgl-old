package nsgl.language.lexeme;

import nsgl.language.Lexeme;

public class IntLexeme extends Lexeme{
	public IntLexeme(char type, boolean signed){ super(type, (signed?"[-+]?":"")+"\\d+"); }
	public IntLexeme(boolean signed){ this('I', signed); }
	public IntLexeme(){ this(true); }

	@Override
	public int priority() { return 1; }

	@Override
	public Object instance(String input) throws Exception { return Integer.parseInt(input); }	
}