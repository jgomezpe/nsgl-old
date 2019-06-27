package nsgl.server;

import java.io.IOException;
import java.util.Iterator;

import nsgl.store.DataQuery;
import nsgl.store.DataStore;
import nsgl.type.array.Vector;
import nsgl.type.keymap.HashMap;
import nsgl.type.keymap.KeyMap;

public class MultiServer extends Server{
	public MultiServer(String name, DataStore store) {
		super(name, store);
		init();
	}

	protected HashMap<String, DynServer> servers = new HashMap<String,DynServer>();
	
	@Override
	public Response process(Command command) throws IOException {
		String platform = command.platform();
		DynServer server = servers.get(platform); 
		if( server==null ) {
			server = new DynServer(platform, store);
			servers.set(platform,server); 
		} 
		return server.process(command);
	}

	@Override
	public Response process(Session session, Command command) { return null; }

	public void init(){
		DataQuery query = store.getQuery(PLATFORM);
		Iterator<KeyMap<String, Object>> col = store.query(query);
		while( col.hasNext() ){ update( (String)col.next().get(NAME) ); }
	}
	
	public void update(String server_name ){
		DynServer server = new DynServer(server_name, store);
		servers.set(server_name,server); 
	}
	
	@Override
	public Vector<String> map() {
		Vector<String> m = new Vector<String>();
		for( String s:servers.keys() ){
			try{
				Server server = servers.get(s);
				Vector<String> sm = server.map();
				for( String k:sm ) m.add(k);
			}catch(Exception e){}
		}			
		return m;
	}
}