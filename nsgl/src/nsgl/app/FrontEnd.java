package nsgl.app;

public interface FrontEnd extends Side{
	public static final String FRONTEND = "frontend";	
	public static final String VIEW = "view";	
	default String id(){ return FRONTEND; }
	default void setId(String id){}
	default BackEnd backend(){
		AppModel m = model();
		if( m==null ) return null;
		return (BackEnd)m.get(BackEnd.BACKEND);
	}
	default View view( String id ){ return (View)get(id); }
}