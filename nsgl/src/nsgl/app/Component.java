package nsgl.app;

import nsgl.type.array.Array;
import nsgl.type.object.Named;

public interface Component extends Named{
	public Side side();
	public void setSide( Side side );	
		
	default Array<Component> children(){ return null; }
}