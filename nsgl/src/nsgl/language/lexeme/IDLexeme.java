package nsgl.language.lexeme;

import java.util.regex.Matcher;

import nsgl.exception.IO;
import nsgl.language.Lexeme;

public class IDLexeme extends Lexeme{

	public IDLexeme(){ super('W', "_*[[a-zA-Z]\\w*"); }
	
	@Override
	public Object instance(String input) throws Exception {
		Matcher matcher = pattern.matcher(input); 
		if( matcher.find() && matcher.group().length()==input.length() ) return input;
		throw IO.exception(IO.UNEXPECTED, input.charAt(matcher.group().length()), matcher.group().length());
	}
}