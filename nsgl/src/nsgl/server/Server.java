package nsgl.server;

import java.io.IOException;

import nsgl.java.reflect.Command;
import nsgl.store.DataStore;
import nsgl.type.array.Vector;
import nsgl.type.keymap.HashMap;
import nsgl.type.keymap.KeyMap;

public abstract class Server{
	public static final String PLATFORM = "Platform";
	public static final String TIMEOUT = "timeout";
	public static final String NAME = "name";

	protected DataStore store;
	protected String name;
	protected HashMap<String, Session> sessions = new HashMap<String,Session>();

	public Server( String name, DataStore store ){
		this.name = name;
		this.store = store;
	}
	
	public void setStore( DataStore store ){ this.store = store; };
	public DataStore store(){ return store; }
	
	public String name(){ return name; }

	protected Session init(ServerCommand command){
		KeyMap<String,Object> platform = store.get(PLATFORM, NAME, name);
		if( platform==null ) return null;
		Long l = (Long)platform.get(TIMEOUT);
		if( l==null ) l = 60000l;
		Session s = new Session(command.session(), System.currentTimeMillis(), l );
		sessions.set(s.id(), s);
		return s;
	}
	
	public abstract Response process( Session session, Command command ) throws IOException;
	
	public Response process( ServerCommand command ) throws IOException{
		command.platform = name;
		String name = command.session();
		Session session= (Session)sessions.get(name); 
		if( session == null ) session = init(command); 
		return process( session, command );
	}
	
	public abstract Vector<String> map();
}