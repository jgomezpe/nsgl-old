package nsgl.language.lexeme;

import nsgl.exception.IO;
import nsgl.language.Lexeme;

public class CharLexeme extends Lexeme{
	protected static final String quotation = "'";
	protected static final String other = "[^\\\\"+quotation+"]";
	protected static final String hexa = "[0-9A-F]";
	protected static final String hexacode = hexa+"{4}";
	protected static final String unicode = "u"+hexacode;
	protected static final String code = "[\\\\"+quotation+"/bfnrt]";
	protected static final String escape = "\\\\("+code+"|"+unicode+")";
	protected static final String any = "(" + other + "|" + escape + ")";

	public CharLexeme() { this('C'); }
	
	public CharLexeme(char type){ super(type, quotation+any+quotation); }

	public static char escape( char c ){
		switch( c ){
			case '\'': case '\\': case '/': return c;
			case 'b': return '\b';
			case 'f': return '\f';
			case 'n': return '\n';
			case 'r': return '\r';
			case 't': return '\t';
			case '0': return '\0';
			default: return c; 
		}
	}

	public static char[] get( String input, int pos ) throws Exception{
		char c = input.charAt(pos);
		if(c=='\\'){
			pos++;
			c = input.charAt(pos);
			char ec = escape(c);
			if( ec!=c ) return new char[]{ec, '2'};
			if( c=='"' ) return new char[]{'"', '2'};
			if( c=='\'' ) return new char[]{'\'', '2'};
			if(c=='u'){
				pos++;
				String uc = input.substring(pos,pos+4);
				int k=Integer.parseInt(uc,16);
				c = (char)k;
				return new char[]{c,'6'};
			}
			throw IO.exception(IO.NOESCAPE, c, pos);
		}
		return new char[] {c,'1'};
	}

	@Override
	public Object instance(String input) throws Exception {
		if( input.length()<3 || input.charAt(0)!='\'' || input.charAt(input.length()-1)!='\'' ) throw IO.exception(IO.NOCHAR, input);
		input = input.substring(1, input.length()-1);
		char[] c = get( input, 0 );
		return c[0];
	}
}