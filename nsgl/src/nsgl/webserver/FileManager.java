package nsgl.webserver;

import java.util.Iterator;

import nsgl.java.reflect.FromMemory;
import nsgl.store.DataQuery;
import nsgl.store.DataStore;
import nsgl.type.keymap.KeyMap;

import java.time.LocalDate; // import the LocalDate class
import java.time.LocalTime;

public class FileManager {
	public static String RESOURCES = "Resources"; 
	public static String PLATFORM = "Platform"; 
	public static String DATA = "data"; 
	public static String NAME = "name"; 
	public static String TYPE = "type"; 
	public static String TIME = "time"; 
	public static String DATE = "date"; 
	public static String KEY = "key"; 
	protected DataStore store;
	protected boolean uncompress;
	protected String pack;
	
	public FileManager( DataStore store, String pack, boolean uncompress ){
		this.store = store;
		this.uncompress = uncompress;
		this.pack = pack;
	}

	public FileManager( DataStore store ){	this(store, "noname", false); }
	
	public void store(String name, String type, byte[] data){
		KeyMap<String, Object> entity = DataStore.entity(RESOURCES);
		entity.set(PLATFORM, pack);
		entity.set(NAME, name);
		entity.set(TYPE, type);
		entity.set(DATA, data);
		entity.set(TIME, LocalTime.now().toString());
		entity.set(DATE, LocalDate.now().toString());
		entity.set(KEY, PLATFORM+','+NAME+','+TYPE);
		store.put(entity); 
	}
	
	public void zip(String name, byte[] data ){
		if( uncompress ){
			try {
				KeyMap<String, byte[]> map = FromMemory.zip(data);
				for( String key:map.keys() ) upload( key.substring(1), map.get(key) );
			} catch (Exception e) { e.printStackTrace(); }
		}else store(name, "zip", data);
	}
	
	public void jar(String name, byte[] data ){
		if( uncompress ){
			try {
				KeyMap<String, byte[]> map = FromMemory.jar(data);
				for( String key:map.keys() ) upload( key.substring(1), map.get(key) );
			} catch (Exception e) { e.printStackTrace(); }
		}else store(name, "jar", data);
	}
	
	public void upload(String file, byte[] data){
		int k = file.lastIndexOf('.');
		String name = file.substring(0,k);
		String type = file.substring(k+1);
		if( type.equals("zip") ) zip(name, data);
		else if( type.equals("jar") ) jar(name,data);
		else store(name, type, data);
	}
	
	public void upload( WebServerCommand cmd ){
		pack = cmd.platform();
		upload( (String)cmd.arg(0), (byte[])cmd.arg(1));
	}
	
	public byte[] download( String file ){
		int k = file.lastIndexOf('.');
		String name = file.substring(0,k);
		String type = file.substring(k+1);
		DataQuery query = store.getQuery(RESOURCES);
		query.addFilter(PLATFORM, DataQuery.EQUAL, pack);
		query.addFilter(NAME, DataQuery.EQUAL, name);
		query.addFilter(TYPE, DataQuery.EQUAL, type);
		Iterator<KeyMap<String, Object>> col = store.query(query);
		if( col.hasNext() )	return (byte[])(col.next().get(DATA));
		return null;
	}
	
	public byte[] download( WebServerCommand cmd ){
		pack = cmd.platform();
		return download( (String)cmd.arg(0) );
	}
}