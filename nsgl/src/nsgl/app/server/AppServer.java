package nsgl.app.server;

import java.io.IOException;

import nsgl.app.FrontBackAppModel;
import nsgl.app.KeyMapAppModel;
import nsgl.app.side.Side;
import nsgl.java.reflect.Command;
import nsgl.server.DynServer;
import nsgl.server.DynServerAdaptee;
import nsgl.server.Response;
import nsgl.server.Session;
import nsgl.type.array.Vector;
import nsgl.type.collection.Collection;

public class AppServer extends KeyMapAppModel implements FrontBackAppModel, DynServerAdaptee{
	public static final String pull="pull()";
	protected DynServer server;

	public Response process(Command command) throws IOException {
		Object obj = backend().execute(command);
		if( obj == null ) return new Response(pull);
		return new Response(obj.toString());	
/*		
		if( command.id().equals("server") && command.method().equals("init") ){
			JSModel model = new JSModel((String)command.args()[0]);
			manager = (VCManager)model.side(FrontEnd.FRONTEND);
		}
		if( manager != null ) return manager.javacall(command);
		throw new IOException("Server has not been initialized"); */
	}
	
	protected Response manage_session(Session session, Command command) throws IOException {
		return null;
	}

	@Override
	public Response process(Session session, Command command) throws IOException {
		if( !session.expired(System.currentTimeMillis()) ) return process(command);
		else return manage_session(session, command);
	}

	@Override
	public Vector<String> map() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setServer(DynServer server) { this.server = server; }

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