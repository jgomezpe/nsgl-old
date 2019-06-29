package nsgl.language.generalized;

import nsgl.language.Language;
import nsgl.language.Lexer;
import nsgl.language.Meaner;
import nsgl.language.Parser;
import nsgl.type.collection.Collection;

public class GeneralizedLanguage<T,S> extends Language<T>{
	protected Encoder<S> encoder;
	public GeneralizedLanguage(Encoder<S> encoder, Lexer lexer, Parser parser, Meaner<T> meaner) {
		super(lexer, parser, meaner);
		this.encoder = encoder;
	}
	
	public T process( Collection<S> reader ) throws Exception{
		StringBuilder sb = new StringBuilder();
		for(S c:reader) sb.append(encoder.encode(c));
		return process( sb.toString() ); 
	}
}