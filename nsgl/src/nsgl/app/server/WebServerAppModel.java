package nsgl.app.server;

import java.io.IOException;

import nsgl.app.BackEnd;
import nsgl.app.FrontBackAppModel;
import nsgl.app.KeyMapAppModel;
import nsgl.app.Side;
import nsgl.java.reflect.Command;
import nsgl.type.array.Vector;
import nsgl.type.collection.Collection;
import nsgl.webserver.DynWebServer;
import nsgl.webserver.DynWebServerAdaptee;
import nsgl.webserver.Response;
import nsgl.webserver.Session;

public class WebServerAppModel extends KeyMapAppModel implements FrontBackAppModel, DynWebServerAdaptee{
	public static final String SERVER = "server";
	public static final String PULL = "pull";
	protected DynWebServer server;

	public WebServerAppModel(BackEnd backend, WebFrontEnd frontend) { init(backend, frontend); }
	
	protected String server(Command command) throws Exception {
		if(command.method().equals(PULL)) return ((WebFrontEnd)frontend()).queue();
		return null;
	}
	
	public Response process(Command command) throws Exception {
		Object obj = (command.id().equals(SERVER))?server(command):backend().execute(command);
		if( obj == null ) obj = "";
		return new Response(obj.toString()); 	
/*		
		if( command.id().equals("server") && command.method().equals("init") ){
			JSModel model = new JSModel((String)command.args()[0]);
			manager = (VCManager)model.side(FrontEnd.FRONTEND);
		}
		if( manager != null ) return manager.javacall(command);
		throw new IOException("Server has not been initialized"); */
	}
	
	protected boolean manage_session(Session session, Command command) throws IOException {
		return true;
	}

	@Override
	public Response process(Session session, Command command) throws Exception {
		if( manage_session(session, command) ) return process(command);
		return null;
	}

	@Override
	public Vector<String> map() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setServer(DynWebServer server) { this.server = server; }

	@Override
	public Side side(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Side> sides() {
		// TODO Auto-generated method stub
		return null;
	}
}