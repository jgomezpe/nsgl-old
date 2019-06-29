package nsgl.app;

import nsgl.app.side.Side;

public interface View extends Component{
	default BackEnd back(){
		Side s = side();
		if( s==null ) return null; 
		AppModel m = s.model();
		if( m==null ) return null;
		return (BackEnd)m.side(BackEnd.BACKEND);
	}

	default FrontEnd front(){ return (FrontEnd)side(); }
}