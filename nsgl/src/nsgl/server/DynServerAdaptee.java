package nsgl.server;

import java.io.IOException;

import nsgl.java.reflect.Command;
import nsgl.type.array.Vector;


public interface DynServerAdaptee {
	void setServer( DynServer server );
	Response process( Session session, Command command ) throws IOException;
	Vector<String> map();	
}