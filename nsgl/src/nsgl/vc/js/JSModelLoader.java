package nsgl.vc.js;

import nsgl.type.keymap.KeyMap;
import nsgl.vc.BackEnd;
import nsgl.vc.Component;

public interface JSModelLoader {
	KeyMap<String, Component>  frontend( String url );
	BackEnd backend( String url );
}
