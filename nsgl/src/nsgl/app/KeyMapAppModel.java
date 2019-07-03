package nsgl.app;

import nsgl.type.keymap.HashMap;

public class KeyMapAppModel extends HashMap<String, Side> implements AppModel{
	@Override
	public boolean set( String side_id, Side side ) {
		side.setModel(this);
		return super.set(side_id, side);
	}

	public boolean add( String side_id, Side side ) {
		side.setModel(this);
		return super.add(side_id, side);
	}
}