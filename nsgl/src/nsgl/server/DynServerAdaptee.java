package nsgl.server;

import java.io.IOException;

import nsgl.type.array.Vector;


public abstract class DynServerAdaptee {
	protected DynServer server;
	public void setServer( DynServer server ){ this.server = server; }
	public abstract Response process( Session session, Command command ) throws IOException;
	public abstract Vector<String> map();	
}