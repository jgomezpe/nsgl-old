package nsgl.language.lexeme;

import nsgl.language.Lexeme;

public class RealLexeme extends Lexeme{
	public RealLexeme(char type, boolean signed){ super(type, (signed?"[-+]?":"")+"\\d+(\\.\\d+)?([eE][+-]?\\d+)?"); }
	public RealLexeme(boolean signed){ this('D', signed); }
	public RealLexeme(){ this(true); }

	@Override
	public Object instance(String input) throws Exception { return Double.parseDouble(input); }		
}