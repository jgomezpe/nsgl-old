package nsgl.js;

import nsgl.gui.paint.Canvas;
import nsgl.gui.paint.CanvasRender;
import nsgl.gui.paint.Drawable;
import nsgl.type.array.Vector;
import nsgl.type.collection.Mutable;

public class JSCanvasRender extends JSView implements CanvasRender{
	protected Vector<Drawable> objects = new Vector<Drawable>();
	protected JSCanvas canvas;

	public JSCanvasRender(String id){
		super("canvas", id);
		canvas = new JSCanvas(this); 
	}
	
	@Override
	public Canvas getCanvas(){ return canvas; }

	@Override
	public Mutable<Drawable> objects(){ return objects; }

	@Override
	public void setCanvas(Canvas canvas){}
	
	@Override
	public void render( Object obj ){
		add(obj);
		render();
	}	
}
