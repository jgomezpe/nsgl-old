package nsgl.web.app;

import java.util.Iterator;

import nsgl.store.DataQuery;
import nsgl.store.DataStore;
import nsgl.type.keymap.KeyMap;

public abstract class WebServerAccess extends WebServerController{
	public static final String USER = "User";
	public static final String ACCESS = "log";
	
	protected DataStore store=null;
	
	public WebServerAccess() { super(ACCESS); }
	
	public void setStore( DataStore store ) { this.store = store; }
	
	protected boolean canAccess( String user, String pass ){
		if( store != null ) {
			DataQuery query = store.getQuery(USER);
			query.addFilter("userid", DataQuery.EQUAL, user);
			query.addFilter("password", DataQuery.EQUAL, pass);
			Iterator<KeyMap<String,Object>> col = store.query( query);
			return col.hasNext();
		}
		return false;
	}
	
	public abstract Object in( String user, String pass );	
	public abstract Object out();
}
