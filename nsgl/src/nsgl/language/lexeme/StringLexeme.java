package nsgl.language.lexeme;

import nsgl.exception.IO;
import nsgl.language.Lexeme;

public class StringLexeme extends Lexeme{
	protected static final String quotation = "\"";
	protected static final String other = "[^\\\\"+quotation+"]";
	protected static final String hexa = "[0-9A-F]";
	protected static final String hexacode = hexa+"{4}";
	protected static final String unicode = "u"+hexacode;
	protected static final String code = "[\\\\"+quotation+"/bfnrt]";
	protected static final String escape = "\\\\("+code+"|"+unicode+")";
	protected static final String any = "(" + other + "|" + escape + ")";

	public StringLexeme(char type ) { super(type, quotation+any+"*"+quotation); }

	public StringLexeme() { this('S'); }

	@Override
	public Object instance(String input) throws Exception {
		StringBuilder sb = new StringBuilder();
		if( input.length()<2 || input.charAt(0)!='"' || input.charAt(input.length()-1)!='"' ) throw IO.exception(IO.NOSTRING, input);
		int n = input.length()-1;
		int pos = 1;
		while(pos<n) {
			char[] c = CharLexeme.get(input, pos);
			sb.append(c[0]);
			pos += (int)(c[1]-'0');
		}
		return sb.toString();
	}
}
