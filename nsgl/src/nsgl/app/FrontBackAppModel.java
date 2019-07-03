package nsgl.app;

public interface FrontBackAppModel extends AppModel{
	default void init(BackEnd backend, FrontEnd frontend ){	
		if( backend!=null ){
			backend.setModel(this);
			backend.setId(BackEnd.BACKEND);
			register(backend);
		}
		if( frontend != null ){
			frontend.setModel(this);
			frontend.setId(FrontEnd.FRONTEND);
			register(frontend);
		}
	}
	
	default BackEnd backend() { return (BackEnd)this.get(BackEnd.BACKEND); }
	default FrontEnd frontend() { return (FrontEnd)this.get(FrontEnd.FRONTEND); }
}