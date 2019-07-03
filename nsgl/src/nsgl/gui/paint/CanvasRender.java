package nsgl.gui.paint;

import nsgl.type.object.Cleanable;
import nsgl.type.collection.Collection;
import nsgl.type.collection.Mutable;
import nsgl.gui.Canvas;
import nsgl.gui.Render;

public interface CanvasRender extends Render{
	public Mutable<Drawable> objects();
	
	public void setCanvas( Canvas canvas );
	
	public Canvas getCanvas();
	
	default void init(){ ((Cleanable)objects()).clear(); }
	
	default void add( Drawable obj ){ objects().add(obj); }

	@Override
	default void add(Object obj){ add((Drawable)obj); }
	
	default void render(){
		Collection<Drawable> c = objects();
		for( Drawable d:c ) d.draw(getCanvas()); 
	}
	
	default void render( Object obj ){ render((Drawable)obj); }
}