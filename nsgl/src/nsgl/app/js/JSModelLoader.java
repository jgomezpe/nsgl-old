package nsgl.app.js;

import nsgl.app.BackEnd;
import nsgl.app.Component;
import nsgl.type.keymap.KeyMap;

public interface JSModelLoader {
	KeyMap<String, Component>  frontend( String url );
	BackEnd backend( String url );
}
