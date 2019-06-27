package nsgl.type.object;

import nsgl.type.keymap.Key;

public class ObjectKey implements Key<String, Object>{
	@Override
	public String key(Object obj){ return obj.toString(); }
}