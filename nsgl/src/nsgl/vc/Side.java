package nsgl.vc;

import nsgl.type.collection.Collection;
import nsgl.type.object.Named;

public interface Side extends Named{
	default boolean hasChanged(){ return false; }
	default void synchronize(){}
	
	public Component component( String id );	
	public Collection<Component> components();
	
	public void setModel( Model model );
	public Model model();
}