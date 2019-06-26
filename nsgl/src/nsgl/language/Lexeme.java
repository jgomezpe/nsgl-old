package nsgl.language;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Lexeme {
	protected char type;
	protected String regex;
	protected Pattern pattern;
	
	public Lexeme( char type, String regex ){
		this.type = type;
		this.regex = regex;
		pattern = Pattern.compile("^("+regex+")");
	}
	
	public int priority() { return 0; }
	
	public char type(){ return type; }
	public String regex(){ return regex; }
	
	public Token token( String input ){
		Matcher matcher = pattern.matcher(input); 
		if( matcher.find() && matcher.group().length()==input.length() ) return new Token(type, 0, input);
		return null;
	}
	
	public abstract Object instance( String input ) throws Exception;
}
