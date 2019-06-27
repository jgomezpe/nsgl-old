package nsgl.type.object.parser;

import java.io.IOException;

import nsgl.exception.IO;
import nsgl.language.Lexer;
import nsgl.language.Parser;
import nsgl.language.Token;
import nsgl.language.Typed;
import nsgl.language.TypedValue;
import nsgl.type.array.Vector;

public class ObjectParser extends Parser{
	protected char S = '#'; 
	@Override
	public Typed analize( Vector<Token> tokens ) throws IOException{
		tokens = Lexer.remove_space(tokens);
		return super.analize(tokens);
	}
	
	@Override
	public Typed process() throws IOException{
		if( token.type()==S && token.value().charAt(0)=='[' ) {
			Vector<Typed> v = new Vector<Typed>();
			next();
			while( token.type() != S || token.value().charAt(0)!=']' ){
				if(token.type() == S && token.value().charAt(0)==',' ) throw IO.exception(IO.UNEXPECTED, ',', token.pos());
				v.add(process());
				if(next().type() == S && token.value().charAt(0)==',' && next().type() == S && token.value().charAt(0)!='[') 
					throw IO.exception(IO.UNEXPECTED, token.value(), token.pos());
			}
			return new TypedValue<Vector<Typed>>('O', v);
		}
		return token; 
	}	
}