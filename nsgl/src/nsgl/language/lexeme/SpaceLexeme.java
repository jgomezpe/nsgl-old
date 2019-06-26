package nsgl.language.lexeme;

import java.util.regex.Matcher;

import nsgl.exception.IO;
import nsgl.language.Lexeme;

public class SpaceLexeme extends Lexeme{
	protected String symbol = "\b\t\r\n";

	public SpaceLexeme(){ this(' '); }
	
	public SpaceLexeme(char type){ super(type, "\\s"); }
	
	@Override
	public Object instance(String input) throws Exception {
		Matcher matcher = pattern.matcher(input); 
		if( matcher.find() && matcher.group().length()==input.length() ) return ' ';
		if( matcher.start()!=0)	throw IO.exception(IO.UNEXPECTED, input.substring(0, matcher.start()), 0);
		int s = matcher.group().length();
		throw IO.exception(IO.UNEXPECTED, input.substring(s), s);
	}
}