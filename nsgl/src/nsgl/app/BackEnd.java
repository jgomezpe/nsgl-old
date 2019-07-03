package nsgl.app;

public interface BackEnd extends Side {
	public static final String BACKEND = "backend";
	public static final String CONTROLLER = "controller";
	default String id(){ return BACKEND; }
	default void setId(String id){}
	default FrontEnd frontend(){
		AppModel m = model();
		if( m==null ) return null;
		return (FrontEnd)m.get(FrontEnd.FRONTEND);
	}
	default Controller controller( String id ){ return (Controller)get(id); }
}