package nsgl.gui;

import nsgl.app.vc.View;

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