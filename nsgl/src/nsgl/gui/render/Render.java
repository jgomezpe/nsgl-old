package nsgl.gui.render;

import nsgl.app.View;

public interface Render extends View{
	void render();
	default void render( Object obj ){
		init();
		add( obj );
		render();
	}
	void add( Object obj );
	void init();
}