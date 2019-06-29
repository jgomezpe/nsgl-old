package nsgl.server;

import java.io.InputStream;

import nsgl.java.reflect.Command;

public class ServerCommand extends Command {
	public ServerCommand(String id, String method, InputStream is) { super(id, method, is); }

	protected String ip;
	protected String navigator;
	protected String platform;

	public void session( String ip, String navigator, String platform ){
		this.ip = ip;
		this.navigator = navigator;
		this.platform = platform;
	}
	
	public String ip(){ return ip; }
	public String navigator(){ return navigator; }
	public String platform(){ return platform; }
	
	public String session(){ return ip+navigator+platform; }

}
