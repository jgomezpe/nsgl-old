package nsgl.language;

import java.io.IOException;

import nsgl.exception.IO;
import nsgl.type.array.Vector;

public abstract class Parser{
	protected int pos;
	protected Token token;
	protected Vector<Token> tokens;
	
	char rule='$';
	
	public char rule(){ return rule; }
	public void setRule(char rule) { this.rule = rule; }

	public Typed analize( Vector<Token> tokens ) throws IOException{
		pos = 0;
		this.tokens = tokens;
		next();
		return process();
	}
	
	protected abstract Typed process() throws IOException;
	
	protected Token next() throws IOException{
		if( pos == tokens.size() ) throw IO.exception(IO.EOI);
		token = tokens.get(pos);
		pos++;
		return token;
	}	
}