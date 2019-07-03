package nsgl.web.server;

import java.util.Iterator;

import nsgl.java.reflect.Command;
import nsgl.store.DataQuery;
import nsgl.store.DataStore;
import nsgl.type.array.Vector;
import nsgl.type.keymap.HashMap;
import nsgl.type.keymap.KeyMap;
import nsgl.web.WebResponse;
import nsgl.web.WebServer;
import nsgl.web.WebCommand;
import nsgl.web.WebSession;

public class MultiWebServer extends WebServer{
	public MultiWebServer(String name, DataStore store) {
		super(name, store);
		init();
	}

	protected HashMap<String, DynWebServer> servers = new HashMap<String,DynWebServer>();
	
	@Override
	public WebResponse process(WebCommand command) throws Exception {
		String platform = command.client().platform();
		DynWebServer server = servers.get(platform); 
		if( server==null ) {
			server = new DynWebServer(platform, store);
			servers.set(platform,server); 
		} 
		return server.process(command);
	}

	@Override
	public WebResponse process(WebSession session, Command command) { return null; }

	public void init(){
		DataQuery query = store.getQuery(PLATFORM);
		Iterator<KeyMap<String, Object>> col = store.query(query);
		while( col.hasNext() ){ update( (String)col.next().get(NAME) ); }
	}
	
	public void update(String server_name ){
		DynWebServer server = new DynWebServer(server_name, store);
		servers.set(server_name,server); 
	}
	
	@Override
	public Vector<String> map() {
		Vector<String> m = new Vector<String>();
		for( String s:servers.keys() ){
			try{
				WebServer server = servers.get(s);
				Vector<String> sm = server.map();
				for( String k:sm ) m.add(k);
			}catch(Exception e){}
		}			
		return m;
	}
}