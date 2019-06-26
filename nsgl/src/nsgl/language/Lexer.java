package nsgl.language;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import nsgl.language.lexeme.CharLexeme;
import nsgl.exception.IO;
import nsgl.language.lexeme.IntegerLexeme;
import nsgl.language.lexeme.RealLexeme;
import nsgl.language.lexeme.SpaceLexeme;
import nsgl.language.lexeme.StringLexeme;
import nsgl.language.lexeme.SymbolLexeme;
import nsgl.vector.Vector;

public class Lexer extends LexemeSet{
	public Lexer(){}
	public Lexer( LexemeSet source ){ for( Lexeme l:source.lexemes ) this.add(l); }
	
	protected Vector<Token> process( Pattern pattern, String input, int start ){
		Matcher matcher = pattern.matcher(input);
		Vector<Token> tokens = new Vector<Token>();
		if( matcher.find(start) ) {
			tokens.add(get(matcher.group(),matcher.start()));
			while( matcher.find() ) tokens.add(get(matcher.group(),matcher.start()));
		}
		return tokens;
	}
	
	public Vector<Token> process( String input, int start ){ return process( pattern(), input, start ); }

	public Vector<Token> process( String input ){ return process( pattern(), input, 0 ); }

	public Vector<Token> analize( String input, int start ) throws IOException{
		Vector<Token> tokens = process( pattern(), input, start );
		Vector<Object> exceptions = new Vector<Object>();
		for( Token t:tokens ) {
			if( t.pos()!=start ) {
				exceptions.add(IO.UNEXPECTED);
				exceptions.add(input.substring(start, t.pos()));
				exceptions.add(start);
			}
			start = t.pos()+t.length();
		}
		if( exceptions.size()==0 ) return tokens;
		exceptions.add(0,IO.MULTIPLE);
		throw IO.exception(exceptions.toArray());
	}
	
	public Vector<Token> analize( String input ) throws IOException{ return analize( input, 0 );	}
	
	public static Vector<Token> remove(Vector<Token> tokens, String toremove ){
		for( int i=tokens.size()-1; i>=0; i-- )	if( toremove.indexOf(tokens.get(i).type()) >= 0 ) tokens.remove(i);
		return tokens;
	}
	
	public static Vector<Token> remove_space(Vector<Token> tokens ){ return remove(tokens, " "); }
	
	public static void main( String[] args ){
		Lexer lexer = new Lexer();
		lexer.add( new IntegerLexeme() );
		lexer.add( new RealLexeme() );
		lexer.add( new StringLexeme() );
		lexer.add( new CharLexeme() );
		lexer.add( new SpaceLexeme() );
		lexer.add( new SymbolLexeme("-+*") );
		
//		Pattern natural = Pattern.compile("\\d+\\.\\d+|\\d+|[-+]");
		
		String input = "12345- 'i' \"como\\\"\\u123F\"   678.90+1234*343434";
		try{
			Vector<Token> tokens = remove_space(lexer.analize(input));
			for( Token t:tokens ) System.out.println(t+"...."+lexer.map(t));
		}catch(Exception e) { e.printStackTrace(); }
/*
  		Matcher matcher = natural.matcher(input);
        while (matcher.find()) {
        	System.out.println(matcher.pattern()+ "#"+ matcher.start() + "#" + matcher.group());
        }
*/        
	}	
}