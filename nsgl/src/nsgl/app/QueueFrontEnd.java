package nsgl.app;

import nsgl.java.reflect.Command;
import nsgl.type.array.Vector;

public class QueueFrontEnd extends KeyMapSide implements FrontEnd{
	public QueueFrontEnd(){ super(FrontEnd.FRONTEND); }

	protected Vector<String> commands_queue = new Vector<String>();

	@Override
	public Object execute(Command command){ 
		commands_queue.add(command.toString());
		return commands_queue;
	}
	
	public String queue(){
		if( commands_queue.size() == 0 ) return "";
		StringBuilder sb = new StringBuilder();
		for(String c:commands_queue ){
			sb.append(c.toString());
			sb.append('\n');
		}
		commands_queue.clear();
		return sb.toString();
	}
}
