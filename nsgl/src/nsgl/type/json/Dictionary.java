package nsgl.type.json;

import nsgl.java.reflect.Command;
import nsgl.java.reflect.CommandProcessor;
import nsgl.type.array.ArrayUtil;
import nsgl.type.keymap.HashMap;
import nsgl.type.keymap.KeyMap;

public class Dictionary implements CommandProcessor{
	protected KeyMap<String, JSON> dict = new HashMap<String,JSON>();

	@Override
	public Object process(Command command) throws Exception {
		JSON dictionary = dict.get(command.method()); 
		Object[] array = ArrayUtil.parse((String)command.arg(0));
		JSON resp = new JSON();
		for( Object x : array ){
			String tag = (String)x;
			String value = dictionary.getString(tag);
			if( value!=null ) resp.set(tag,value);
		}
		return resp;
	}
}