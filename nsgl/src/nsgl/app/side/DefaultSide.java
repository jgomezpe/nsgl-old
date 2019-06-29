package nsgl.app.side;

import nsgl.app.AppModel;
import nsgl.type.object.Thing;

public abstract class DefaultSide extends Thing implements Side{
	public DefaultSide(String id) { super(id); }

	protected AppModel model;
	
	@Override
	public void setModel(AppModel model){ this.model = model; }

	@Override
	public AppModel model(){ return model; }
}