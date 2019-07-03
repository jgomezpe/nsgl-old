package nsgl.web;

import nsgl.java.reflect.Command;
import nsgl.store.DataStore;
import nsgl.type.array.Vector;
import nsgl.type.keymap.HashMap;
import nsgl.type.keymap.KeyMap;

public abstract class WebServer{
	public static final String PLATFORM = "Platform";
	public static final String TIMEOUT = "timeout";
	public static final String NAME = "name";

	protected DataStore store;
	protected String name;
	protected HashMap<String, WebSession> sessions = new HashMap<String,WebSession>();

	public WebServer( String name, DataStore store ){
		this.name = name;
		this.store = store;
	}
	
	public void setStore( DataStore store ){ this.store = store; };
	public DataStore store(){ return store; }
	
	public String name(){ return name; }

	protected WebSession init(WebCommand command){
		KeyMap<String,Object> platform = store.get(PLATFORM, NAME, name);
		if( platform==null ) return null;
		Long l = (Long)platform.get(TIMEOUT);
		if( l==null ) l = 60000l;
		WebSession s = new WebSession(command.session(), System.currentTimeMillis(), l );
		sessions.set(s.id(), s);
		return s;
	}
	
	public abstract WebResponse process( WebSession session, Command command ) throws Exception;
	
	public WebResponse process( WebCommand command ) throws Exception{
		command.client().setPlatform( name );
		String name = command.session();
		WebSession session= (WebSession)sessions.get(name); 
		if( session == null ) session = init(command); 
		return process( session, command );
	}
	
	public abstract Vector<String> map();
}