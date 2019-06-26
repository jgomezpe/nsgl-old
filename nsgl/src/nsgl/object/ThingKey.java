package nsgl.object;

import nsgl.keymap.Key;
import nsgl.keymap.MultiKey;

public class ThingKey implements Key<String,Thing>, MultiKey<Thing> {
	@Override
	public String key(Thing obj){ return obj.id(); }

	@Override
	public String[] keys(Thing obj) { return obj.ids();	}
}