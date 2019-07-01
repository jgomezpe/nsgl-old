package nsgl.webserver;

import nsgl.java.reflect.Command;
import nsgl.type.array.Vector;


public interface DynWebServerAdaptee {
	void setServer( DynWebServer server );
	Response process( Session session, Command command ) throws Exception;
	Vector<String> map();	
}