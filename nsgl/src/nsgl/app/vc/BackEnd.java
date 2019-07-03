package nsgl.app.vc;

import nsgl.app.AppModel;
import nsgl.app.Side;

public interface BackEnd extends Side {
	public static final String ID = "backend";
	public static final String CONTROLLER = "controller";
	default String id(){ return ID; }
	default void setId(String id){}
	default FrontEnd frontend(){
		AppModel m = model();
		if( m==null ) return null;
		return (FrontEnd)m.get(FrontEnd.ID);
	}
	default Controller controller( String id ){ return (Controller)get(id); }
}