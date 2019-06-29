package nsgl.app;

import nsgl.app.side.Side;

public interface Controller extends Component{
	default FrontEnd front(){
		Side s = side();
		if( s==null ) return null; 
		AppModel m = s.model();
		if( m==null ) return null;
		return (FrontEnd)m.side(FrontEnd.FRONTEND);
	}
	
	default BackEnd back(){ return (BackEnd)side(); }
}