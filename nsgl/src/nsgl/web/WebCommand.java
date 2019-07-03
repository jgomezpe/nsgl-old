package nsgl.web;

import java.io.InputStream;

import nsgl.java.reflect.Command;
import nsgl.web.app.WebServerController;

public class WebCommand extends Command {
	protected WebClient client = null;
	
	public WebCommand(String id, String method, InputStream is) { super(id, method, is); }

	public void setClient( WebClient client ){ this.client = client; }
	
	public WebClient client(){ return client; }
	@Override
	public Object run( Object obj ) throws Exception{
		if(obj instanceof WebServerController ) ((WebServerController)obj).setClient(client);
		return super.run(obj);
	}
	
	public String session() { return client.session(); }
}