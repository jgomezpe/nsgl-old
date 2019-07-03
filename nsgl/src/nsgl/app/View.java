package nsgl.app;

public interface View extends Component{
	default BackEnd back(){
		Side s = side();
		if( s==null ) return null; 
		AppModel m = s.model();
		if( m==null ) return null;
		return (BackEnd)m.get(BackEnd.BACKEND);
	}

	default FrontEnd front(){ return (FrontEnd)side(); }
}