package nsgl.app;

import nsgl.type.keymap.HashMap;
import nsgl.type.keymap.KeyMap;

public class KeyMapSide extends HashMap<String, Component> implements Side{
	protected String id;
	protected boolean changed = false;
	protected AppModel model;

	public KeyMapSide(String id){ this( id, new HashMap<String,Component>() ); }

	public KeyMapSide(String id, KeyMap<String, Component> keymap){
		this.id = id;
		init(keymap);
	}

	@Override
	public void setModel(AppModel model){ this.model = model; }

	@Override
	public AppModel model(){ return model; }
	
	public void init(KeyMap<String, Component> keymap){
		this.clear();		
		for( Component c:keymap ) if(c.side()!=this) c.setSide(this);	
	}
	
	@Override 
	public boolean add( String c_id, Component c ) {
		if(c.side()!=this) c.setSide(this);
		return super.add(c_id, c);
	}

	@Override 
	public boolean set( String c_id, Component c ) {
		if(c.side()!=this) c.setSide(this);
		return super.set(c_id, c);
	}
	
	public void register(Component component){
		changed = true;
		String[] ids = component.ids();
		for( String id:ids) this.set(id, component); 
		component.setSide(this);
	}
	
	public boolean hasChanged(){ return changed; }
	public void synchronize(){ changed = false; }

	@Override
	public String id(){ return this.id; }
	
	@Override
	public void setId(String id) { this.id = id; }	
}