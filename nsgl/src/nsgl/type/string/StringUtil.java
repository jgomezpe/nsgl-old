package nsgl.type.string;

import nsgl.language.Lexeme;
import nsgl.language.lexeme.CharLexeme;
import nsgl.language.lexeme.IntLexeme;
import nsgl.language.lexeme.RealLexeme;
import nsgl.language.lexeme.StringLexeme;

public class StringUtil{
	protected static Lexeme str = new StringLexeme();
	protected static Lexeme integer = new IntLexeme();
	protected static Lexeme real = new RealLexeme();
	protected static Lexeme character = new CharLexeme();
	
	public static String store( String str ){
		StringBuilder sb = new StringBuilder();
		sb.append('"');
		for( int i=0; i<str.length(); i++ ){
			char c = str.charAt(i);
			switch( c ){
				case '/': sb.append("\\/"); break;	
				case '\\': sb.append("\\\\"); break;
				case '\b': sb.append("\\b"); break;
				case '\f': sb.append("\\f"); break;
				case '\n': sb.append("\\n"); break;
				case '\r': sb.append("\\r"); break;
				case '\t': sb.append("\\t"); break;
				case '\'': sb.append("\'"); break;
				case '"': sb.append("\\\""); break;
				default:
					if( c < 32 || c > 255 ){
						sb.append("\\u");
						sb.append(Integer.toHexString((int)c));
					}else sb.append(c);
				break;
			}
		}
		sb.append('"');
		return sb.toString();	
	}
	
	public static String parse( String txt ) throws Exception{ return (String)str.instance(txt); }
}