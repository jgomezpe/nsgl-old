package nsgl.app.vc;

import nsgl.app.AppModel;

public interface VCAppModel extends AppModel{
	default void init(BackEnd backend, FrontEnd frontend ){	
		if( backend!=null ){
			backend.setModel(this);
			backend.setId(BackEnd.ID);
			register(backend);
		}
		if( frontend != null ){
			frontend.setModel(this);
			frontend.setId(FrontEnd.ID);
			register(frontend);
		}
	}
	
	default BackEnd back() { return (BackEnd)this.get(BackEnd.ID); }
	default FrontEnd front() { return (FrontEnd)this.get(FrontEnd.ID); }
}