package nsgl.app.vc;

import nsgl.app.AppModel;
import nsgl.app.Component;
import nsgl.app.Side;

public interface Controller extends Component{
	default FrontEnd front(){
		Side s = side();
		if( s==null ) return null; 
		AppModel m = s.model();
		if( m==null ) return null;
		return (FrontEnd)m.get(FrontEnd.ID);
	}
	
	default BackEnd back(){ return (BackEnd)side(); }
}