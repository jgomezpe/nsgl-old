package nsgl.web.app;

import nsgl.app.keymap.KMAppModel;
import nsgl.app.keymap.KMBackEnd;
import nsgl.app.vc.BackEnd;
import nsgl.app.vc.VCAppModel;
import nsgl.app.vc.QueueFrontEnd;
import nsgl.java.reflect.Command;
import nsgl.type.array.Vector;
import nsgl.web.WebResponse;
import nsgl.web.WebCommand;
import nsgl.web.WebSession;
import nsgl.web.server.DynWebServer;
import nsgl.web.server.DynWebServerAdaptee;

public class WebServerAppModel extends KMAppModel implements VCAppModel, DynWebServerAdaptee{
	public static final String SERVER = "server";
	public static final String PULL = "pull";
	protected DynWebServer server;

	public WebServerAppModel() { this( new KMBackEnd() ); }
			
	public WebServerAppModel(BackEnd backend) { this(backend, new QueueFrontEnd() ); }
	
	public WebServerAppModel(BackEnd backend, QueueFrontEnd frontend) {
		init(backend, frontend); 
		backend.register(this.main());
	}
	
	public WebServerMainController main() { return new WebServerMainController(); }
	
	public WebResponse process(Command command) throws Exception {
		Object obj = back().execute(command);
		if( obj == null ) obj = "";
		if( obj instanceof WebResponse ) return (WebResponse)obj;
		return new WebResponse(obj.toString()); 	
/*		
		if( command.id().equals("server") && command.method().equals("init") ){
			JSModel model = new JSModel((String)command.args()[0]);
			manager = (VCManager)model.side(FrontEnd.FRONTEND);
		}
		if( manager != null ) return manager.javacall(command);
		throw new IOException("Server has not been initialized"); */
	}
	
	@Override
	public WebResponse process(WebSession session, Command command) throws Exception {
		long time = System.currentTimeMillis();
		if( session.expired(time) ){
			if( command.id().equals("log") && command.method().equals("in") ) return process(command); 
			else{
				WebCommand c = (WebCommand)command;
				WebCommand cmd = new WebCommand("log", "out", null);
				cmd.setClient(c.client()); 
				return process(cmd);
			} 
		}
		return process(command); 
	}

	@Override
	public Vector<String> map() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setServer(DynWebServer server) { this.server = server; }

	@Override
	public DynWebServer server(){ return this.server; }
}