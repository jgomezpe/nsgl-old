package nsgl.app.vc;

import nsgl.app.AppModel;
import nsgl.app.Component;
import nsgl.app.Side;

public interface View extends Component{
	default BackEnd back(){
		Side s = side();
		if( s==null ) return null; 
		AppModel m = s.model();
		if( m==null ) return null;
		return (BackEnd)m.get(BackEnd.ID);
	}

	default FrontEnd front(){ return (FrontEnd)side(); }
}