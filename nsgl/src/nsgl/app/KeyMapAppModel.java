package nsgl.app;

import nsgl.app.side.Side;
import nsgl.type.collection.Collection;
import nsgl.type.keymap.HashMap;

public class KeyMapAppModel implements AppModel{
	protected HashMap<String, Side> sides = new HashMap<String,Side>();

	@Override
	public Side side(String id){ try{ return sides.get(id); }catch(Exception e){ return null; } }
	
	@Override
	public Collection<Side> sides(){ return sides; }

	@Override
	public void clear(){ sides.clear(); }
	
	@Override
	public void register( Side side ){
		sides.set(side.id(), side);
		if( side.model() != this ) side.setModel(this);
	}
}