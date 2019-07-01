package nsgl.js;

import nsgl.gui.log.Log;

public class JSLog extends JSView implements Log{
	protected String out="";
	protected String err="";
	
	public JSLog(String id){ super("log",id); }

	@Override
	public void display(boolean output){
		if( output ) execute("out", out);
		else execute("error",err);
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
