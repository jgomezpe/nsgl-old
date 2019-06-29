package nsgl.type.json.parser;

import nsgl.exception.IO;
import nsgl.language.Lexeme;

public class WordLexeme extends Lexeme{

	public WordLexeme(){ super('T', "true|false|null"); }
	
	@Override
	public Object instance(String input) throws Exception {
		if( input.equals("true") ) return true;
		if( input.equals("false") ) return false;
		if( input.equals("null") ) return null;
		throw IO.exception(JSONParser.NOVALID, input);
	}
}