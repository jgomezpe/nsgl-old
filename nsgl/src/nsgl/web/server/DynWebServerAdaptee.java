package nsgl.web.server;

import nsgl.java.reflect.Command;
import nsgl.type.array.Vector;
import nsgl.web.WebResponse;
import nsgl.web.WebSession;


public interface DynWebServerAdaptee {
	void setServer( DynWebServer server );
	DynWebServer server();
	WebResponse process( WebSession session, Command command ) throws Exception;
	Vector<String> map();	
}