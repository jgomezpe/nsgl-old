package demo.type;

import nsgl.language.Token;
import nsgl.language.Typed;
import nsgl.language.TypedValue;
import nsgl.type.array.Vector;
import nsgl.type.object.parser.ObjectLexer;
import nsgl.type.object.parser.ObjectMeaner;
import nsgl.type.object.parser.ObjectParser;

public class ObjectParserDemo {
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
