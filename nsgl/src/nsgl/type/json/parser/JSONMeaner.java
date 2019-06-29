package nsgl.type.json.parser;

import nsgl.exception.IO;
import nsgl.language.LexemeSet;
import nsgl.language.Meaner;
import nsgl.language.Token;
import nsgl.language.Typed;
import nsgl.language.TypedValue;
import nsgl.type.array.Vector;
import nsgl.type.json.JSON;
import nsgl.type.object.Pair;

public class JSONMeaner implements Meaner<Object>{
	protected LexemeSet lexemes;
	
	public JSONMeaner(LexemeSet lexemes) { this.lexemes = lexemes; }
	
	@SuppressWarnings("unchecked")
	@Override
	public Object apply(Typed obj) throws Exception {
		if( obj instanceof Token ) return lexemes.map((Token)obj);
		@SuppressWarnings({"rawtypes" })
		TypedValue tv = (TypedValue)obj;
		if( tv.value() instanceof Vector ){
			Vector<Object> f = new Vector<Object>();
			Vector<Typed> v = (Vector<Typed>)tv.value(); 
			for( Typed t:v ) f.add(apply(t));
			Object[] attr = f.toArray();
			if( attr.length > 0 && attr[0] instanceof Pair) {
				JSON json = new JSON();
				for( Object a:attr ) json.set((Pair<String,Object>)a);
				return json;
			}
			return attr;
		}

		if( tv.value() instanceof Typed[] ) {
			Typed[] pair = (Typed[])tv.value();
			return new Pair<String, Object>((String)apply(pair[0]), apply(pair[1]));
		}
	
		throw IO.exception(JSONParser.NOVALID, obj.type());
	}
}
