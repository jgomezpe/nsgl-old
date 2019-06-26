package nsgl.language.lexeme;

import java.util.regex.Matcher;

import nsgl.exception.IO;
import nsgl.language.Lexeme;

public class SymbolLexeme  extends Lexeme{
	public SymbolLexeme(String symbols){ this('#',symbols); }
	public SymbolLexeme(char type, String symbols){ super(type, "["+symbols+"]"); }

	@Override
	public Object instance(String input) throws Exception {
		Matcher matcher = pattern.matcher(input); 
		if( matcher.find() && matcher.group().length()==input.length() ) return input.charAt(0);
		throw IO.exception(IO.UNEXPECTED, input, 0);
	}
}