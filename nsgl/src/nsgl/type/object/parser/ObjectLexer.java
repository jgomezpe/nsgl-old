package nsgl.type.object.parser;

import nsgl.language.Lexer;
import nsgl.language.lexeme.CharLexeme;
import nsgl.language.lexeme.IntLexeme;
import nsgl.language.lexeme.RealLexeme;
import nsgl.language.lexeme.SpaceLexeme;
import nsgl.language.lexeme.StringLexeme;
import nsgl.language.lexeme.SymbolLexeme;

public class ObjectLexer extends Lexer{
	public ObjectLexer() {
		add( new IntLexeme() );
		add( new RealLexeme() );
		add( new StringLexeme() );
		add( new CharLexeme() );
		add( new SpaceLexeme() );
		add( new SymbolLexeme("\\[\\],") );
	}
}
