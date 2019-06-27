package nsgl.gui.log;

import nsgl.vc.DefaultComponent;
import nsgl.vc.js.JSView;

public class JSLog extends DefaultComponent implements JSView, Log{
	protected String out="";
	protected String err="";
	
	public JSLog(String id){ super(id); }

	@Override
	public void display(boolean output){
		if( output ) execute("log.out", out);
		else execute("log.error",err);
	}

	@Override
	public void error(String txt) {
		err = txt;
		display(false);
	}

	@Override
	public void out(String txt) {
		out = txt;
		display(true);
	}
}
