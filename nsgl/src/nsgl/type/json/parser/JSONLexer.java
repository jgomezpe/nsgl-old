package nsgl.type.json.parser;

import nsgl.language.Lexer;
import nsgl.language.lexeme.IntLexeme;
import nsgl.language.lexeme.RealLexeme;
import nsgl.language.lexeme.SpaceLexeme;
import nsgl.language.lexeme.StringLexeme;
import nsgl.language.lexeme.SymbolLexeme;

public class JSONLexer extends Lexer{
	public JSONLexer() {
		add( new IntLexeme() );
		add( new RealLexeme() );
		add( new StringLexeme() );
		add( new SpaceLexeme() );
		add( new SymbolLexeme("\\[\\]\\{\\},:") );
		add( new WordLexeme() );
	}	
}