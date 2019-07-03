package nsgl.app.vc;

import nsgl.app.AppModel;
import nsgl.app.Side;

public interface FrontEnd extends Side{
	public static final String ID = "frontend";	
	public static final String VIEW = "view";	
	default String id(){ return ID; }
	default void setId(String id){}
	default BackEnd backend(){
		AppModel m = model();
		if( m==null ) return null;
		return (BackEnd)m.get(BackEnd.ID);
	}
	default View view( String id ){ return (View)get(id); }
}