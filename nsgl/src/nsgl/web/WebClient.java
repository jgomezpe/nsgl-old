package nsgl.web;

public class WebClient {
	protected String ip;
	protected String navigator;
	protected String platform;

	public WebClient( String ip, String navigator, String platform ){
		this.ip = ip;
		this.navigator = navigator;
		this.platform = platform;
	}
	
	public void setPlatform( String platform ) { this.platform = platform; }
	
	public String ip(){ return ip; }
	public String navigator(){ return navigator; }
	public String platform(){ return platform; }
	
	public String session(){ return ip+navigator+platform; }
}
