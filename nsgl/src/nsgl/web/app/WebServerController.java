package nsgl.web.app;

import nsgl.app.DefaultComponent;
import nsgl.app.vc.Controller;
import nsgl.web.WebClient;

public class WebServerController extends DefaultComponent implements Controller{
	protected WebClient client = null;
	
	public WebServerController(String id) { super(id); }
	
	public void setClient( WebClient client ) { this.client = client; }
}
