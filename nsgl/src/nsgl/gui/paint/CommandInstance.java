package nsgl.gui.paint;

import nsgl.type.json.JSON;
import nsgl.type.json.JSON2Instance;

public class CommandInstance implements JSON2Instance<Command>{
	public CommandInstance() {}

	@Override
	public Command load(JSON json) { return new Command( json ); }

	@Override
	public JSON store(Command command){ return command; }
}