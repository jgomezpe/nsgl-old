package nsgl.app;

import nsgl.exception.IO;
import nsgl.exception.NoSuch;
import nsgl.java.reflect.Command;

import java.io.IOException;

import nsgl.type.collection.Indexed;
import nsgl.type.object.Named;

public interface Side extends Indexed<String,Component>, Named{
	default boolean hasChanged(){ return false; }
	default void synchronize(){}
	
	void setModel( AppModel model );
	AppModel model();

	default void register(Component component){
		String[] ids = component.ids();
		for( String id:ids) this.set(id, component); 
		component.setSide(this);
	}	
	
	default Object execute( Command command ) throws IOException{
		String id = command.id();
		Component c = get(id);
		if( c==null ) throw IO.exception(NoSuch.NOSUCHELEMENT, id);
		try { return command.run(c); }catch(Exception e ) { throw IO.exception(NoSuch.NOSUCHMETHOD, command.method()); }
	}	
}