package nsgl.exception;

import nsgl.json.JSON;

public class ProcessExceptionWithJSON extends ProcessException {
	protected JSON manager;
	
	public ProcessExceptionWithJSON( JSON manager ) { this.manager = manager; }
	
	public ProcessExceptionWithJSON(){ this( new JSON() ); }
	
	@Override
	protected String apply(Object[] msg) {
		StringBuilder sb = new StringBuilder();
		sb.append(manager.getString((String)msg[0]));
		sb.append(' ');
		String format = manager.getString((String)msg[1]);
		Object[] e_args = new Object[msg.length-2];
		System.arraycopy(msg, 2, e_args, 0, e_args.length);
		sb.append(String.format(format, e_args));
		return sb.toString();
	}
}