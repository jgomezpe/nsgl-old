package nsgl.web.server;
import java.io.IOException;

import nsgl.java.reflect.Command;
import nsgl.java.reflect.FromMemory;
import nsgl.service.io.FileResource;
import nsgl.store.DataStore;
import nsgl.type.array.Vector;
import nsgl.type.keymap.KeyMap;
import nsgl.web.WebResponse;
import nsgl.web.WebServer;
import nsgl.web.WebSession;
import nsgl.web.resource.FileConfig;

public class DynWebServer extends WebServer{
	public static final String SETUP = "setup";
	public static final String RESOURCES = "Resources";
	public static final String KEY = "key";
	protected final String USERID= "userid";
	protected final String USER = "User";
	
	protected DynWebServerAdaptee app;

	protected DynWebServerAdaptee platform(){
		FileConfig fc = new FileConfig(store, name);
		byte[] bytes = fc.download("server.jar");
		if( bytes!=null ){
			try{
				ClassLoader loader = FromMemory.jarLoader(bytes);
				Class<?> cl = loader.loadClass("AppServer");
				return (DynWebServerAdaptee)cl.newInstance();
			}catch(Exception e){ e.printStackTrace(); }
		}
		return null;
	}
	
	public void setup( String file ) throws IOException{
		FileConfig fc = new FileConfig(store);
		byte[] bytes = FileResource.toByteArray(FileResource.get(SETUP+'/'+file));
		fc.upload(file, bytes);
	}
	
	protected boolean put(String name){
		KeyMap<String,Object> platform = store.get(PLATFORM, NAME, name);
		if( platform==null ){
			KeyMap<String, Object> entity = DataStore.entity(PLATFORM);
			entity.set(NAME, name);
			entity.set(TIMEOUT, 600000);
			entity.set(KEY, NAME);
			store.put(entity);
			entity = DataStore.entity(USER);
			entity.set(PLATFORM, name);
			entity.set(USERID, name);
			entity.set(KEY, PLATFORM+','+USERID);
			entity.set("password", "admin");
			store.put(entity); 
			return true;
		}	
		return false;
	}
	
	protected void boot(){
		put(name);
		if( put(FileConfig.LIBRARY) ) try { setup( "server.jar" ); }catch (IOException e) { e.printStackTrace(); }
	}
	
	public DynWebServer( String name, DataStore store ){
		super(name,store);
		boot();
		app = platform();
		app.setServer(this);
	}
	
	@Override
	public WebResponse process(WebSession session, Command command) throws Exception{
		if( command.id().equals("server") && command.method().equals("reset") ) app = platform();
		else if( app != null ) return app.process(session, command);
		return null;
	}

	@Override
	public Vector<String> map(){ return app.map(); }	
}