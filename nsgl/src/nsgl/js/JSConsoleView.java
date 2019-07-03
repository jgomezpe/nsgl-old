package nsgl.js;

import nsgl.gui.ConsoleView;

public class JSConsoleView extends JSView implements ConsoleView{
	protected String out="";
	protected String err="";
	
	public JSConsoleView(String id){ super("log",id); }

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
