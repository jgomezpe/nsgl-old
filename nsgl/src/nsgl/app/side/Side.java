package nsgl.app.side;

import nsgl.app.Component;
import nsgl.exception.IO;
import nsgl.exception.NoSuch;
import nsgl.java.reflect.Command;

import java.io.IOException;

import nsgl.app.AppModel;
import nsgl.type.collection.Collection;
import nsgl.type.object.Named;

public interface Side extends Named{
	default boolean hasChanged(){ return false; }
	default void synchronize(){}
	
	public Component component( String id );	
	public Collection<Component> components();
	
	public void setModel( AppModel model );
	public AppModel model();
	
	default Object execute( Command command ) throws IOException{
		String id = command.id();
		Component c = component(id);
		if( c==null ) throw IO.exception(NoSuch.NOSUCHELEMENT, id);
		try { return command.run(c); }catch(Exception e ) { throw IO.exception(NoSuch.NOSUCHMETHOD, command.method()); }
	}	
}