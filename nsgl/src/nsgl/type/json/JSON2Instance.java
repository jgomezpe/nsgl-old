package nsgl.type.json;

import nsgl.type.json.JSON;

public interface JSON2Instance<T>{
	public T load( JSON json );
	public JSON store( T obj );
}