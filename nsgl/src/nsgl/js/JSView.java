package nsgl.js;
import java.io.IOException;

import nsgl.app.DefaultComponent;
import nsgl.app.View;
import nsgl.java.reflect.Command;

public class JSView extends DefaultComponent implements View{
	protected String TYPE;
	
	public JSView(String TYPE, String id) {
		super(id);
		this.TYPE = TYPE;
	}

	public void execute( String method, Object... args ){
		Object[] nargs = new Object[args.length+1];
		System.arraycopy(args, 0, nargs, 1, args.length);
		nargs[0] = this.id();
		try { front().execute( new Command(TYPE, method, nargs) ); } catch (IOException e) { e.printStackTrace(); }
	}
}