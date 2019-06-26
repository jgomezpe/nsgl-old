package nsgl.exception;

import nsgl.language.Language;
import nsgl.object.parser.ObjectLexer;
import nsgl.object.parser.ObjectMeaner;
import nsgl.object.parser.ObjectParser;

public abstract class ProcessException {
	protected static Language<Object> lang = null;

	abstract protected String apply( Object[] str );
	
	public String apply( String str ){
		if( lang == null ){
			ObjectLexer lexer = new ObjectLexer();
			ObjectParser parser = new ObjectParser();
			ObjectMeaner meaner = new ObjectMeaner(lexer);
			lang = new Language<Object>(lexer, parser, meaner);
		}
		try{
			Object obj =  lang.process(str);
			if( !(obj instanceof Object[]) ) obj = new Object[] {obj};
			return this.apply((Object[])obj); 
		}catch(Exception e) { return e.getMessage(); }
	}
}