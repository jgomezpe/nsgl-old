package nsgl.type.object;

import nsgl.type.keymap.HashMap;
import nsgl.type.keymap.KeyMap;

public class UsesKeyMap<K,V> {
	protected KeyMap<K, V> keymap;
	
	public UsesKeyMap( KeyMap<K, V> keymap ){ this.keymap = keymap; }
	
	public UsesKeyMap(){ this( new HashMap<K,V>() ); }
}