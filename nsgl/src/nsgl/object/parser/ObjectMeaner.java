package nsgl.object.parser;

import nsgl.language.LexemeSet;
import nsgl.language.Meaner;
import nsgl.language.Token;
import nsgl.language.Typed;
import nsgl.language.TypedValue;
import nsgl.vector.Vector;

public class ObjectMeaner implements Meaner<Object>{
	protected LexemeSet lexemes;
	
	public ObjectMeaner(LexemeSet lexemes) { this.lexemes = lexemes; }
	
	@Override
	public Object apply(Typed obj) throws Exception {
		if( obj instanceof Token ) return lexemes.map((Token)obj);
		@SuppressWarnings("unchecked")
		TypedValue<Vector<Typed>> tv = (TypedValue<Vector<Typed>>)obj;
		Vector<Object> v = new Vector<Object>();
		for( Typed t:tv.value() ) v.add(apply(t));
		return v.toArray();
	}

	public static void print( Typed type ) {
		if( type instanceof Token ) System.out.print(type);
		else {
			@SuppressWarnings("unchecked")
			TypedValue<Vector<Typed>> tv = (TypedValue<Vector<Typed>>)type;
			System.out.print('<');
			for( Typed t:tv.value() ) {
				System.out.print(' ');
				print(t);
			}
			System.out.print('>');
		}
	}
	
	public static void print( Object obj ) {
		if( obj instanceof Object[] ) {
			Object[] array = (Object[])obj;
			System.out.print('[');
			for( Object t:array ) {
				System.out.print(' ');
				print(t);
			}
			System.out.print(']');
		}else System.out.print(obj);
	}
	
	public static void main( String[] args ) {
		String msg = "[1234,\"dummy\",'A',[34.2,'F',\"mo\\\"re\\\"\"]]";
		ObjectLexer lexer = new ObjectLexer();
		try {
			Vector<Token> tokens = lexer.analize(msg);
			for( Token t : tokens ) System.out.println(t);
			System.out.println("*****************************");
			ObjectParser parser = new ObjectParser();
			Typed type = parser.analize(tokens);
			print(type);
			System.out.println();
			System.out.println("*****************************");
			ObjectMeaner meaner = new ObjectMeaner(lexer);
			Object obj = meaner.apply(type);
			print(obj);
		}catch(Exception e) {
			e.printStackTrace();
		}	
	}
	
}
