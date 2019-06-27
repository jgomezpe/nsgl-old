package nsgl.vc.js.server;

import java.io.IOException;

import nsgl.server.Command;
import nsgl.vc.FrontEnd;
import nsgl.vc.js.JSModel;

public class VCServer implements Server{
	protected VCManager manager = null;

	@Override
	public String process(Command command) throws IOException {
		if( command.id().equals("server") && command.method().equals("init") ){
			JSModel model = new JSModel((String)command.args()[0]);
			manager = (VCManager)model.side(FrontEnd.FRONTEND);
		}
		if( manager != null ) return manager.javacall(command);
		throw new IOException("Server has not been initialized");
	}
}