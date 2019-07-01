package nsgl.webserver;

import nsgl.store.DataStore;

public class FileConfig extends FileManager{
	public final static String LIBRARY = "lib";
	
	public FileConfig( DataStore store, String platform){ super( store, platform, true ); }

	public FileConfig( DataStore store ){ this( store, LIBRARY ); }
	
	public void jar(String name, byte[] data ){
		if( name.equals("server") ){
			uncompress = false;
			super.jar( name, data );
			uncompress = true;
		}else super.jar(name, data); 	
	}
	
	public byte[] download( String file ){
		byte[] data = super.download(file);
		if( data==null && !pack.equals(LIBRARY)){
			String xpack = pack;
			pack = LIBRARY;
			data = super.download(file);
			pack = xpack;
		}
		return data;
	}
}